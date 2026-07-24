import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;
import java.util.*;
/*
Exercise 17: (2) Create a radiation counter that can have any number of remote sensors.
*/
class GeigerCounter implements Runnable {
    //id konkretnego sensora
    private final int id;
    public GeigerCounter(int id) {
        this.id = id;
        gcounters.add(this);
    }
    //lokaln licznik, każdy sensor posiada własną wartość number
    private int number = 0;
    //lista wszystkich utworzonych sensorów,
    //pozwala na końcu zsumować wyniki każdego z nich
    private static List<GeigerCounter> gcounters =
            new ArrayList<GeigerCounter>();
    //wspólny licznik wszystkich wykrytych zdarzeń
    //pole static oznacza, że wszystkie sensory korzystają z jednej wartości
    private static int count = 0;
    private static volatile boolean canceled = false;
    //używane do zwiększenia prawdopodobieństwa przełączenia wątku
    //podczas modyfikowania wspólnego licznika
    private Random rand = new Random(47);
    // Remove the synchronized keyword to see counting fail:
    //zwiększa licznik wszystkich sensorów
    //ponieważ count jest static, wszystkie wątki modyfikują tę samą wartość
    //synchronizacja GeigerCounter.class sprawia, że tylko jeden wątek
    //może w danym momencie wykonać całą operację zwiększenia count
    public int increment() {
        synchronized (GeigerCounter.class) {
            int temp = count;
            if (rand.nextBoolean()) // Yield half the time
                Thread.yield();
            return (count = ++temp);
        }
    }
    public void run() {
        //każdy sensor pracuje do momentu ustawienia canceled na true
        while(!canceled) {
            //number należy do konkretnego sensora
            //zwiększenie i odczyt number korzystają z tej samej blokady this
            synchronized(this) {
                ++number;
            }
            println(this);
            increment();
        }

    }
    //zwraca wspólną liczbę wszystkich wykrytych zdarzeń, używa blokady GeigerCounter.class
    //czyli tej samej co increment()
    public static synchronized int getTotalCount() {
        return count;
    }
    //zwraca liczbę zdarzeń  przez sensor
    //synchronized korzysta z blokady this
    public synchronized int getValue() { return number; }
    //sumuje liczniki wszystkich sensorów,
    //wynik powinien być taki sam jak wartość wspólnego count
    public static int sumSensors() {
        int sum = 0;
        for(GeigerCounter sensors : gcounters)
            sum += sensors.getValue();
        return sum;
    }
    public String toString() {
        return "GeigerCounter sensor nr: " + id;
    }
    //flaga zatrzymująca wszystkie sensory
    public static void cancel() {
        canceled = true;
    }
}

public class Zad21_17{
    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            println("użycie: java Zad21_17 <ilość czujników>");
            return;
        }
        int z = Integer.parseInt(args[0]);
        ExecutorService kz = Executors.newCachedThreadPool();
        for (int i = 0; i < z; i++) {
            kz.execute(new GeigerCounter(i));
        }
        TimeUnit.SECONDS.sleep(3);
        GeigerCounter.cancel();
        kz.shutdown();
        if(!kz.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        println("Total: " + GeigerCounter.getTotalCount());
        println("Sum of Entrances: " + GeigerCounter.sumSensors());

    }
}

/*
Program uruchamia wiele czujników jako osobne zadania i zatrzymuje je po określonym czasie,
następnie porównuje wspólny licznik z sumą wartości wszystkich pojedynczych czujników.

Dostęp do wspólnego licznika musi być odpowiednio zsynchronizowany, aby kilka
wątków nie mogło jednocześnie modyfikować wartości.
*/