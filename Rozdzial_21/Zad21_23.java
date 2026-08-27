import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (7) Demonstrate that WaxOMatic.java works successfully when you use
notify( ) instead of notifyAll( ).
*/

class Car_2 {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notify();
    }
    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notify();
    }
    public synchronized void waitForWaxing()
            throws InterruptedException {
        while(waxOn == false)
            wait();
    }
    public synchronized void waitForBuffing()
            throws InterruptedException {
        while(waxOn == true)
            wait();
    }
}
class WaxOn implements Runnable {
    private Car_2 car;
    public WaxOn(Car_2 c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                println("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Wax On task");
    }
}
class WaxOff implements Runnable {private Car_2 car;
    public WaxOff(Car_2 c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch(InterruptedException e) {
            println("Exiting via interrupt");
        }
        println("Ending Wax Off task");
    }
}
public class Zad21_23 {
    public static void main(String[] args) throws Exception {
        Car_2 car = new Car_2();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

/*
Notify() budzi dowolnie wybrany JEDEN wątek spośród tych, które czekają przez wait()
na tym samym monitorze(synchronized(this)). Nie ma gwarancji, który zostanie wybrany.

W przypadku notifyAll() wszyscy waiterzy na danym monitorze zostają obudzeni.

W WaxOMatic notify() wystarcza, ponieważ WaxOn i WaxOff pracują naprzemiennie
i w danym momencie najwyżej jeden z nich czeka na monitorze obiektu Car.
Tylko jeden z nich ma odpalony wait(), więc notify() ma do wyboru tylko ten jeden wątek.
*/