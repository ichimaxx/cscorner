import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 25: (1) In the Chef class in Restaurant.java, return from run( ) after
calling shutdownNow( ) and observe the difference in behavior.
*/
class Meal1 {
    private final int orderNum;
    public Meal1(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}
class WaitPerson1 implements Runnable {
    private Zad21_25 restaurant;
    public WaitPerson1(Zad21_25 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                println("Waitperson got " + restaurant.meal);
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch(InterruptedException e) {
            println("WaitPerson interrupted");
        }
    }
}
class Chef1 implements Runnable {
    private Zad21_25 restaurant;
    private int count = 0;
    public Chef1(Zad21_25 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                println("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal1(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            println("Chef interrupted");
        }
    }
}
public class Zad21_25 {
    Meal1 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson1 waitPerson = new WaitPerson1(this);
    Chef1 chef = new Chef1(this);
    public Zad21_25() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
    public static void main(String[] args) {
        new Zad21_25();
    }
}

/*
Z pomocą return, Chef run() kończy się od razu po komentarzu "Out of food, closing",
Dzieje się tak, ponieważ shutdownNow() wysyła tylko interrupt(), a to nie kończy wykonywanego kodu
dokładnie na tej instrukcji, run() idzie dalej, aż do sleep(), gdzie interrupt wywołuje Exception.
*/