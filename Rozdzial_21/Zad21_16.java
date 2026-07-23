import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/*
Exercise 16: (1) Modify Exercise 15 to use explicit Lock objects.
*/
class SyncTest2 {
    private final Lock lock = new ReentrantLock();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void f() {
        lock.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("f()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void g() {
        lock.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("g()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void h() {
        lock.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("h()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void ff() {
        lock.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("ff()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void gg() {
        lock1.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("gg()");
                Thread.yield();
            }
        } finally {
            lock1.unlock();
        }
    }

    public void hh() {
        lock2.lock();
        try {
            for (int i = 0; i < 50; i++) {
                println("hh()");
                Thread.yield();
            }
        } finally {
            lock2.unlock();
        }
    }
}
public class Zad21_16 {
    public static void main(String[] args) throws InterruptedException {
        final SyncTest2 ds = new SyncTest2();
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
Metody f(), g() i h() używają wspólnego ReentrantLock().
Z tego powodu tylko jeden wątek może w danym momencie wykonywać jedną z tych metod.
Pozostałe wątki muszą czekać na zwolnienie wspólnej blokady.

Metody ff() gg() i hh() używają różnych ReentrantLock().
Ich blokady są od siebie niezależne, dlatego wszystkie trzy metody mogą wykonywać
się w tym samym czasie.
 */