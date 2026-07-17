import myutils.Generator;
import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of
all the Fibonacci numbers. Create several tasks and display the results.
*/
public class Zad21_5 implements Generator<Integer>, Callable<Integer> {
    private int count = 0;
    private int zlicz = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    private int n = 0;
    public Zad21_5(int n) {
        this.n = n;
    }
    @Override
    public Integer call() {
        for(int i = 0; i < n; i++) {
            zlicz += next();
            Thread.yield();
        }
        return zlicz;
    }
    public static void main(String[] args) {
        ArrayList<Future<Integer>> results =
                new ArrayList<Future<Integer>>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            results.add(exec.submit(new Zad21_5(8)));
        }
        exec.shutdown();
        int i = 1;
        for(Future<Integer> fs : results)
            try {
                println("FIBO " + i);
                // get() blocks until completion:
                System.out.println(fs.get());
                i++;
            } catch(InterruptedException e) {
                System.out.println(e);
                return;
            } catch(ExecutionException e) {
                System.out.println(e);
            }
    }
}
/*
Zadanie dodaje 8 liczb Fibonacciego w pięciu zadaniach i je sumuje.
Wynik wychodzi 54.

Zadanie wykonuje interfejs Callable<Integer>, ponieważ zadanie ma zwrócić wynik typu Integer.

Metoda submit() przekazuje obiekt Callable do ExecutorService i zwraca obiekt Future<Integer>.
Future reprezentuje wynik, który może nie być jeszcze gotowy.

Metoda get() pobiera wynik zadania. Jeśli zadanie jeszcze się nie zakończyło,
get() zatrzymuje wątek main i czeka na wynik.

Metoda shutdown() powoduje, że executor nie przyjmuje nowych zadań,
ale wszystkie wcześniejsze zadania zostaną wykonane.
*/