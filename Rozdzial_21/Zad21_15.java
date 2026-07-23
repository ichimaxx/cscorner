import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;
/*
Exercise 15: (1) Create a class with three methods containing critical sections that all
synchronize on the same object. Create multiple tasks to demonstrate that only one of these
methods can run at a time. Now modify the methods so that each one synchronizes on a
different object and show that all three methods can be running at once.
*/
class SyncTest {
    private Object syncObject = new Object();
    private Object ssyncObject = new Object();
    private Object sssyncObject = new Object();

    public void f() {
        synchronized (syncObject) {
            for (int i = 0; i < 50; i++) {
                println("f()");
                Thread.yield();
            }
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 50; i++) {
                println("g()");
                Thread.yield();
            }
        }
    }

    public void h() {
        synchronized (syncObject) {
            for (int i = 0; i < 50; i++) {
                println("h()");
                Thread.yield();
            }
        }
    }

    public void ff() {
        synchronized (syncObject) {
            for (int i = 0; i < 50; i++) {
                println("ff()");
                Thread.yield();
            }
        }
    }

    public void gg() {
        synchronized (ssyncObject) {
            for (int i = 0; i < 50; i++) {
                println("gg()");
                Thread.yield();
            }
        }
    }

    public void hh() {
        synchronized (sssyncObject) {
            for (int i = 0; i < 50; i++) {
                println("hh()");
                Thread.yield();
            }
        }
    }
}
public class Zad21_15 {
    public static void main(String[] args) throws InterruptedException {
        final SyncTest ds = new SyncTest();
        println("\nTEST: WSZYSTKIE METODY ZSYNCHRONIZOWANE Z TYM SAMYM OBIEKTEM: \n");
        new Thread(() -> ds.f()).start();
        new Thread(() -> ds.g()).start();
        new Thread(() -> ds.h()).start();
        TimeUnit.MILLISECONDS.sleep(3000);
        println("\nTEST: KAŻDA METODA ZSYNCHRONIZOWANA Z INNYM OBIEKTEM: \n");
        new Thread(() -> ds.ff()).start();
        new Thread(() -> ds.gg()).start();
        new Thread(() -> ds.hh()).start();

    }
}

/*
Program pokazuje działanie sekcji krytycznych.
Metody f(), g() i h() synchronizują się na wspólnym obiekcie syncObject.
Z tego powodu tylko jeden wątek może w danym momencie wykonywać jedną z tych metod.
Pozostałe wątki muszą czekać na zwolnienie wspólnej blokady.

Metody ff() gg() i hh() synchronizują się na trzech różnych obiektach.
Ich blokady są od siebie niezależne, dlatego wszystkie trzy metody mogą wykonywać
się w tym samym czasie.
 */