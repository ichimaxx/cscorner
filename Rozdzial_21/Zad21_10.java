import myutils.Generator;
import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (4) Modify Exercise 5 following the example of the ThreadMethod class,
so that runTask( ) takes an argument of the number of Fibonacci numbers to sum, and each
time you call runTask( ) it returns the Future produced by the call to submit( ).
*/
public class Zad21_10 implements Generator<Integer>, Callable<Integer> {
    private int count = 0;
    private int zlicz = 0;
    //Jeden wspólny ExecutorService dla wszystkich runTask()
    private static ExecutorService exec = Executors.newCachedThreadPool();
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    private int n = 0;
    public Zad21_10(int n) {
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
    //Przekazuje nowe zadanie do wspólnego executora
    //zwraca Future otrzymany z metody submit()
    public static Future<Integer> runTask(int f) {
        return exec.submit(new Zad21_10(f));
    }
    public static void main(String[] args) {
        ArrayList<Future<Integer>> results =
                new ArrayList<Future<Integer>>();
        for(int i = 0; i < 5; i++) {
            results.add(runTask(8));
            results.add(runTask(3));
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
Program tworzy jeden wspólny ExecutorService, który wykonuje zadania Callable<Integer>.
runTask(int f) przyjmuje ile liczb ciągu trzeba zsumować.
Za każdym wywołaniem tworzy nowy obiekt Zad21_10, przekazuje go do metody submit() i zwraca
otrzymany Future<Integer>
Każde zadanie ma własne pola count, zlicz i n, dlatego obliczenia nie wpływają na siebie.
Future.get() czeka, aż dane zadanie zakończy obliczenia, i zwraca ich wynik.
*/