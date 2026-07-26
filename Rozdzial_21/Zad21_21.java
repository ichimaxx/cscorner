import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 21: (2) Create two Runnables, one with a run( ) that starts and calls wait( ).
The second class should capture the reference of the first Runnable object. Its run( )
should call notifyAll( ) for the first task after some number of seconds have passed so that
the first task can display a message. Test your classes using an Executor.
*/
class Pierwsza implements Runnable {
    public synchronized void run() {
        try {
            while(!Thread.interrupted()) {
                println("Start Pierwsza On! ");
                TimeUnit.MILLISECONDS.sleep(2005);
                //wait() zawiesza task i ZWALNIA monitor obiektu Pierwsza
                wait();
                //po notifyAll() task musi ponownie zdobyć monitor
                //zanim może kontynuować od tego miejsca
                println("Pierwsza została obudzona przez notifyAll()...");
                TimeUnit.MILLISECONDS.sleep(2005);
            }
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Pierwsza On task");
    }
}

class Druga implements Runnable {
    //referencja do TEGO SAMEGO obiektu Pierwsza, który wykonuje wait()
    private Pierwsza z;
    public Druga(Runnable z) {
        this.z = (Pierwsza) z;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                println("Start Druga On! ");
                TimeUnit.MILLISECONDS.sleep(2005);
                //Trzeba zdobyć monitor z przed użyciem notifyAll()
                synchronized (z) {
                    //budzi task oczekujący przez wait() na obiekcie z
                    z.notifyAll();
                }
            }
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Druga On task");
    }
    }

public class Zad21_21 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Pierwsza t1 = new Pierwsza();
        exec.execute(new Druga(t1));
        exec.execute(t1);
        TimeUnit.SECONDS.sleep(15);
        System.out.println("Użycie exec.shutdownNow()");
        exec.shutdownNow();
    }
}

/*
Klasa Pierwsza wykonuje wait(), co zwalnia jej monitor.
Druga zdobywa ten sam monitor i wykonuje notifyAll().
Pierwsza po obudzeniu ponownie zdobywa monitor i kontynuuje działanie.

wait() zwalnia monitor, sleep() go nie zwalnia.
*/