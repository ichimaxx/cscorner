import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 29: (8) Modify ToastOMatic.java to create peanut butter and jelly on toast
sandwiches using two separate assembly lines (one for peanut butter, the second for jelly,
then merging the two lines).
*/

class Toast {
    //stany pojedynczej kromki
    public enum Status { DRY, JELLED, PEANUTBUTTERED }
    //nowy utworzony tost
    private Status status = Status.DRY;
    private final int id;
    public Toast(int idn) { id = idn; }
    //zmiana stanu toast
    public void pbutter() { status = Status.PEANUTBUTTERED; }
    //zmiana stanu toast
    public void jelly() { status = Status.JELLED; }
    public Status getStatus() { return status; }
    public int getId() { return id; }
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}
//gotowa kanapka złożona z dwóch różnych kromek
//obie muszą mieć ten sam nr id
class Sandwich {
    private Toast peanutToast;
    private Toast jellyToast;
    public Sandwich(Toast peanutToast, Toast jellyToast) {
        this.peanutToast = peanutToast;
        this.jellyToast = jellyToast;
    }
    //za id przyjmowana jest id kromki z masłem orzechowym
    //później isCorrect() sprawdzi czy druga(jelly) ma ten sam numer
    public int getId() { return peanutToast.getId(); }
    //sprawdzenie czy kanapka została prawidłowo złożona
    public boolean isCorrect() {
        return peanutToast.getId() == jellyToast.getId() &&
                peanutToast.getStatus() == Toast.Status.PEANUTBUTTERED &&
                jellyToast.getStatus() == Toast.Status.JELLED;
    }
    public String toString() {
        return "Sandwich " + getId() + ": [" + peanutToast + "] and [" + jellyToast + "]";
    }
}
class SandwichQueue extends LinkedBlockingQueue<Sandwich> {}
class ToastQueue extends LinkedBlockingQueue<Toast> {}
class Toaster implements Runnable {
    private ToastQueue toastQueue;
    //osobny licznik dla lini produkcyjnej
    private int count = 0;
    //seed 47 daje powtarzalne opóźnienia
    private Random rand = new Random(47);
    public Toaster(ToastQueue tq) { toastQueue = tq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(
                        100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                println(t);
                // Insert into queue
                //put() może blokować wątek, gdyby kolejka miała ograniczoną pojemność i była pełna
                toastQueue.put(t);
            }
        } catch(InterruptedException e) {
            println("Toaster interrupted"); }
        println("Toaster off");
    }
}
//drugi toster
class Toaster2 implements Runnable {
    private ToastQueue toastQueue2;
    private int count = 0;
    private Random rand = new Random(47);
    public Toaster2(ToastQueue tq) { toastQueue2 = tq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(
                        100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                println(t);
                // Insert into queue
                toastQueue2.put(t);
            }
        } catch(InterruptedException e) {
            println("Toaster2 interrupted"); }
        println("Toaster2 off");
    }
}
// Apply butter to toast:
class PeanutButterer implements Runnable {
    private ToastQueue toastQueue, butteredQueue;
    public PeanutButterer(ToastQueue dry, ToastQueue buttered) {
        toastQueue = dry;
        butteredQueue = buttered;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                //take() pobiera i usuwa następny tost.
                //gdy kolejka jest pusta, wątek czeka aż toster utworzy nową kromkę
                Toast t = toastQueue.take();
                t.pbutter();
                println(t);
                butteredQueue.put(t);
            }
        } catch(InterruptedException e) {
            println("Butterer interrupted");
        }
        println("Butterer off");
    }
}
//druga linia obróbki
class Jeller implements Runnable {
    private ToastQueue toastQueue2, jelledQueue;
    public Jeller(ToastQueue dry, ToastQueue jelled) {
        toastQueue2 = dry;
        jelledQueue = jelled;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = toastQueue2.take();
                //smarowanie
                t.jelly();
                println(t);
                jelledQueue.put(t);
            }
        } catch(InterruptedException e) {
            println("Jeller interrupted");
        }
        println("Jeller off");
    }
}
//łączy dwie linie produkcyjne
//pobiera jedną kromkę z jelly i jedną z masłem orzechowym i tworzy z nich Sandwich
class Merger implements Runnable {
    private ToastQueue jelledQueue, butteredQueue;
    //kolejka gotowych kanapek
    private SandwichQueue finishedQueue;
    public Merger(ToastQueue jelled, ToastQueue buttered, SandwichQueue finished) {
        jelledQueue = jelled;
        butteredQueue = buttered;
        finishedQueue = finished;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //merger czeka, aż obie linie dostarczą po jednej przygotowanej kromce
                Toast jellyToast = jelledQueue.take();
                Toast peanutToast = butteredQueue.take();
                //połączenie dwóch kromek
                Sandwich sandwich = new Sandwich(peanutToast, jellyToast);
                println(sandwich);
                //przekazanie do Eater
                finishedQueue.put(sandwich);
            }
        } catch(InterruptedException e) {
            println("Merger interrupted"); }
        println("Merger off");
    }
}
// Consume the toast:
//sprawdza kolejność numerów i poprawność obu kromek
class Eater implements Runnable {
    private SandwichQueue finishedQueue;
    private int counter = 0;
    public Eater(SandwichQueue finished) {
        finishedQueue = finished;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Sandwich t = finishedQueue.take(); // Verify that the toast is coming in order,
                // and that all pieces are getting jammed:
                //bład występuje gdy:
                //kanapki nie przychodzą kolejno lub jedna z kromek ma niewłaściwy stan, albo mają różne numery
                if(t.getId() != counter++ ||
                        !t.isCorrect()) {
                    println(">>>> Error: " + t);
                    System.exit(1);
                } else
                    println("Chomp! " + t);
            }
        } catch(InterruptedException e) {
            println("Eater interrupted");
        }
        println("Eater off");
    }
}
public class Zad21_29 {
    public static void main(String[] args) throws Exception {
        ToastQueue toastQueue2 = new ToastQueue(),
                toastQueue = new ToastQueue(),
                jelledQueue = new ToastQueue(),
                butteredQueue = new ToastQueue();
        SandwichQueue finishedQueue = new SandwichQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(toastQueue));
        exec.execute(new Toaster2(toastQueue2));
        exec.execute(new PeanutButterer(toastQueue, butteredQueue));
        exec.execute(new Jeller(toastQueue2, jelledQueue));
        exec.execute(new Merger(jelledQueue, butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        //produkuje kanapki przez 5 sekund
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}

/*
PIERWSZA LINIA:

Toaster
    |
    | put()
    v
toastQueue
    |
    | take()
    v
PeanutButterer
    |
    | pbutter()
    v
butteredQueue
                         \
                          \
                           Merger
                          /    |
                         /     | new Sandwich(...)
                               v
DRUGA LINIA:             finishedQueue
                               |
Toaster2                       | take()
    |                          v
    | put()                  Eater
    v
toastQueue2
    |
    | take()
    v
Jeller
    |
    | jelly()
    v
jelledQueue

Wszystkie etapy synchronizowane są przez BlockingQueue.
*/