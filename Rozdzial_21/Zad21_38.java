import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 38: (3) Using the approach in CarBuilder.java, model the house-building
story that was given in this chapter.
*/
class Home {
    private final int id;
    //false oznacza, że część nie została jeszcze zamontowana
    private boolean
            zbrojenie = false, szalunki = false, wylaneFundamenty = false, konstrukcja = false, beton = false, kanalizacja = false;
    public Home(int idn) { id = idn; }
    // Empty Home object:
    public Home() { id = -1; }
    public synchronized int getId() { return id; }
    //pracownicy wywołują te metody, aby złożyć konkretne etapy domu
    public synchronized void addZbrojenie() { zbrojenie = true; }
    public synchronized void addSzalunki() {
        szalunki = true;
    }
    public synchronized void addWylanieFundamentow() { wylaneFundamenty = true; }
    public synchronized void addKanalizacja() { kanalizacja = true; }
    public synchronized void addBeton() { beton = true; }
    public synchronized void addKonstrukcja() { konstrukcja = true; }
    public synchronized String toString() {
        return "Home " + id + " [" + " zbrojenie: " + zbrojenie
                + " szalunki: " + szalunki
                + " wylane fundamenty: " + wylaneFundamenty + " kanalizacja: "
                + kanalizacja + " beton: " + beton
                + " konstrukcja: " + konstrukcja + " ]";
    }
}
//kolejka przechowywująca referencje do obiektów Home
class HomeQueue extends LinkedBlockingQueue<Home> {}
//wykopywanie fundamentów
class FoundationBuilder implements Runnable {
    //kolejka, do której trafiają działki z wykopanym fundamentem
    private HomeQueue homeQueue;
    //numer następnego domu
    private int counter = 0;
    public FoundationBuilder(HomeQueue cq) { homeQueue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czas kopania fundamentów
                TimeUnit.MILLISECONDS.sleep(500);
                // tworzy dom z kolejnym numerem
                Home c = new Home(counter++);
                println("Wykopano miejsce pod fundamenty dla " + c);
                //przekazuje dom do pierwszego etapu
                homeQueue.put(c);
            }
        } catch(InterruptedException e) {
            println("Interrupted: Foundation");
        }
        println("FoundationBuilder off");
    }
}
//pierwszy etap montażu: zbrojenie i szalunki
class Ekipa1 implements Runnable {
    //wejście pierwszego etapu i wyjście do drugiego etapu
    private HomeQueue foundationQueue, zbrojenieQueue;
    //dom aktualnie obsługiwany przez tą ekipę
    private Home home;
    //przy barierze spotykają się 3 zadania:
    //ekipa1, szalunkiWorker, zbrojenieWorker
    private CyclicBarrier barrier = new CyclicBarrier(3);
    private WorkerPool workerPool;
    public Ekipa1(HomeQueue cq, HomeQueue bq, WorkerPool rp){
        foundationQueue = cq;
        workerPool = rp;
        zbrojenieQueue = bq;
    }
    //pracownicy pobierają z pomocą tej metody aktualny dom
    public Home home() { return home; }
    //pracownicy używają bariery należącej do tego etapu
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na dom
                home = foundationQueue.take();
                // pobiera z puli dwóch pracowników pierwszego etapu
                workerPool.hire(ZbrojenieWorker.class, this);
                workerPool.hire(SzalunkiWorker.class, this);
                //czeka aż obaj pracownicy zakończą pracę
                barrier.await();
                //przekazuje dom do drugiego etapu montażu
                zbrojenieQueue.put(home);
            }
        } catch(InterruptedException e) {
            println("Exiting Ekipa1 via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        println("Ekipa1 off");
    }
}
//drugi etap montażu: zbrojenie i wylany fundament
class Ekipa2 implements Runnable {
    private HomeQueue zbrojenieQueue, wylanyFundamentQueue;
    private Home home;
    //przy tej barierze spotyka się:
    //ekipa2 i FundamentWorker
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private WorkerPool workerPool;
    public Ekipa2(HomeQueue bq, HomeQueue fq, WorkerPool rp){
        wylanyFundamentQueue = fq;
        workerPool = rp;
        zbrojenieQueue = bq;
    }
    public Home home() { return home; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until chassis is available:
                home = zbrojenieQueue.take();
                // pobiera z puli pracownika drugiego etapu
                workerPool.hire2(FundamentWorker.class, this);
                barrier.await();
                // dom trafia do następnego etapu
                wylanyFundamentQueue.put(home);
            }
        } catch(InterruptedException e) {
            println("Exiting Ekipa2 via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        println("Ekipa2 off");
    }
}
//trzeci etap: kanalizacja
class Ekipa3 implements Runnable {
    private HomeQueue wylanyFundamentQueue, kanalizacjaQueue;
    private Home home;
    //przy tej barierze spotyka się:
    //ekipa3 i KanalizacjaWorker
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private WorkerPool workerPool;
    public Ekipa3(HomeQueue bq, HomeQueue fq, WorkerPool rp){
        kanalizacjaQueue = fq;
        workerPool = rp;
        wylanyFundamentQueue = bq;
    }
    public Home home() { return home; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                home = wylanyFundamentQueue.take();
                workerPool.hire3(KanalizacjaWorker.class, this);
                barrier.await();
                kanalizacjaQueue.put(home);
            }
        } catch(InterruptedException e) {
            println("Exiting Ekipa3 via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        println("Ekipa3 off");
    }
}
class Ekipa4 implements Runnable {
    private HomeQueue kanalizacjaQueue, betonQueue;
    private Home home;
    //przy tej barierze spotyka się:
    //ekipa4 i BetonWorker
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private WorkerPool workerPool;
    public Ekipa4(HomeQueue bq, HomeQueue fq, WorkerPool rp){
        betonQueue = fq;
        workerPool = rp;
        kanalizacjaQueue = bq;
    }
    public Home home() { return home; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                home = kanalizacjaQueue.take();
                workerPool.hire4(BetonWorker.class, this);
                barrier.await();
                betonQueue.put(home);
            }
        } catch(InterruptedException e) {
            println("Exiting Ekipa4 via interrupt");
        } catch(BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        println("Ekipa4 off");
    }
}
class Ekipa5 implements Runnable {
    private HomeQueue betonQueue, konstrukcjaQueue;
    private Home home;
    //przy tej barierze spotyka się:
    //ekipa5 i KonstrukcjaWorker
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private WorkerPool workerPool;
    public Ekipa5(HomeQueue bq, HomeQueue fq, WorkerPool rp){
        konstrukcjaQueue = fq;
        workerPool = rp;
        betonQueue = bq;
    }
    public Home home() { return home; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                home = betonQueue.take();
                workerPool.hire5(KonstrukcjaWorker.class, this);
                barrier.await();
                konstrukcjaQueue.put(home);
            }
        } catch(InterruptedException e) {
            println("Exiting Ekipa5 via interrupt");
        } catch(BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        println("Ekipa5 off");
    }
}
//pobiera ukończone konstrukcje domów i wyświetla stan wszystkich etapów budowy
class Supervisor implements Runnable {
    private HomeQueue homeQueue;
    public Supervisor(HomeQueue cq) { homeQueue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na całkowicie ukończoną konstrukcję domu
                println(homeQueue.take());
            }
        } catch(InterruptedException e) {
            println("Exiting Supervisor via interrupt");
        }
        println("Supervisor off");
    }
}
//wspólna klasa bazowa wszystkich pracowników
abstract class Worker implements Runnable {
    private WorkerPool pool;
    public Worker(WorkerPool p) { pool = p; }
    //pracownik może zostać przypisany do danego etapu
    //w danym momencie tylko jedno z tych pól powinno być różne od null
    protected Ekipa1 ekipa1;
    protected Ekipa2 ekipa2;
    protected Ekipa3 ekipa3;
    protected Ekipa4 ekipa4;
    protected Ekipa5 ekipa5;
    //zwraca barierę etapu, do którego aktualnie należy pracownik
    private CyclicBarrier currentBarrier() {
        if  (ekipa1 != null) {
            return ekipa1.barrier();
        }
        if (ekipa2 != null) {
            return ekipa2.barrier();
        }
        if (ekipa3 != null) {
            return ekipa3.barrier();
        }
        if (ekipa4 != null) {
            return ekipa4.barrier();
        }
        if (ekipa5 != null) {
            return ekipa5.barrier();
        }
        throw new IllegalStateException(
                "Pracownik nie jest przypisany do żadnej ekipy"
        );
    }
    //przypisuje pracownika do pierwszego etapu
    public Worker assignAssembler(Ekipa1 assembler) {
        this.ekipa2 = null;
        this.ekipa1 = assembler;
        this.ekipa3 = null;
        this.ekipa4 = null;
        this.ekipa5 = null;
        return this;
    }
    //przypisuje pracownika do drugiego etapu
    public Worker assignAssembler(Ekipa2 assembler) {
        this.ekipa2 = assembler;
        this.ekipa1 = null;
        this.ekipa3 = null;
        this.ekipa4 = null;
        this.ekipa5 = null;
        return this;
    }
    public Worker assignAssembler(Ekipa3 assembler) {
        this.ekipa2 = null;
        this.ekipa1 = null;
        this.ekipa3 = assembler;
        this.ekipa4 = null;
        this.ekipa5 = null;
        return this;
    }
    public Worker assignAssembler(Ekipa4 assembler) {
        this.ekipa2 = null;
        this.ekipa1 = null;
        this.ekipa3 = null;
        this.ekipa4 = assembler;
        this.ekipa5 = null;
        return this;
    }
    public Worker assignAssembler(Ekipa5 assembler) {
        this.ekipa2 = null;
        this.ekipa1 = null;
        this.ekipa3 = null;
        this.ekipa4 = null;
        this.ekipa5 = assembler;
        return this;
    }
    //false oznacza, że pracownik śpi i czeka w puli
    private boolean engage = false;
    //budzi pracownika po przydzieleniu mu pracy
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    // każdy konkretny pracownik inaczej wykonuje swoją pracę
    abstract protected void performService();
    public void run() {
        try {
            //na początku pracownik trafia do puli i czeka na zatrudnienie
            powerDown(); // Wait until needed
            while(!Thread.interrupted()) {
                //wykonuje swoją konkretną czynność
                performService();
                //czeka na pozostałych pracowników i ekipę swojego etapu
                currentBarrier().await();// Synchronize
                // po wykonaniu zadania wraca do puli i zasypia
                powerDown();
            }
        } catch(InterruptedException e) {
            println("Exiting " + this + " via interrupt");
        } catch(BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        println(this + " off");
    }
    //odłącza pracownika od etapu, zwraca go do puli i usypia
    private synchronized void
    powerDown() throws InterruptedException {
        engage = false;
        //pracownik nie pracuje już dla żadnej ekipy
        ekipa1 = null;
        ekipa2 = null;
        ekipa3 = null;
        ekipa4 = null;
        ekipa5 = null;
        //ponownie staje się dostępny w puli
        pool.release(this);
        //czeka aż WorkerPool ponownie przydzieli mu pracę
        while(engage == false) // Power down
            wait();
    }
    public String toString() { return getClass().getName(); }
}
//montaż zbrojenia pierwszy etap
class ZbrojenieWorker extends Worker {
    public ZbrojenieWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " montaż zbrojenia");
        ekipa1.home().addZbrojenie();
    }
}
//zakładanie szalunków pierwszy etap
class SzalunkiWorker extends Worker {
    public SzalunkiWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " zakładanie szalunków");
        ekipa1.home().addSzalunki();
    }
}
//wylewanie fundamentu drugi etap
class FundamentWorker extends Worker {
    public FundamentWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " wylewa Fundament");
        ekipa2.home().addWylanieFundamentow();
    }
}
//instalacja kanalizacji trzeci etap
class KanalizacjaWorker extends Worker {
    public KanalizacjaWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " instalacja Kanalizacji");
        ekipa3.home().addKanalizacja();
    }
}
//wylewanie betonu, czwarty etap
class BetonWorker extends Worker {
    public BetonWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " betonowa plyta");
        ekipa4.home().addBeton();
    }
}
//montaż konstrukcji domu, ostatni etap
class KonstrukcjaWorker extends Worker {
    public KonstrukcjaWorker(WorkerPool pool) { super(pool); }
    protected void performService() {
        println(this + " montaż konstrukcji domu");
        ekipa5.home().addKonstrukcja();
    }
}
//przechowuje pracowników, którzy aktualnie nie wykonują pracy
class WorkerPool {
    // Quietly prevents identical entries:
    private Set<Worker> pool = new HashSet<Worker>();
    //dodaje wolnego pracownika do puli, i budzi zadania oczekujące w hire()
    public synchronized void add(Worker r) {
        pool.add(r);
        notifyAll();
    }
    //zatrudnia odpowiedni typ pracownika dla pierwszego Assemblera
    public synchronized void
    hire(Class<? extends Worker> workerType, Ekipa1 d)
            throws InterruptedException {
        for(Worker r : pool)
            if(r.getClass().equals(workerType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        //brak pracownika, czeka aż jakiś wróci do puli
        wait();
        //po obudzeniu ponownie szuka właściwego pracownika
        hire(workerType, d);
    }
    //zatrudnia odpowiedni typ pracownika dla ekipy
    public synchronized void
    hire2(Class<? extends Worker> workerType, Ekipa2 d)
            throws InterruptedException {
        for(Worker r : pool)
            if(r.getClass().equals(workerType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait(); // None available
        hire2(workerType, d); // Try again, recursively
    }
    public synchronized void
    hire3(Class<? extends Worker> workerType, Ekipa3 d)
            throws InterruptedException {
        for(Worker r : pool)
            if(r.getClass().equals(workerType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait(); // None available
        hire3(workerType, d); // Try again, recursively
    }
    public synchronized void
    hire4(Class<? extends Worker> workerType, Ekipa4 d)
            throws InterruptedException {
        for(Worker r : pool)
            if(r.getClass().equals(workerType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait(); // None available
        hire4(workerType, d); // Try again, recursively
    }
    public synchronized void
    hire5(Class<? extends Worker> workerType, Ekipa5 d)
            throws InterruptedException {
        for(Worker r : pool)
            if(r.getClass().equals(workerType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait(); // None available
        hire5(workerType, d); // Try again, recursively
    }
    //po zakończeniu pracy pracownik ponownie trafia do puli
    public synchronized void release(Worker r) { add(r); }
}
public class Zad21_38 {
    public static void main(String[] args) throws Exception {
        //sześć kolejek, każda odpowiada innemu stanowi domu
        HomeQueue foundationQueue = new HomeQueue(),
                zbrojenieQueue = new HomeQueue(),
                wylanyFundamentQueue = new HomeQueue(),
                kanalizacjaQueue = new HomeQueue(),
                betonQueue = new HomeQueue(),
                konstrukcjaQueue = new HomeQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        WorkerPool workerPool = new WorkerPool();
        exec.execute(new ZbrojenieWorker(workerPool));
        exec.execute(new SzalunkiWorker(workerPool));
        exec.execute(new FundamentWorker(workerPool));
        exec.execute(new KanalizacjaWorker(workerPool));
        exec.execute(new BetonWorker(workerPool));
        exec.execute(new KonstrukcjaWorker(workerPool));
        exec.execute(new Ekipa1(
                foundationQueue, zbrojenieQueue, workerPool));
        exec.execute(new Ekipa2(
                zbrojenieQueue, wylanyFundamentQueue, workerPool));
        exec.execute(new Ekipa3(
                wylanyFundamentQueue, kanalizacjaQueue, workerPool));
        exec.execute(new Ekipa4(
                kanalizacjaQueue, betonQueue, workerPool));
        exec.execute(new Ekipa5(
                betonQueue, konstrukcjaQueue, workerPool));
        exec.execute(new Supervisor(konstrukcjaQueue));
        // zaczyna się od wykopania fundamentu
        exec.execute(new FoundationBuilder(foundationQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}

/*
Celem ćwiczenia było odwzorowanie procesu budowy domu za pomocą współpracujących zadań,
podobnie jak w przykładzie CarBuilder.

Ten sam obiekt Home przechodzi przez kolejne kolejki reprezentujące następne
etapy budowy. Zbrojenie i szalunki są wykonywane równocześnie,
a kolejne prace rozpoczynają się dopiero po zakończeniu wcześniejszych.

BlockingQueue przekazuje dom pomiędzy etapami, CyclicBarrier synchronizuje ekipę
z jej pracownikami, a WorkerPool przechowuje dostępnych pracowników
i przydziela ich do odpowiednich zadań
*/