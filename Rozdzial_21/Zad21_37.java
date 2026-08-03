import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 37: (2) Modify CarBuilder.java to add another stage to the car-building
process, whereby you add the exhaust system, body, and fenders. As with the second stage,
assume these processes can be performed simultaneously by robots.
*/
//samochód przechodzący przez kolejne etapy produkcji
class Car {
    private final int id;
    //false oznacza, że część nie została jeszcze zamontowana
    private boolean
            engine = false, driveTrain = false, wheels = false, exhaust = false, body = false, fenders = false;
    public Car(int idn) { id = idn; }
    // Empty Car object:
    public Car() { id = -1; }
    public synchronized int getId() { return id; }
    //roboty wywołują te metody, aby zamontować konkretne części
    public synchronized void addEngine() { engine = true; }
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    public synchronized void addWheels() { wheels = true; }
    public synchronized void addFenders() { fenders = true; }
    public synchronized void addBody() { body = true; }
    public synchronized void addExhaust() { exhaust = true; }
    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine
                + " driveTrain: " + driveTrain
                + " wheels: " + wheels + " exhaust: "
                + exhaust + " body: " + body
                + " fenders: " + fenders + " ]";
    }
}
//kolejka przechowywująca referencje do obiektów Car
class CarQueue extends LinkedBlockingQueue<Car> {}
//tworzy podstawowy samochód bez zamontowanych części
class ChassisBuilder implements Runnable {
    //kolejka, do której trafiają nowe podwozia
    private CarQueue carQueue;
    //numer następnego samochodu
    private int counter = 0;
    public ChassisBuilder(CarQueue cq) { carQueue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czas tworzenia podwozia
                TimeUnit.MILLISECONDS.sleep(500);
                // tworzy samochód z kolejnym numerem
                Car c = new Car(counter++);
                println("ChassisBuilder created " + c);
                //przekazuje samochód do pierwszego etapu montażu
                carQueue.put(c);
            }
        } catch(InterruptedException e) {
            println("Interrupted: ChassisBuilder");
        }
        println("ChassisBuilder off");
    }
}
//pierwszy etap montażu: silnik, napęd i koła
class Assembler implements Runnable {
    //wejście pierwszego etapu i wyjście do drugiego etapu
    private CarQueue chassisQueue, bodyQueue;
    //samochód aktualnie obsługiwany przez ten assembler
    private Car car;
    //przy barierze spotykają się 4 zadania:
    //assembler, engineRobot, DriveTrainRobot, WheelRobot
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assembler(CarQueue cq, CarQueue bq, RobotPool rp){
        chassisQueue = cq;
        robotPool = rp;
        bodyQueue = bq;
    }
    //roboty pobierają z pomocą tej metody aktualny samochód
    public Car car() { return car; }
    //roboty używają bariery należącej do tego etapu
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na samochód utworzony przez ChassisBuilder
                car = chassisQueue.take();
                // pobiera z puli trzy roboty pierwszego etapu
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                //czeka aż wszystkie trzy roboty zakończą pracę
                barrier.await();
                //przekazuje samochód do drugiego etapu montażu
                bodyQueue.put(car);
            }
        } catch(InterruptedException e) {
            println("Exiting Assembler via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        println("Assembler off");
    }
}
//drugi etap montażu: karoseria, wydech i błotniki
class Assembler2 implements Runnable {
    private CarQueue bodyQueue, finishingQueue;
    private Car car;
    //przy tej barierze spotyka się:
    //assembler2, BodyRobot, ExhaustRobot, FendersRobot
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assembler2(CarQueue bq, CarQueue fq, RobotPool rp){
        finishingQueue = fq;
        robotPool = rp;
        bodyQueue = bq;
    }
    public Car car() { return car; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until chassis is available:
                car = bodyQueue.take();
                // Hire robots to perform work:
                robotPool.hire2(BodyRobot.class, this);
                robotPool.hire2(ExhaustRobot.class, this);
                robotPool.hire2(FendersRobot.class, this);
                barrier.await(); // Until the robots finish
                // gotowy samochód trafia do kolejki końcowej
                finishingQueue.put(car);
            }
        } catch(InterruptedException e) {
            println("Exiting Assembler via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        println("Assembler off");
    }
}
//pobiera gotowe samochody i wyświetla ich stan
class Reporter implements Runnable {
    private CarQueue carQueue;
    public Reporter(CarQueue cq) { carQueue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na całkowicie ukończony samochód
                println(carQueue.take());
            }
        } catch(InterruptedException e) {
            println("Exiting Reporter via interrupt");
        }
        println("Reporter off");
    }
}
//wspólna klasa bazowa wszystkich robotów
abstract class Robot implements Runnable {
    private RobotPool pool;
    public Robot(RobotPool p) { pool = p; }
    //robot może zostać przypisany do pierwszego albo drugiego etapu.
    //w danym momencie tylko jedno z tych pól powinno być różne od null
    protected Assembler assembler;
    protected Assembler2 assembler2;
    //zwraca barierę etapu, do którego aktualnie należy robot
    private CyclicBarrier currentBarrier() {
        if  (assembler != null) {
            return assembler.barrier();
        }
        if (assembler2 != null) {
            return assembler2.barrier();
        }
        throw new IllegalStateException(
                "Robot nie jest przypisany do żadnego Assemblera"
        );
    }
    //przypisuje robota do pierwszego etapu
    public Robot assignAssembler(Assembler assembler) {
        this.assembler2 = null;
        this.assembler = assembler;
        return this;
    }
    //przypisuje robota do drugiego etapu
    public Robot assignAssembler(Assembler2 assembler) {
        this.assembler2 = assembler;
        this.assembler = null;
        return this;
    }
    //false oznacza, że robot śpi i czeka w puli
    private boolean engage = false;
    //budzi robota po przydzieleniu mu pracy
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    // każdy konkretny robot inaczej wykonuje swoją pracę
    abstract protected void performService();
    public void run() {
        try {
            //na początku robot trafia do puli i czeka na zatrudnienie
            powerDown(); // Wait until needed
            while(!Thread.interrupted()) {
                //wykonuje swoją konkretną czynność
            performService();
            //czeka na pozostałe roboty i assembler swojego etapu
            currentBarrier().await();// Synchronize
            // po wykonaniu zadania wraca do puli i zasypia
            powerDown();
        }
    } catch(InterruptedException e) {
            println("Exiting " + this + " via interrupt");
    } catch(BrokenBarrierException e) {
        // This one we want to know about
        throw new RuntimeException(e);
    }
        println(this + " off");
}
//odłącza robota od etapu, zwraca go do puli i usypia
private synchronized void
powerDown() throws InterruptedException {
    engage = false;
    //robot nie pracuje już dla żadnego assemblera
    assembler = null;
    assembler2 = null;// Disconnect from the Assembler
    //ponownie staje się dostępny w puli
    pool.release(this);
    //czeka aż RobotPool ponownie przydzieli mu pracę
    while(engage == false) // Power down
        wait();
}
public String toString() { return getClass().getName(); }
}
//składanie silnika
class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing engine");
        assembler.car().addEngine();
    }
}
//składanie układu napędowego
class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
//montaż kół
class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}
//montaż błotników, drugi etap
class FendersRobot extends Robot {
    public FendersRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing Fenders");
        assembler2.car().addFenders();
    }
}
//montaż karoserii, drugi etap
class BodyRobot extends Robot {
    public BodyRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing Body");
        assembler2.car().addBody();
    }
}
//montaż wydechu, drugi etap
class ExhaustRobot extends Robot {
    public ExhaustRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        println(this + " installing Exhaust");
        assembler2.car().addExhaust();
    }
}
//przechowuje roboty które aktualnie nie wykonują pracy
class RobotPool {
    // Quietly prevents identical entries:
    private Set<Robot> pool = new HashSet<Robot>();
    //dodaje wolnego robota do puli, i budzi zadania oczekujące w hire()
    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }
    //zatrudnia odpowiedni typ robota dla pierwszego Assemblera
    public synchronized void
    hire(Class<? extends Robot> robotType, Assembler d)
            throws InterruptedException {
        for(Robot r : pool)
            if(r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Power it up to do the task
                return;
            }
        //brak robota, czeka aż jakiś wróci do puli
        wait();
        //po obudzeniu ponownie szuka właściwego robota
        hire(robotType, d);
    }
    //zatrudnia odpowiedni typ robota dla drugiego Assemblera
    public synchronized void
    hire2(Class<? extends Robot> robotType, Assembler2 d)
            throws InterruptedException {
        for(Robot r : pool)
            if(r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Power it up to do the task
                return;
            }
        wait(); // None available
        hire2(robotType, d); // Try again, recursively
    }
    //po zakończeniu pracy robot ponownie trafia do puli
    public synchronized void release(Robot r) { add(r); }
}
public class Zad21_37 {
    public static void main(String[] args) throws Exception {
        //trzy kolejki, każda odpowiada innemu stanowi samochodu
        //bez części, po pierwszym etapie, po dwóch etapach(gotowy)
        CarQueue chassisQueue = new CarQueue(),
                bodyQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new FendersRobot(robotPool));
        exec.execute(new BodyRobot(robotPool));
        exec.execute(new ExhaustRobot(robotPool));
        exec.execute(new Assembler(
                chassisQueue, bodyQueue, robotPool));
        exec.execute(new Assembler2(
                bodyQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        // Start everything running by producing chassis:
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
/*
ćwiczenie polegało na dodaniu drugiego etapu budowy samochodu.

Pierwszy assembler montuje silnik, napęd i koła, a następnie
przekazuje samochód przez bodyQueue do drugiego Assemblera.
Drugi etap równocześnie montuje karoserię, wydech i błotniki.

ćwiczenie miało na celu pokazać współpracę pomiędzy rzeczami,
które były poruszane w rozdziale Concurrency.
*/