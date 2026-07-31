import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 31: (8) Change DeadlockingDiningPhilosophers.java so that when a
philosopher is done with its chopsticks, it drops them into a bin. When a philosopher wants
to eat, it takes the next two available chopsticks from the bin. Does this eliminate the
possibility of deadlock? Can you reintroduce deadlock by simply reducing the number of
available chopsticks?
*/
//wspólny pojemnik z dostępnymi pałeczkami
class Bin {
    BlockingQueue<Chopstick> chopsticks;
    public Bin(int size) {
        chopsticks = new ArrayBlockingQueue<>(size);
        //wypełnienie pojemnika pałeczkami
        for (int i = 0; i < size; i++) {
            chopsticks.add(new Chopstick());
        }
    }
    //pobiera pałeczkę lub czeka, gdy pojemnik jest pusty
    public Chopstick take() throws InterruptedException {
        return chopsticks.take();
    }
    //zwraca pałeczkę do pojemnika
    public void put(Chopstick chopstick) throws InterruptedException {
        chopsticks.put(chopstick);
    }
}
//pałeczka nie potrzebuje już własnego taken,
//ponieważ jej dostępność kontroluje BlockingQueue
class Chopstick {
}
//wszyscy filozofowie korzystają z tego samego pojemnika
class Philosopher implements Runnable {
    private Bin bin;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
        if(ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFactor * 250));
    }
    public Philosopher(Bin bin,
                       int ident, int ponder){
        this.bin = bin;
        id = ident;
        ponderFactor = ponder;
    }
    //zmienne są tworzone dla każdego posiłku od nowa,
    //null oznacza, że pałeczka nie została jeszcze pobrana
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Chopstick pierwsza = null;
                Chopstick druga = null;
                try {
                    println(this + " " + "thinking");
                    pause();
                    // Philosopher becomes hungry
                    //filozof bierze dwie dowolne pałeczki
                    println(this + " " + "grabbing first");
                    pierwsza = bin.take();
                    println(this + " " + "grabbing second");
                    druga = bin.take();
                    println(this + " " + "eating");
                    pause();
                } finally {
                    //zwraca dokładnie te same pałeczki, które pobrano
                    //finally wykona się również przy przerwaniu wątku
                    if (pierwsza != null) {
                        try {
                            bin.put(pierwsza);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (druga != null) {
                        try {
                            bin.put(druga);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            println(this + " " + "exiting via interrupt");
        }
    }
        public String toString() { return "Philosopher " + id; }
}
public class Zad21_31 {
    public static void main(String[] args) throws Exception {
        //czas losowego myślenia i jedzenia
        int ponder = 5;
        if(args.length > 0)
            ponder = Integer.parseInt(args[0]);
        //liczba filozofów
        int size = 5;
        //liczba pałeczek może być inna niż liczba filozofów
        int iloscPaleczek = 6;
        if(args.length > 1)
            size = Integer.parseInt(args[1]);
        if(args.length > 2)
            iloscPaleczek = Integer.parseInt(args[2]);
        ExecutorService exec = Executors.newCachedThreadPool();
        //jeden wspólny pojemnik dla filozofów
        Bin bin = new Bin(iloscPaleczek);

        for(int i = 0; i < size; i++)
            exec.execute(new Philosopher(
                    bin, i, ponder));
        if(args.length == 4 && args[3].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
/*
W oryginalnym programie każdy filozof miał pałeczkę prawą i lewą.
W tej wersji wszystkie pałeczki znajdują się we wspólnym Bin.
Do kontrolowania dostępu do pałeczek użyto BlockingQueue.

Filozof bierze dwie następne dostępne pałeczki:

Bin
|take() pierwsza
|take() druga
V
Filozof je
|put() pierwsza
|put() druga
V
Bin

=

Nawet z jednym pojemnikiem dla wszystkich deadlock jest możliwy,
ale tylko w sytuacji, gdy pałeczek jest mniej niż filozofów lub jest ich tyle samo, dla przykładu,
jeżeli każdy z filozofów skończy z jedną pałeczką i pustym koszem, wszyscy będą czekać
na drugą pałeczkę i nie zwrócą pierwszej.
*/