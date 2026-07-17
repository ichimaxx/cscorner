import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;
/*
Exercise 3: (1) Repeat Exercise 1 using the different types of executors shown in this
section.
*/
public class Zad21_3 implements Runnable {
    public Zad21_3 () {
        println("STARTUP MESSAGE");
    }
    @Override
    public void run() {
        println("message1");
        Thread.yield();
        println("message2");
        Thread.yield();
        println("message3");
        Thread.yield();
        println("SHUTDOWN");
    }
    public static void main(String[] args) throws InterruptedException {
        //Tworzy nowe wątki w miarę potrzebny i ponownie wykorzystuje wątki, które są już wolne.
        ExecutorService exec1 = Executors.newCachedThreadPool();
        println("\nExecutor newCachedThreadPool(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_3 z = new Zad21_3();
            exec1.execute(z);
        }
        exec1.shutdown();
        exec1.awaitTermination(1, TimeUnit.MINUTES);
        //Używa maksymalnie pięciu wątków roboczych. Pozostałe zadania oczekują w kolejce.
        ExecutorService exec2 = Executors.newFixedThreadPool(5);
        println("\nExecutor newFixedThreadPool(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_3 k = new Zad21_3();
            exec2.execute(k);
        }
        exec2.shutdown();
        exec2.awaitTermination(1, TimeUnit.MINUTES);
        //Używa jednego wątku roboczego. Zadania wykonują się kolejno, jedno po drugim.
        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        println("\nExecutor newSingleThreadExecutor(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_3 f = new Zad21_3();
            exec3.execute(f);
        }
        exec3.shutdown();
        exec3.awaitTermination(1, TimeUnit.MINUTES);
    }
}

/*
Zadanie testuje trzy przypadki użycia obiektu Executor:
newCachedThreadPool();
Tworzy nowe wątki w miarę potrzeby i ponownie wykorzystuje
wcześniej utworzone wątki, jeżeli są dostępne.

newFixedThreadPool(5);
Używa maksymalnie pięciu wątków roboczych. Jeżeli przesłano więcej niż 5 zadań,
pozostałe zadania oczekują w kolejce.

newSingleThreadExecutor()
Używa jednego wątku roboczego, dlatego przesłane do niego zadania
wykonują się kolejno, jedno po drugim. Dzięki temu zadania tego executora nie wykonują się równocześnie.

Metoda shutdown() nie przerywa już przesłanych zadań i nie czeka na ich zakończenie.
Powoduje tylko, że executor nie przyjmuje nowych zadań.

awaitTermination(1, TimeUnit.MINUTES) zatrzymuje wątek main maksymalnie na jedną minutę
i oczekuje na zakończenie wszystkich zadań danego executora. Dzięki temu następny executor nie jest
uruchamiany przed zakończeniem poprzedniego.
 */