import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 22: (4) Create an example of a busy wait. One task sleeps for a while and then
sets a flag to true. The second task watches that flag inside a while loop (this is the busy
wait) and when the flag becomes true, sets it back to false and reports the change to the
console. Note how much wasted time the program spends inside the busy wait, and create a
second version of the program that uses wait( ) instead of the busy wait.
*/
class Pierwsza1zBusyWait implements Runnable {
    //volatile zmiana flagi wykonana przez jeden wątek jest widoczna dla drugiego wątku
    public volatile static boolean flaga = false;
    public synchronized void run() {
        try {
            println("Pierwszy task śpi przez 2 sekundy...");
            TimeUnit.MILLISECONDS.sleep(2005);
            flaga = true;
            println("Flaga ustawiona na true\n");
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Pierwsza1zBusyWait On task\n");
    }

}
class Druga1zBusyWait implements Runnable {
    public synchronized void run() {
        long licznik = 0;
        long start = System.nanoTime();
        // BUSY WAIT - wątek cały czas sprawdza flagę i zużywa CPU
        while (!Pierwsza1zBusyWait.flaga) {
            licznik++;
        }
        Pierwsza1zBusyWait.flaga = false;
        println("Wykryto zmianę flagi -> reset na false");
        println("Całkowita liczba wykonanych inkrementacji: " + licznik);
        long end = System.nanoTime();
        println("Czas:" + (end - start) / 1_000_000.0 + "ms\n");
        println("Ending Druga1zBusyWait On task\n");
    }
}
class Pierwsza2zWait implements Runnable {
    public volatile static boolean flaga = false;
    public  void run() {
        try {
            println("Pierwszy task śpi przez 2 sekundy...");
            TimeUnit.MILLISECONDS.sleep(2005);
            //wymagane jest posiadanie monitora tego obiektu przed notifyAll()
            synchronized(this) {
                flaga = true;
                println("Flaga ustawiona na true -> notifyAll()\n");
                notifyAll();
            }
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Pierwsza2zWait On task\n");
    }

}
class Druga2zWait implements Runnable {
    private Pierwsza2zWait z;
    public Druga2zWait(Runnable r) {
        this.z = (Pierwsza2zWait) r;
    }
    public synchronized void run () {
        try {
            long licznik = 0;
            long start = System.nanoTime();
            synchronized (z) {
                while (!z.flaga) {
                    println("Flaga false -> task przechodzi w wait()\n");
                    //wait() zawiesza task i jednocześnie zwalcza monitor z
                    z.wait();
                    //po notifyAll() task musi ponownie zdobyć monitor z,
                    //zanim wait() może się zakończyć
                    licznik++;
                }
                println("Task obudzony, flaga = true");
                z.flaga = false;
            }
            println("Flaga zmieniona na false");
            println("Całkowita liczba wykonanych inkrementacji: " + licznik);
            long end = System.nanoTime();
            println("Czas:" + (end - start) / 1_000_000.0 + "ms\n");
        } catch (InterruptedException e) {
            println("Exiting via Interrupt");
        }
        println("Ending Druga2zWait On task\n");
    }
}
public class Zad21_22 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        println("\nTEST Z busy wait: \n");
        Pierwsza1zBusyWait t1 = new Pierwsza1zBusyWait();
        Druga1zBusyWait t2 = new Druga1zBusyWait();
        exec.execute(t1);
        exec.execute(t2);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Użycie exec.shutdownNow()");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(5);

        ExecutorService exec2 = Executors.newCachedThreadPool();
        println("\n\n\nTEST Z wait() : \n");
        Pierwsza2zWait tt1 = new Pierwsza2zWait();
        exec2.execute(new Druga2zWait(tt1));
        exec2.execute(tt1);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Użycie exec2.shutdownNow()");
        exec2.shutdownNow();
        TimeUnit.SECONDS.sleep(5);
    }
}

/*
Busy wait ciągle sprawdza flagę i niepotrzebnie zużywa CPU.

Wersja z wait() zawiesza task i zwalnia monitor podczas oczekiwania.
Po notifyAll() task ponownie zdobywa monitor i sprawdza warunek.
*/