import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 12: (3) Repair AtomicityTest.java using the synchronized keyword. Can
you demonstrate that it is now correct?
*/
public class Zad21_12  implements Runnable {
    private int i = 0;
    private volatile boolean runner = true;
    //po dodaniu synchronized getValue() nie może odczytać i pomiędzy pierwszym a drugim i++
    public synchronized int getValue() { return i; }
    private synchronized void evenIncrement() { i++; i++; }
    public void run() {
        while(runner)
            evenIncrement();
    }
    public void stop() {
        runner = false;
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Zad21_12 at = new Zad21_12();
        exec.execute(at);
        boolean flag = true;
        for(int i = 0; i < 123123; i++) {
            int val = at.getValue();
            if(val % 2 != 0) {
                System.out.println("Odczytano nieparzystą wartość..." + val);
                flag = false;
                break;
            } else {
                println(val);
            }
        }
        //zatrzymuje zadanie i wyłącza przyjmowanie zadań do executora
        at.stop();
        exec.shutdown();
        if(flag) {
            println("Wykonano pomyślnie...");
        }
    }
}

/*
Synchronizacja obu metod na tym samym obiekcie uniemożliwia
odczytanie zmiennej pomiędzy dwoma operacjami i++.
Przeprowadzono 123123 próby, które nie wykrywają wartości nieparzystej.
*/