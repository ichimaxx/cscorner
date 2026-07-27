import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 24: (1) Solve a single-producer, single-consumer problem using wait( ) and
notifyAll( ). The producer must not overflow the receiver’s buffer, which can happen if the
producer is faster than the consumer. If the consumer is faster than the producer, then it
must not read the same data more than once. Do not assume anything about the relative
speeds of the producer or consumer.
*/
class Stuff {
    private final int orderNum;
    public Stuff(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Stuff " + orderNum; }
}
class Consumer implements Runnable {
    private Zad21_24 base;
    public Consumer(Zad21_24 r) { base = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(base.stuff == null)
                        wait(); // ... for the chef to produce a meal
                }
                println("Consumer got " + base.stuff);
                synchronized(base.prod) {
                    base.stuff = null;
                    base.prod.notifyAll(); // Ready for another
                }
            }
        } catch(InterruptedException e) {
            println("Consumer interrupted");
        }
    }
}
class Producer implements Runnable {
    private Zad21_24 base;
    private int count = 0;
    public Producer(Zad21_24 r) { base = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    //czeka aż poprzedni stuff zniknie z bufora, żeby mógł stworzyć nowy
                    while(base.stuff != null)
                        wait(); // ... for the meal to be taken
                }
                println("Order up! ");
                synchronized(base.cons) {
                    base.stuff = new Stuff(++count);
                    base.cons.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            println("Producer interrupted");
        }
    }
}
public class Zad21_24 {
    //bufor, o pojemności jednego obiektu
    Stuff stuff;
    ExecutorService exec = Executors.newCachedThreadPool();
    Consumer cons = new Consumer(this);
    Producer prod = new Producer(this);
    public Zad21_24() throws InterruptedException {
        exec.execute(prod);
        exec.execute(cons);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
    public static void main(String[] args) throws InterruptedException {
        new Zad21_24();
    }
}

/*
Bufor mieści jeden obiekt Stuff.

Producer czeka przez wait(), gdy bufor jest pełny,
więc nie może nadpisać nieodebranych danych.

Consumer czeka przez wait(), gdy bufor jest pusty,
więc nie może odczytać tych samych danych ponownie.

Po zmianie stanu bufora odpowiedni task jest budzony przez notifyAll()

Celem zadania jest zsynchronizowanie Producera i Consumera tak,
aby się nie wyprzedzały wzajemnie:

Producer szybszy, czeka, aż Consumer opróżni bufor.
Consumer szybszy, czeka, aż Producer umieści nowe dane.

Dzięki temu żaden task nie może "wyprzedzić" drugiego i spowodować utraty albo ponownego odczytania danych.
*/