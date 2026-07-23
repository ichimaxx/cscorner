import static myutils.Skrocenie_Print.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.*;
/*
Exercise 14: (4) Demonstrate that java.util.Timer scales to large numbers by creating
a program that generates many Timer objects that perform some simple task when the
timeout completes.
*/
public class Zad21_14 {
    private static final AtomicInteger k =
            new AtomicInteger();

    public static void main(String[] args) {
        //pętla for tysiąca Timerów
        for (int i = 0; i < 1000; i++) {
        Timer timer = new Timer();
        //anonimowa klasa TimerTask() w timer.schedule()
            //metoda timer.schedule() planuje wykonanie wskazanego zadania po określonym czasie
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                        //inkrementacja na polu Atomic
                        println("Timer ran " + k.incrementAndGet());
                        timer.cancel();
                }
            }, 1000);
        }
    }
}

/*
Zadanie atomowo zwiększa wspólny licznik AtomicInteger i wypisuje
informacje o swoim uruchomieniu. AtomicInteger zapewnia poprawne zwiększenie licznika,
nawet gdy wiele wątków wykonuje zadania jednocześnie.
Po wykonaniu zadania każdy Timer zostaje anulowany za pomocą metody cancel().

*/