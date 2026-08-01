import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 33: (7) Modify GreenhouseScheduler.java so that it uses a DelayQueue
instead of a ScheduledExecutor.
*/
public class Zad21_33 {
    //stan urządzeń w szklarni widoczny dla wszystkich Threads
    private volatile boolean light = false;
    private volatile boolean water = false;
    //jedna wspólna kolejka przechowująca zaplanowane zdarzenia
    private final DelayQueue<DelayedEvent> queue = new DelayQueue<DelayedEvent>();
    private String thermostat = "Day";
    public synchronized String getThermostat() {
        return thermostat;
    }
    public synchronized void setThermostat(String value) {
        thermostat = value;
    }
    //dodaje zdarzenie które ma wykonać się tylko jeden raz
    public void schedule(Runnable event, int delay) {
        DelayedEvent e = new DelayedEvent(event, delay);
        queue.put(e);
    }
    //dodaje zdarzenie, wykonywane pierwszy raz po initialDelay, a następnie cyklicznie co podany period
    public void repeat(Runnable event, int initialDelay, long period) {
        DelayedEvent e = new DelayedEvent(event, initialDelay, period);
        queue.put(e);
    }
    /*
    Opakowanie dla właściwego zdarzenia Runnable
    Przechowuje termin wykonania i ewentualny okres powtarzania
     */
        class DelayedEvent implements Delayed, Runnable {
        private final Runnable event;
        //moment, w którym zdarzenie ma stać się dostępne
        private long trigger;
        //okres powtarzania, wartość 0 oznacza że zdarzenie jest jednorazowe
        private long period;
        //zdarzenia powtarzalne
        public DelayedEvent(Runnable event, int delay, long period) {
            this.event = event;
            trigger = System.nanoTime() +
                    NANOSECONDS.convert(delay, MILLISECONDS);
            this.period = NANOSECONDS.convert(period, MILLISECONDS);
        }
        //zdarzenia jednorazowe
        DelayedEvent(Runnable event, int delay) {
            this.event = event;
            trigger = System.nanoTime() +
                    NANOSECONDS.convert(delay, MILLISECONDS);
        }
        //zwraca czas pozostały do wykonania zdarzenia
        public long getDelay(TimeUnit unit) {
            return unit.convert(
                    trigger - System.nanoTime(), NANOSECONDS);
        }
        //ustawia zdarzenie w kolejce według terminu wykonania
        public int compareTo(Delayed arg) {
            DelayedEvent that = (DelayedEvent)arg;
            if(trigger < that.trigger) return -1;
            if(trigger > that.trigger) return 1;
            return 0;
        }
        @Override
        //odpala zdarzenia
        public void run() {
            event.run();
                if(period > 0) {
                    trigger += period;
                    queue.put(this);
                }
        }
        }
    class LightOn implements Runnable{
        public void run() {
            // Put hardware control code here to
            // physically turn on the light.
            System.out.println("Turning on lights");
            light = true;
        }
    }
    class LightOff implements Runnable{
        public void run() {
            // Put hardware control code here to
            // physically turn off the light.
            System.out.println("Turning off lights");
            light = false;
        }
    }
    class WaterOn implements Runnable{
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }
    class WaterOff implements Runnable{
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }
    class ThermostatNight implements Runnable{
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }
    class ThermostatDay implements Runnable{
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }
    //task czeka na najbliższe gotowe zdarzenie, pobiera je z kolejki i wykonuje w swoim wątku
    class DelayedTaskConsumer implements Runnable {
        private DelayQueue<DelayedEvent> q;
        public DelayedTaskConsumer(DelayQueue<DelayedEvent> q) {
            this.q = q;
        }
        public void run() {
            try {
                while(!Thread.interrupted())
                    //take() blokuje wątek, dopóki delay pierwszego zdarzenia nie wygaśnie
                    q.take().run(); // Run task with the current thread
            } catch(InterruptedException e) {
                // Acceptable way to exit
            }
            println("Finished DelayedTaskConsumer");
        }
    }
    class Bell implements Runnable{
        public void run() { System.out.println("Bing!"); }
    }
    class Terminate implements Runnable {
        private ExecutorService exec;

        public Terminate(ExecutorService e) {
            exec = e;
        }

        public void run() {
            println(this + " Calling shutdownNow()");
            exec.shutdownNow();
            //dane DataPoint są zapisywane w osobnym wątku, ponieważ bieżący jest zatrzymywany
            new Thread() {
                public void run() {
                    for(DataPoint d : data)
                        System.out.println(d);
                }
            }.start();
        }
    }
    // New feature: data collection
    //pojedynczy zapis z czujników
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }
        public String toString() {
            return time.getTime() +
                    String.format(
                            " temperature: %1$.1f humidity: %2$.2f",
                            temperature, humidity);
        }
    }
        private Calendar lastTime = Calendar.getInstance();
    { // ustawia początkowy czas pomiaru
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }
    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);
    //lista przystosowana do użycia pod threads
    List<DataPoint> data = Collections.synchronizedList(
            new ArrayList<DataPoint>());
    class CollectData implements Runnable {
        public void run() {
            System.out.println("Collecting data");
            synchronized(Zad21_33.this) {
                // Pretend the interval is longer than it is:
                lastTime.set(Calendar.MINUTE,
                        lastTime.get(Calendar.MINUTE) + 30);
                // One in 5 chances of reversing the direction:
                if(rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                // Store previous value:
                lastTemp = lastTemp +
                        tempDirection * (1.0f + rand.nextFloat());
                if(rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity +
                        humidityDirection * rand.nextFloat();
                // Calendar must be cloned, otherwise all
                // DataPoints hold references to the same lastTime.
                // For a basic object like Calendar, clone() is OK.
                data.add(new DataPoint((Calendar)lastTime.clone(),
                        lastTemp, lastHumidity));
            }
        }
    }
    public static void main(String[] args) {
        Zad21_33 gh = new Zad21_33();
        // Former "Restart" class not necessary:
        ExecutorService exec = Executors.newCachedThreadPool();
        gh.repeat(gh.new Bell(), 0, 1500);
        gh.repeat(gh.new ThermostatNight(),0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);
        gh.schedule(gh.new Terminate(exec), 10000);
        exec.execute((gh.new DelayedTaskConsumer(gh.queue)));
    }
}

/*
DelayQueue przechowuje obiekty DelayedEvent i ustawia je według wartości trigger.
Jako pierwszy udostępnia element z najwcześniejszym trigger,
ale dopiero po wygaśnięciu jego opóźnienia.

schedule() dodaje zdarzenie jednorazowe
repeat() dodaje zdarzenie powtarzalne z określonym period

DelayedTaskConsumer wykonuje take(), które czeka aż termin najbliższego zdarzenia zostanie osiągnięty

Runnable
    V
    V
    V
DelayedEvent
    V
    V
    V
DelayQueue
    V
    V take()
    V
DelayedTaskConsumer
    V
    V
    V
event.run()
    V
    V
    V
ponowne dodanie do kolejki

 */