import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10
seconds, then displays its sleep time and exits. Create and run a quantity (given on the
command line) of these tasks.
*/
public class Zad21_6 implements Runnable{
    private static int taskCount = 0;
    private final int id = taskCount++;
    public Zad21_6() {}
    Random rand = new Random();
    @Override
    public void run() {
        //zakres losowania od 1 s do 10 s
        int sleepTime = rand.nextInt(9001) + 1000;
        try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                println("Task# " + id + " spał przez: " + (sleepTime / 1000.0) + "s");
        } catch(InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
    public static void main(String[] args) {
        if(args.length == 0) {
            println("RUN: java Zad21_6 liczbaZadan");
            return;
        }
        //liczba tasków do stworzenia
        int liczbaZadan = Integer.parseInt(args[0]);
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < liczbaZadan; i++)
            exec.execute(new Zad21_6());
        exec.shutdown();
    }
}

/*
W zadaniu każdy task otrzymuje własny numer id. W metodzie run() losowany jest czas od 1000 do 10000ms.
Następnie task zostaje uśpiony za pomocą TimeUnit.MILLISECONDS.sleep(sleepTime);
Po zakończeniu program wyświetla numer tasku oraz czas, jaki spał.

Z pierwszego argumentu command line pobierana jest ilość tworzonych tasków:
java Zad21_6 6   - oznacza uruchomienie 6 tasks

Executor newCachedThreadPool() tworzy nowe wątki w miare potrzeby
i może wykonywać kilka zadań jednocześnie. Z tego powodu taski nie muszą
kończyć się zgodnie z kolejnością ich numerów. Zwykle wcześniej
zakończy się task, który wylosował krótszy czas oczekiwania.
*/