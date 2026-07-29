import java.util.concurrent.*;
import java.io.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 28: (3) Modify TestBlockingQueues.java by adding a new task that places
LiftOff on the BlockingQueue, instead of doing it in main( ).
*/
//pojedyncze zadanie(task)
class LiftOff4 implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;
 public LiftOff4() {}
 public LiftOff4(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}
//pobiera LiftOff z kolejki i wykonuje je jeden po drugim
class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff4> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff4> queue) {
        rockets = queue;
    }
    //tą metodę w zadaniu zastępuje Wkladacz
    public void add(LiftOff4 lo) {
        try {
            rockets.put(lo);
        } catch(InterruptedException e) {
            println("Interrupted during put()");
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //take() pobiera i usuwa element z kolejki

                //jeżeli pusta, wątek jest zablokowany do czasu pojawienia się elementu
                LiftOff4 rocket = rockets.take();
                //wywołuje run() a nie start(), dzięki temu wszystkie LiftOff są uruchamiane z tego samego wątku,
                //przez co, wykonują się po kolei
                rocket.run(); // Use this thread
            }
        } catch(InterruptedException e) {
            println("Waking from take()");
        }
        println("Exiting LiftOffRunner");
    }
}
class Wkladacz implements Runnable {
    private BlockingQueue<LiftOff4> queue;
    public Wkladacz(BlockingQueue<LiftOff4> queue) {
        this.queue = queue;
    }
    public void run() {
        //tworzy i wkłada do kolejki dokładnie 5 rakiet(LiftOff)
        try {
            for (int i = 0; i < 5; i++) {
                //put() może zablokować ten wątek,
                //ArrayBlockingQuaue, gdy kolejka jest pełna
                //SynchronousQueue blokuje put() do momentu,
                //kiedy inny wątek nie będzie gotowy odebrać elementu przez take()
                queue.put(new LiftOff4(5));
            }
        } catch (InterruptedException e) {
            println("Przerwano wkładanie");
        }
    }
}
public class Zad21_28 {
    //czeka na naciśnięcie Enter
    static void getkey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        } catch(java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void getkey(String message) {
        println(message);
        getkey();
    }
    static void test(String msg, BlockingQueue<LiftOff4> queue) {
        println("");
        print(msg);
        println("");
        //pobiera rakiety z queue
        LiftOffRunner runner = new LiftOffRunner(queue);
        //wkłada rakiety do queue
        Wkladacz runner2 = new Wkladacz(queue);
        //każde zadanie osobny wątek
        Thread t = new Thread(runner);
        Thread t2 = new Thread(runner2);
        t.start();
        t2.start();
        //czeka aż zostanie naciśniety Enter
        getkey("\nPress 'Enter' (" + msg + ")");
        //przerywanie obu zadań
        t.interrupt();
        t2.interrupt();
        println("Finished " + msg + " test");
    }
    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff4>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff4>(3));
        test("SynchronousQueue", // Size of 0, nie magazynuje elementu, jedynie przekazuje go bezpośrednio
                //pomiędzy put() i take()
                new SynchronousQueue<LiftOff4>());
    }
}

/*
Wkladacz tworzy obiekty LiftOff i jest odpowiedzialny za put().

LiftOffRunner pobiera elementy przez take() i wykonuje ich run().

LinkedBlockingQueue - praktycznie nieograniczona kolejka, można włożyć szybko wszystkie elementy,
nawet jeżeli wątek ich nie przetworzył.

ArrayBlockingQueue - kolejka o stałej pojemności 3, gdy znajdują się w niej trzy elementy, następne put()
czeka na zwolnienie miejsca przez take().

SynchronousQueue - kolejka o pojemności 0, nie magazynuje elementów. Każde put() musi bezpośrednio
spotkać się z take() wykonywanym przez konsumenta.
*/