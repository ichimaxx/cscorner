import java.util.concurrent.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 42: (7) Modify WaxOMatic.java so that it implements active objects.
*/
public class Zad21_42 {
    //active object posiada własny pojedynczy wątek oraz kolejkę wiadomości
    //wszystkie operacje wysłane do executora są wykonywane po kolei przez jeden wątek
    private ExecutorService ex =
            Executors.newSingleThreadExecutor();
    //pole jest prywatne, ponieważ tylko aktywny obiekt powinien kontrolować swój własny stan
    private boolean waxOn = false;
    //handler nakładający wosk,
    // metoda nie zmienia pola waxOn bezpośrednio w wątku, który ją wywołał
    //zamiast tego przekazuje wiadomość jako Runnable do kolejki aktywnego obiektu
    public void
    waxed() {
        //Lambda jest skróconym zapisem obiektu Runnable.
        //Wiadomość trafia do kolejki aktywnego obiektu
        //i zostanie wykonana przez jego własny wątek.
        ex.execute(() -> {
            if(!waxOn) {
                println("WaxOn!");
                waxOn = true;
            }
        });
    }
    //handler polerujący samochód
    //wiadomość trafia do kolejki tak samo jak waxed() i zostanie wykonana przez exexutor Zad21_42
    public void
    buffed() {
        //Lambda jest skróconym zapisem obiektu Runnable.
        //Wiadomość trafia do kolejki aktywnego obiektu
        //i zostanie wykonana przez jego własny wątek.
        ex.execute(() -> {
            if(waxOn) {
                println("WaxOff!");
                waxOn = false;
            }
        });
    }
    //shutdown executora
    private void shutdown() {
        ex.shutdown();
    }
    public static void main(String[] args) {
        Zad21_42 car = new Zad21_42();
        //main wysyła wiadomości do aktywnego obiektu
        //Nie wykonuje bezpośrednio nakładania, ani polerowania.
        //wiadomości trafiają do kolejki executora.
        for (int i = 0; i < 10; i++) {
            car.waxed();
            car.buffed();
        }
        car.shutdown();
    }

}

/*
Zad21_42 jest aktywnym obiektem, ponieważ posiada własny newSingleThreadExecutor(),
kolejkę wiadomości, oraz samodzielnie kontroluje pole waxOn.

Metody waxed() i buffed() nie wykonują operacji bezpośrednio.
Przekazują zadania jako lambdy Runnable do executora.

Pojedynczy wątek executora wykonuje wiadomości po kolei.
waxed() -> buffed() -> waxed() -> buffed()

Dzieki temu tylko jeden wątek zmienia pole waxOn i nie trzeba używać
synchronized, wait() ani notifyAll().
*/
