import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static myutils.Skrocenie_Print.println;

/*
Exercise 11: (2) In the innerclasses/GreenhouseController.java example,
GreenhouseController contains a hard-coded set of events. Change the program so that it
reads the events and their relative times from a text file.
*/

abstract class Event_2 {
    private long eventTime;
    private String name;
    protected final long delayTime;
    public Event_2(long delayTime) {
        this.delayTime = delayTime;
        start();
    }
    public void start() { // Allows restarting
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }
    public abstract void action();
}
class Controller_3 {
    // A class from java.util to hold Event objects:
    private List<Event_2> eventList = new ArrayList<Event_2>();
    public void addEvent(Event_2 c) { eventList.add(c); }
    public void run() {
        while(eventList.size() > 0)
            // Make a copy so you're not modifying the list
            // while you're selecting the elements in it:
            for(Event_2 e : new ArrayList<Event_2>(eventList))
                if(e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
    }
}
class GreenhouseControls_3 extends Controller_3 {
    private boolean light = false;
    public class LightOn extends Event_2 {
        public LightOn(long delayTime) { super(delayTime); }
        public void action() {
            // Put hardware control code here to
            // physically turn on the light.
            light = true;
        }
        public String toString() { return "Light is on"; }
    }
    public class LightOff extends Event_2 {
        public LightOff(long delayTime) { super(delayTime); }
        public void action() {
            // Put hardware control code here to
            // physically turn off the light.
            light = false;
        }
        public String toString() { return "Light is off"; }
    }
    private boolean water = false;
    public class WaterOn extends Event_2 {
        public WaterOn(long delayTime) { super(delayTime); }
        public void action() {
            // Put hardware control code here.
            water = true;
        }
        public String toString() {
            return "Greenhouse water is on";
        }
    }
    public class WaterOff extends Event_2 {
        public WaterOff(long delayTime) { super(delayTime); }
        public void action() {
            // Put hardware control code here.
            water = false;
        }
        public String toString() {
            return "Greenhouse water is off";
        }
    }
    private String thermostat = "Day";
    public class ThermostatNight extends Event_2 {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            thermostat = "Night";
        }
        public String toString() {
            return "Thermostat on night setting";
        }
    }
    public class ThermostatDay extends Event_2 {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            thermostat = "Day";
        }
        public String toString() {
            return "Thermostat on day setting";
        }
    }
    // An example of an action() that inserts a
    // new one of itself into the event list:
    public class Bell extends Event_2 {
        public Bell(long delayTime) { super(delayTime); }
        public void action() {
            addEvent(new Bell(delayTime));
        }
        public String toString() { return "Bing!"; }
    }
    class Restart extends Event_2 {
        private Event_2[] eventList;

        public Restart(long delayTime, Event_2[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event_2 e : eventList)
                addEvent(e);
        }

        public void action() {
            for (Event_2 e : eventList) {
                e.start(); // Rerun each event
                addEvent(e);
            }
            start(); // Rerun this Event
            addEvent(this);
        }

        public String toString() {
            return "Restarting system";
        }
    }
    public static class Terminate extends Event_2 {
        public Terminate(long delayTime) { super(delayTime); }
        public void action() { System.exit(0); }
        public String toString() { return "Terminating";  }
    }
}
public class Zad18_11 {
    public static LinkedList<String> read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        LinkedList<String> zz = new LinkedList<>();
        while ((s = in.readLine()) != null)
            //dodaje każdą linię z pliku do listy
            zz.add(s);
        in.close();
        return zz;
    }

    public static void main(String[] args) throws IOException {
        long delay = 12000L * 1000000;
        GreenhouseControls_3 gc = new GreenhouseControls_3();
        if (args.length <= 0) {
            println("USAGE: java Zad18_11 filename");
            return;
        }
        //lista eventów
        List<Event_2> events = new ArrayList<Event_2>();
        for (String line : read(args[0])) {
            //podzielenie listy line na osobne wyrazy
            String[] parts = line.split("\\s+");
            String eventName = parts[0];
            // czas w pliku event.txt jest podany w milisekundach, a Event używa System.nanoTime()
            //dlatego mnożenie przez 1000000
            long eventTime = Long.parseLong(parts[1]) * 1000000;
            //dla każdej linii z pliku sprawdzana jest nazwa eventu, która tworzy odpowiedni obiekt klasy Event
            switch(eventName) {
                case "BELL":
                    gc.addEvent(gc.new Bell(eventTime));
                    break;
                case "LightOn":
                    events.add(gc.new LightOn(eventTime));
                    break;
                case "LightOff":
                    events.add(gc.new LightOff(eventTime));
                    break;
                case "WaterOn":
                    events.add(gc.new WaterOn(eventTime));
                    break;
                case "WaterOff":
                    events.add(gc.new WaterOff(eventTime));
                    break;
                case "ThermostatDay":
                    events.add(gc.new ThermostatDay(eventTime));
                    break;
                case "ThermostatNight":
                    events.add(gc.new ThermostatNight(eventTime));
                    break;
                default:
                    println("nieznany event: " + eventName);
            }
        }
        //zamiana kontenera eventów na array eventów, aby można było uruchomić z metodą Restart()
        Event_2[] eventArray = events.toArray(new Event_2[0]);
        gc.addEvent(gc.new Restart(2000L * 1000000, eventArray));
        gc.addEvent(new GreenhouseControls_3.Terminate(delay));
        gc.run();
    }
}