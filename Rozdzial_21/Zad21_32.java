import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 32: (7) Use a CountDownLatch to solve the problem of correlating the
results from the Entrances in OrnamentalGarden.java. Remove the unnecessary code
from the new version of the example.
*/
class Entrance implements Runnable {
    //każde entrance korzysta z tego samego latcha
    private CountDownLatch latch;
    //lista wszysdtkichj wejsc potrzebna do koncowego zsumowania wyników
    private static List<Entrance> entrances =
            new ArrayList<Entrance>();
    //licznik osób dla jednego wejścia
    private int number = 0;
    // Doesn’t need synchronization to read:
    private final int id;
    //zapewnia widoczność zmiany dla wszystkich wątków
    private static volatile boolean canceled = false;
    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
        // zachowuje wszystkie obiekty Entrance do późniejszego zsumowania
        entrances.add(this);
    }

    public void run() {
        try {
            while (!canceled) {
                //każdy wątek zwiększa tylko własny licznik
                    ++number;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    println("sleep interrupted");
                }
            }
            println("Stopping " + this);
        } finally {
            //informuje main(), że to Entrance zakończyło pracę
            //każde zadanie wykonuje countDown() dokładnie jeden raz
            latch.countDown();
        }
    }

    public int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
//końcowa suma wyników wszystkich wejść
    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}
public class Zad21_32 {
    //liczba uruchamianych wejść i początkowa wartość latcha
    static final int SIZE = 100;
    public static void main(String[] args) throws Exception {
        //Latch zaczyna od 100
        //każde zakończone entrance zmniejszy go o 1
        CountDownLatch latch = new CountDownLatch(SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        //uruchomienie wszystkich wejść
        for(int i = 0; i < SIZE; i++) {
            exec.execute(new Entrance(latch, i));
        }
        //zlicza osoby przez 3 sekundy
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        //main czeka aż wszystkie entrance wykonają countDown()
        //i licznik latcha osiągnie 0
        latch.await();
        exec.shutdown();
        //wyniki są sumowane dopiero po zakończeniu wszystkich zadań
        println("Sum of Entrances: " + Entrance.sumEntrances());
    }
}

/*
CountDownLatch służy tutaj do poinformowania wątku main,
że wszystkie obiekty Entrance zakończyły już pracę.

Latch otrzymuje początkową wartość równą liczbie wejść:
new CountDownLatch(100)

Każde Entrance podczas kończenia wykonuje dokładnie raz:
latch.countDown()

Każdy Entrance posiada własny licznik number.
Po wywołaniu cancel() wejścia kończą pracę i wykonują latch.countDown()
w bloku finally

main zatrzymuje się w latch.await() dopóki wszystkie 100 obiektów Entrance nie zakończy działania.
Dzięki temu main nie zsumuje wyników za wcześnie, gdy
część obiektów Entrance nadal zwiększa swoje liczniki.

Zadaniem CountDownLatch jest wyłącznie skoordynowanie zakończenia zadań,
każde Entrance zgłasza "skończyłem", a main czeka na wszystkie.
Jest on też jednorazowy, po osiągnięciu 0 nie można go ponownie ustawić na 100,
trzeba stworzyć nowy obiekt.


Entrance zakończone
|
| countDown()
V
CountDownLatch: 100 > 99 > ... > 0
|
| await() przestaje blokować
V
main sumuje wyniki

*/

