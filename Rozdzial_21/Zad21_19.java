import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 19: (4) Modify OrnamentalGarden.java so that it uses interrupt( ).
*/
class Count1 {
    private int count = 0;
    private Random rand = new Random(47);
    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment() {
        int temp = count;
        if(rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }public synchronized int value() { return count; }
}
class Entrance1 implements Runnable {
    private static Count1 count = new Count1();
    private static List<Entrance1> entrances =
            new ArrayList<Entrance1>();
    private int number = 0;
    // Doesn’t need synchronization to read:
    private final int id;
    public Entrance1(int id) {
        this.id = id;
        // Keep this task in a list. Also prevents
        // garbage collection of dead tasks:
        entrances.add(this);
    }
    public void run() {
            try {
                //zmiana flagi while cancaled na while !Thread.currentThread().isInterrupted()
                //jeżeli wątek otrzyma interrupt() poza sleep(), is interrupted() zwróci true
                //pętla zostanie zakończona
                while(!Thread.currentThread().isInterrupted()) {
                    synchronized (this) {
                        ++number;
                    }
                    println(this + " Total: " + count.increment());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch(InterruptedException e) {
                println("sleep interrupted");
            }
        println("Stopping " + this);
    }
    public synchronized int getValue() { return number; }
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
    public static int getTotalCount() {
        return count.value();
    }
    public static int sumEntrances() {
        int sum = 0;
        for(Entrance1 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}
    public class Zad21_19 {
        public static void main(String[] args) throws InterruptedException {
            ExecutorService exec =
                    Executors.newCachedThreadPool();
            for(int i = 0; i < 5; i++)
                exec.execute(new Entrance1(i));
            // Run for a while, then stop and collect the data:
            TimeUnit.SECONDS.sleep(3);
            //shutdownNow() wysyła interrupt() do wszystkich działających wątków na raz
            exec.shutdownNow();
            if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
                println("Some tasks were not terminated!");
            println("Total: " + Entrance1.getTotalCount());
            println("Sum of Entrances: " + Entrance1.sumEntrances());
        }
    }

    /*
    Program modyfikuje OrnamentalGarden, w której własna flaga canceled
    została zastąpiona mechanizmem interrupt().

    Każdy entrance działa jako osobne zadanie i wykonuje pętle tak długo,
    jak jego wątek nie będzie miał flagi interrupt().

    Jeżeli interrupt() pojawi się podczas działania pętli,
    stan przerwania zostanie wykryty przez isInterrupted(), pętla zostanie zakończona.
    Jeśli interrupt() pojawi się podczas sleep(), metoda sleep() wyrzuci wyjątek,
    obsłuży wyjątek i skończy działanie run().

    W sumie celem ćwiczenia było pokazanie jak można zastąpić flagę sterującą,
    mechanizmem interrupt() do zatrzymywania zadań.

    */