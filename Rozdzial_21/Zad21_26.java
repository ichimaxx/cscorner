import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 26: (8) Add a BusBoy class to Restaurant.java. After the meal is delivered,
the WaitPerson should notify the BusBoy to clean up.
*/
class Meal2 {
    private final int orderNum;
    public Meal2(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}
class BusBoy implements Runnable {
    public boolean sprzatniete = false;
    private Zad21_26 restaurant;
    public BusBoy(Zad21_26 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    //busboy czeka, dopóki waitperson nie zgłosi potrzeby sprzątania
                    while(restaurant.waitPerson.done == false)
                        wait(); // ... for WaitPerson to finish eat
                    println("BusBoy cleaned...");
                    //sprzątanie zakończone, busboy może czekać na następne
                    restaurant.waitPerson.done = false;
                    synchronized(restaurant.waitPerson) {
                        //informuje waitperson, że sprzątanie zakończone
                        sprzatniete = true;
                        //budzi waitperson czekającego na zakończenie sprzątania
                        restaurant.waitPerson.notifyAll(); // Ready for another
                    }
                }
            }
        } catch(InterruptedException e) {
            println("BusBoy interrupted");
        }
    }
}
class WaitPerson2 implements Runnable {
    //false - busboy nie ma jeszcze nic do sprzątania
    //true - waitperson zgłosił busboy potrzebe sprzątania
    public boolean done = false;
    private Zad21_26 restaurant;
    public WaitPerson2(Zad21_26 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    //czeka aż chef przygotuje meal
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                println("Waitperson got " + restaurant.meal);
                //zgłasza busboy, że można zrobić sprzątanie
                synchronized(restaurant.busBoy) {
                    done = true;
                    //budzi busboy
                    restaurant.busBoy.notifyAll(); // Ready for another
                }
                synchronized(this) {
                    //waitperson idzie dalej, dopiero gdy busboy skończy sprzątanie
                    while(restaurant.busBoy.sprzatniete == false)
                        wait();
                }
                //zmienia flage do następnego cyklu
                restaurant.busBoy.sprzatniete = false;
                synchronized(restaurant.chef) {
                    //meal obslużony, sprzątanie zakończone bufor meal można opróżnić
                    restaurant.meal = null;

                    //pobudka szefa
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch(InterruptedException e) {
            println("WaitPerson interrupted");
        }
    }
}
class Chef2 implements Runnable {
    private Zad21_26 restaurant;
    private int count = 0;

    public Chef2(Zad21_26 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //czeka dopóki poprzedni meal nie zostanie całkowicie obsłużony
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait(); // ... for the meal to be taken
                    }
                }
                    if (++count == 10) {
                        println("Out of food, closing");
                        restaurant.exec.shutdownNow();
                        return;
                    }
                    println("Order up! ");
                    synchronized (restaurant.waitPerson) {
                        //chef umieszcza nowy meal w buforze
                        restaurant.meal = new Meal2(count);
                        //budzi waitperson czekającego na meal
                        restaurant.waitPerson.notifyAll();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch(InterruptedException e) {
                println("Chef interrupted");
            }
        }
    }
public class Zad21_26 {
    Meal2 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson2 waitPerson = new WaitPerson2(this);
    BusBoy busBoy = new BusBoy(this);
    Chef2 chef = new Chef2(this);
    public Zad21_26() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
    public static void main(String[] args) {
        new Zad21_26();
    }
}

/*
W zadaniu dodano trzeci task - BusBoy.

Chef tworzy meal i budzi waitperson.

Waitperson odbiera Meal, ustawia done = true i przez notifyAll() budzi busboy do sprzątania.

BusBoy czeka przez wait() dopóki done == false.
Po otrzymaniu sygnału sprząta, ustawia done na false, oraz sprzatniete = true i budzi waitperson.

WaitPerson czeka na zakończenie sprzątania. Dopiero gdy sprzątniete = true, opróżnia bufor meal
i budzi Chefa, który może przygotować następny posiłek.


Przebieg programu:

Chef
V
tworzy meal
V
notifyAll(waitperson)

WaitPerson
V
odbiera meal
V
done = true
notifyAll(BusBoy)

BusBoy
V
while(done == false)
wait()
V
sprząta
V
done = false
V
sprzątnięte = true
notifyAll(WaitPerson)

WaitPerson
V
while(sprzątnięte == fals)
wait()
V
sprzątanie zakonczone
V
meal = null
notifyAll(chef)

Chef może zrobić następny meal


*/