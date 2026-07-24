import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;
/*
Exercise 18: (2) Create a non-task class with a method that calls sleep( ) for a long
interval. Create a task that calls the method in the non-task class. In main( ), start the task,
then call interrupt( ) to terminate it. Make sure that the task shuts down safely.
*/
//non-task class z metodą która woła sleep() na 100 sekund
class LongSleeper {
    public static void longSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(100);
    }
}
//task który woła metodę w non-task class
public class Zad21_18 implements Runnable {
    @Override
    public void run() {
        try {
            LongSleeper.longSleep();
        } catch (InterruptedException e) {
            println("LongSleeper ŚPI...\nINTERRUPTED EXCEPTION...");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // main startuje task i woła interrupt() przez f.cancel(true)
        ExecutorService exec =
                Executors.newCachedThreadPool();
        Future<?> f = exec.submit(new Zad21_18());
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
    }
}
/*
Klasa implementuje Runnable i wywołuje metodę longSleep().
W main() zadanie jest uruchamiane za pomocą submit(), dzięki czemu otrzymujemy obiekt Future
reprezentujący konkretne zadanie.

Po sekundzie wywoływane jest cancel(true), które próbuje przerwać wątek.
Ponieważ sleep() jest operacją przerywalną, przerwanie powoduje wyrzucenie Interrupted Exception.

Przykład pokazuje, że interrupt() nie zatrzymuje wątku bezpośrednio, ale wysyła żądanie przerwania,
na które sleep() reaguje poprzez InterruptedException.
 */