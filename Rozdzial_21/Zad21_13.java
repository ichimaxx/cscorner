import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 13: (1) Repair SerialNumberChecker.java using the synchronized
keyword. Can you demonstrate that it is now correct?
*/
class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static synchronized int nextSerialNumber() {
        return serialNumber++;
    }
}
//przechowuje ostatnio wygenerowane numery
class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;
    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value not produced
        // by the SerialNumberGenerator:
        for(int i = 0; i < size; i++)
            array[i] = -1;
    }
    public synchronized void add(int i) {
        array[index] = i;
        // Wrap index and write over old elements:
        index = ++index % len;
    }
    public synchronized boolean contains(int val) {
        for(int i = 0; i < len; i++)
            if(array[i] == val) return true;
        return false;
    }
}
public class Zad21_13 {
    private static volatile boolean runner = true;
    private static final int SIZE = 10;
    private static CircularSet serials =
            new CircularSet(1000);
    private static ExecutorService exec =
            Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
        public void run() {
            while(Zad21_13.runner) {
                int serial =
                        SerialNumberGenerator.nextSerialNumber();
                println(serial);
                if(serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }
    public static void stop() {
        runner = false;
    }
    public static void main(String[] args) throws Exception  {
        if(args.length == 0) {
            println("uzycie: java Zad21_13 <ilosc sekund>");
            return;
        }
        for(int i = 0; i < SIZE; i++)
            exec.execute(new SerialChecker());
    // Stop after n seconds if there’s an argument:
        Integer z = Integer.valueOf(args[0]);
            TimeUnit.SECONDS.sleep(z);
            Zad21_13.stop();
            System.out.println("No duplicates detected during " + z + " seconds");
            System.exit(0);
    }
}

/*
Program sprawdza, czy wiele wątków korzystających ze wspólnego generatora otrzyma unikalne numery seryjne.

Metoda nextSerialNumber() zwiększa pole serialNumber.
Operacja serialNumber++ nie jest atomic, ponieważ składa się z odczytania wartości,
zwiększenia jej i ponownego zapisania. Bez synchronizacji dwa wątki
mogłyby odczytać tą samą wartość i otrzymać identyczny numer seryjny.

Problem został naprawiony przez dodanie synchronized do metody nextSerialNumber().

CircularSet przechowuje ostatnio wygenerowane numery. Jego metody add() i contains()
są zsynchronizowane, aby kilka wątków nie modyfikowało i odczytywało tablicy jednocześnie.
*/