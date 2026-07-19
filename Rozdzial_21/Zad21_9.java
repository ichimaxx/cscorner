import java.util.concurrent.*;
import java.util.*;
/*
Exercise 9: (3) Modify SimplePriorities.java so that a custom ThreadFactory sets
the priorities of the threads.
*/
class NowaFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Random rand = new Random();
        Thread t = new Thread(r);
        int f = rand.nextInt(2);
        switch(f) {
            case 0:
                t.setPriority(Thread.MIN_PRIORITY);
                break;
            case 1:
                t.setPriority(Thread.MAX_PRIORITY);
        }
        return t;
    }
}
public class Zad21_9 implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization


    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        while (true) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new NowaFactory());
        for (int i = 0; i < 5; i++)
            exec.execute(
                    new Zad21_9());
        exec.shutdown();
    }
}
/*
Zadanie modyfikuje SimplePriorities.java dodając customową fabrykę, która sama w sobie ustawia priorytety wątków.
*/