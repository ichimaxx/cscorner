import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.util.concurrent.locks.*;

/*
Exercise 39: (6) Does FastSimulation.java make reasonable assumptions? Try
changing the array to ordinary ints instead of AtomicInteger and using Lock mutexes.
Compare the performance between the two versions of the program.
*/

//wersja 1:
//każda komórka tablicy jest osobnym obiektem AtomicInteger
//aktualizacja odbywa się z użyciem compareAndSet
class FastSimulation {
    //zliczanie udanych i nieudanych aktualizacji
    protected static AtomicInteger nieudanecompareadset = new AtomicInteger();
    protected static AtomicInteger udanecompareadset = new AtomicInteger();
    //tablica ma 100000 wierszy i 30 genów w każdym wierszu
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    //każda komórka jest osobnym AtomicInteger, można je aktualizować atomowo bez blokowania całej tablicy
    static final AtomicInteger[][] GRID =
            new AtomicInteger[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);
    static class Evolver implements Runnable {
        public void run() {
            while(!Thread.interrupted()) {
                // losowany element, który zostanie użyty
                int element = rand.nextInt(N_ELEMENTS);
                //aktualizacja wszystkich genów wybranego elementu
                for(int i = 0; i < N_GENES; i++) {
                    //wyznaczamy poprzedniego sąsiada,
                    //dla elementu 0 poprzednikiem jest ostatni element,
                    //więc tablica jest traktowana jako zamknięty okrąg
                    int previous = element - 1;
                    if(previous < 0) previous = N_ELEMENTS - 1;
                    //wyznacza następnego sąsiada,
                    //dla ostatniego elementu następny jest element 0
                    int next = element + 1;
                    if(next >= N_ELEMENTS) next = 0;
                    //zapamiętuje aktualną wartość komórki
                    //ta wartość później będzie wartością oczekiwaną przekazaną do compareAndset()
                    int oldvalue = GRID[element][i].get();
                    // oblicza średnią
                    //obecnej wartości, wartości poprzedniego sąsiada i następnego
                    int newvalue = oldvalue +
                            GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3; // Average the three values
                    //CompareAndSet(CAS) zapisuje newValue tylko wtedy,
                    //gdy obecna wartość nadal jest równa oldValue
                    //zwraca true po udanym zapisie i false po nieudanym
                    boolean updated = GRID[element][i]
                            .compareAndSet(oldvalue, newvalue);
                    if(updated) {
                        //nowa wartość została zapisana
                        udanecompareadset.incrementAndGet();
                    } else  {
                        //inny wątek zmienił tą samą komórkę,
                        //zgodnie z przykładem, nie ma drugiej próby, wynik odrzucony
                        nieudanecompareadset.incrementAndGet();
                    }
                }
            }
        }
    }
}

//wersja2
//tablica zawiera zwykłe wartości int
//bezpieczeństwo zapewnia wspólny ReentrantLock()
//z chronioną sekcją pracuje tylko jeden wątek
public class Zad21_39 {
    //liczba aktualizacji zakończonych w wersji z Lock
    //nie ma tutaj nieudanych CAS, ponieważ po zdobyciu zamka
    //zapis zawsze zostanie wykonany
    private static AtomicInteger udaneaktualizacje = new AtomicInteger();
    //jeden wspólny zamek dla wszystkich wątków i całej tablicy
    static final Lock lock = new ReentrantLock();
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    //zwykła tablica zamiast AtomicInteger
    static final int[][] GRID =
            new int[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);

    static class Evolver2 implements Runnable {
        public void run() {
            while(!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for(int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if(previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if(next >= N_ELEMENTS) next = 0;
                    //sekcja krytyczna
                    lock.lock();
                    try {
                        int oldvalue = GRID[element][i];
                        // Perform some kind of modeling calculation:
                        int newvalue = oldvalue +
                                GRID[previous][i] + GRID[next][i];
                        newvalue /= 3; // Average the three values
                            GRID[element][i] = newvalue;
                        udaneaktualizacje.incrementAndGet();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
    //porównanie obu wersji
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        //wypełnia tablice AtomicInteger losowymi wartościami
        for(int i = 0; i < N_ELEMENTS; i++)
            for(int j = 0; j < N_GENES; j++)
                FastSimulation.GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
        //uruchamia 50 zadań evolver
        for(int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new FastSimulation.Evolver());
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
        exec.awaitTermination(3, TimeUnit.SECONDS);
        println("Ilość nieudanych operacji CompareAndSet (CAS) FastSimulation.java: " + FastSimulation.nieudanecompareadset);
        println("Ilość udanych operacji CompareAndSet (CAS) FastSimulation.java: " + FastSimulation.udanecompareadset);

        ExecutorService exec2 = Executors.newCachedThreadPool();
        for(int i = 0; i < N_ELEMENTS; i++)
            for(int j = 0; j < N_GENES; j++)
                GRID[i][j] = rand.nextInt(1000);
        for(int i = 0; i < N_EVOLVERS; i++)
            exec2.execute(new Evolver2());
        TimeUnit.SECONDS.sleep(5);
        exec2.shutdownNow();
        exec2.awaitTermination(3, TimeUnit.SECONDS);
        println("Udane aktualizacje w Zad21_39.java: " + udaneaktualizacje);
    }
}
/*
Zadanie porównuje wydajność dwóch sposobów zabezpieczenia wspólnej tablicy.
Pierwsza wersja używa AtomicInteger i compareAndSet(), dzięki czemu wątki mogą równolegle
aktualizować różne komórki, ale niektóre aktualizacje mogą zostać odrzucone przy kolizji.
Druga wersja używa zwykłych int oraz jednego wspólnego ReentrantLock, dlatego każda
aktualizacja jest poprawie wykonana, ale wątki muszą czekać na zwolnienie zamka.
Porównywana jest liczba udanych aktualizacji wykonanych przez obie wersje w ciągu 5 sekund.

Ilość nieudanych operacji CompareAndSet (CAS) FastSimulation.java: 76
Ilość udanych operacji CompareAndSet (CAS) FastSimulation.java: 463526324
Udane aktualizacje w Zad21_39.java: 162621360

Wersja z AtomicInteger i compareAndSet() w tym teście jest wydajniejsza,
ponieważ wątki mogą równolegle aktualizować różne komórki tablicy.
Wersja z jednym wspólnym Lock ogranicza współbieżność, ponieważ w danym
momencie tylko jeden wątek może wykonywać aktualizacje.
Wadą CAS jest możliwość odrzucenia aktualizacji przy kolizji, natomiast
Lock gwarantuje wykonanie każdej aktualizacji, kosztem mniejszej wydajności.
*/