import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 20: (1) Modify CachedThreadPool.java so that all tasks receive an
interrupt( ) before they are completed.
*/
class LiftOff3 implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff3() {
    }

    public LiftOff3(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        //ODLICZAJ dopóki zadanie nie zostanie przerwane i countDown sie nie skończył
        while (!Thread.currentThread().isInterrupted() && countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
        if (Thread.currentThread().isInterrupted()) {
            println("Thread " + id + " interrupted!!");
        }
    }
}
public class Zad21_20 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff3(30000));
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
}

/*
Zmodyfikowano class CachedThreadPool tak, aby wszystkie uruchomione zadania
otrzymały interrupt() przed zakończeniem odliczania.

Ponieważ Thread.yield() nie wyrzuca InterruptedException,
sprawdzono status przerwania za pomocą isInterrupted().
*/