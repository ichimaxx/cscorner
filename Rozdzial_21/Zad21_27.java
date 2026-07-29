import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
import java.util.concurrent.locks.*;
/*
Exercise 27: (2) Modify Restaurant.java to use explicit Lock and Condition objects.
*/
class Meal2 {
    private final int orderNum;
    public Meal2(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}
class WaitPerson2 implements Runnable {
    private Zad21_27 restaurant;
    public WaitPerson2(Zad21_27 r) { restaurant = r; }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                restaurant.lock.lock();
                try {
                    while (restaurant.meal == null)
                        restaurant.condition.await();  // ... for the chef to produce a meal
                } finally {
                    restaurant.lock.unlock();
                }
                println("Waitperson got " + restaurant.meal);
                restaurant.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.condition.signalAll(); // Ready for another
                } finally {
                    restaurant.lock.unlock();
                }
            }
        } catch(InterruptedException e) {
            println("WaitPerson interrupted");
        }
    }
}
class Chef2 implements Runnable {
    private Zad21_27 restaurant;
    private int count = 0;
    public Chef2(Zad21_27 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                restaurant.lock.lock();
                try {
                    while(restaurant.meal != null)
                        restaurant.condition.await(); // ... for the meal to be taken
                } finally {
                    restaurant.lock.unlock();
                }
                if(++count == 10) {
                    println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                println("Order up! ");
                restaurant.lock.lock();
                try {
                    restaurant.meal = new Meal2(count);
                    restaurant.condition.signalAll();
                } finally {
                    restaurant.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            println("Chef interrupted");
        }
    }
}
public class Zad21_27 {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Meal2 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson2 waitPerson = new WaitPerson2(this);
    Chef2 chef = new Chef2(this);
    public Zad21_27() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
    public static void main(String[] args) {
        new Zad21_27();
    }
}

/*
W zadaniu zamieniono synchronized na Lock + condition.

Każdy obiekt używany jako monitor posiada jeden wspólny zbiór wątków oczekujących przez wait().

Natomiast przy jednym Lock, można utworzyć kilka osobnych Condition:
Condition grupa1 = lock.newCondition();
Condition grupa2 = lock.newCondition();
Wtedy można obudzić tylko odpowiednią grupę bez budzenia innych wątków.

W obu przypadkach przy signal() i notify() budzą tylko jeden wątek bez gwarancji konkretnego,
Obudzony wątek musi najpierw zdobyć monitor(synchronized) albo Lock.
Po obudzeniu sprawdza się warunek w while.
 */