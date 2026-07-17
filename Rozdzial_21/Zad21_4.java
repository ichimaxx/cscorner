import myutils.Generator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static myutils.Skrocenie_Print.println;

/*
Exercise 4: (1) Repeat Exercise 2 using the different types of executors shown in this
section.
*/
public class Zad21_4 implements Generator<Integer>,Runnable {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    private int n = 0;
    public Zad21_4(int n) {
        this.n = n;
    }
    @Override
    public void run() {
        for(int i = 0; i < n; i++) {
            System.out.print(next() + " ");
            Thread.yield();
        }
    }
    public static void main(String[] args) throws InterruptedException{
        //Tworzy nowe wątki w miarę potrzebny i ponownie wykorzystuje wątki, które są już wolne.
        ExecutorService exec1 = Executors.newCachedThreadPool();
        println("\nExecutor newCachedThreadPool(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_4 z = new Zad21_4(7);
            exec1.execute(z);
        }
        exec1.shutdown();
        exec1.awaitTermination(1, TimeUnit.MINUTES);
        //Używa maksymalnie pięciu wątków roboczych. Pozostałe zadania oczekują w kolejce.
        ExecutorService exec2 = Executors.newFixedThreadPool(5);
        println("\nExecutor newFixedThreadPool(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_4 k = new Zad21_4(7);
            exec2.execute(k);
        }
        exec2.shutdown();
        exec2.awaitTermination(1, TimeUnit.MINUTES);
        //Używa jednego wątku roboczego. Zadania wykonują się kolejno, jedno po drugim.
        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        println("\nExecutor newSingleThreadExecutor(): \n");
        for(int i = 0; i < 7; i++) {
            Zad21_4 f = new Zad21_4(7);
            exec3.execute(f);
        }
        exec3.shutdown();
        exec3.awaitTermination(1, TimeUnit.MINUTES);
    }
}

/*
Zadanie uruchamia siedem osobnych generatorów ciągu Fibonacciego
za pomocą trzech typów ExecutorService.

Każdy obiekt Zad21_4 ma własne pole count, dlatego każdy task generuje osobny ciąg siedmiu liczb.
*/