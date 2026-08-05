import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 41: (6) Add a message handler to ActiveObjectDemo.java that has no return
value, and call this within main( ).
*/

public class Zad21_41 {
    //Executor posiada tylko jeden wątek roboczy.
    //Wszystkie przesłanego zadania trafiają do kolejki i są wykonywane po kolei.
    private ExecutorService ex =
            Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);
    // symuluje czasochłonne obliczenia
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(
                    100 + rand.nextInt(factor));
        } catch(InterruptedException e) {
            println("sleep() interrupted");
        }
    }
    //handler wiadomości zwracający wynik Integer
    //metoda nie wykonuje obliczeń bezpośrednio, tylko przekazuje Callable
    //do kolejki executora i zwraca Future<Integer>
    public Future<Integer>
    calculateInt(final int x, final int y) {
        return ex.submit(new Callable<Integer>() {
            //ta metoda zostanie później wykonana przez pojedynczy wątek executora
            public Integer call() {
                println("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }
    //handel wiadomości bez wartości zwrotnej(cel zadania)
    //execute() przyjmuje runnable dlatego nie zwraca Future ani wyniku wykonywanego zadania
    public void
    pokazWiadomosc(String z) {
        //Lambda jest skróconym zapisem obiektu Runnable.
        //Wiadomość trafia do kolejki aktywnego obiektu
        //i zostanie wykonana przez jego własny wątek.
        ex.execute(() -> {
            pause(500);
            println(z);
        });
    }
    public Future<Float>
    calculateFloat(final float x, final float y) {
        return ex.submit(new Callable<Float>() {
            public Float call() {
                println("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }
    public void shutdown() { ex.shutdown(); }
    public static void main(String[] args) {
        Zad21_41 d1 = new Zad21_41();
        // Prevents ConcurrentModificationException:
        //lista Future<Integer> i Future<Float>
        //znak ? oznacza, że dokładny typ wyniku konkretnego future może być różny (wildcard)
        List<Future<?>> results =
                new CopyOnWriteArrayList<Future<?>>();
        //wysłanie pięciu wiadomości calculateFloat()
        //metody natychmiast zwracają Future, a obliczenia czekają w kolejce executora
        for(float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(d1.calculateFloat(f, f));
        for(int i = 0; i < 5; i++)
            results.add(d1.calculateInt(i, i));
        //5 wiadomości bez wartości zwrotnej
        for(int i = 0; i < 5; i++)
            d1.pokazWiadomosc("HELLO");
        //To pojawia się wcześniej niż obliczenia, ponieważ wcześniejsze wywołania
        //umieściły tylko zadania w kolejce.
        println("All asynch calls made");
        //pętla działa do momentu aż lista zawiera niewykonane zadania
        while(results.size() > 0) {
            for(Future<?> f : results)
                //sprawdza czy zadanie zostało zakończone bez blokowania aktualnego wątku
                if(f.isDone()) {
                    try {
                        print(f.get());
                    } catch(Exception e) {
                        throw new RuntimeException(e);
                    }
                    //usuwa obsłużony wynik. CopyOnWriteArrayList pozwala na
                    //usunięcie elementu podczas przechodzenia pętlą for-each bez
                    //ConcurrentModificationException
                    results.remove(f);
                }
        }
        d1.shutdown();
    }
}

/*
W klasie ActiveObjectDemo.java(Zad21_41.java) dodano dodatkowy handler wiadomości pokazWiadomosc(), który
nie zwraca wyniku. Zadanie jest przekazywane do ExecutorService za pomocą execute() jako Runnable.

calculateInt() i calculateFloat() korzystają z Callable, ponieważ zwracają wyniki przez obiekty Future.
Wszystkie zadania trafiają do kolejki newSingleThreadExecutor() i są wykonywane po kolei przez jeden wątek.

CopyOnWriteArrayList przechowuje wyniki Future i pozwala bezpiecznie usuwać zakończone zadania podczas iteracji.

Aktywny obiekt pozwala asynchronicznie wysyłać zadania do kolejki.
Callable stosuje się gdy potrzeba wyniku, a Runnable gdy zadanie ma tylko wykonać określoną czynność.
*/