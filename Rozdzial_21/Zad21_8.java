import java.util.concurrent.TimeUnit;

/*
Exercise 8: (1) Modify MoreBasicThreads.java so that all the threads are daemon
threads, and verify that the program ends as soon as main( ) is able to exit.
*/
class LiftOff1 implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff1() {}
    public LiftOff1(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}
public class Zad21_8 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Thread d = new Thread(new LiftOff1());
            d.setDaemon(true);
            d.start();
        }
        System.out.println("Waiting for LiftOff");
        TimeUnit.MILLISECONDS.sleep(11);
    }
}

/*
Wszystkie utworzone wątki są daemonami, dlatego nie utrzymują JVM przy życiu. Gdy main() zakończy działanie,
program kończy się, mimo że zadania LiftOff1 nie zakończyły odliczania.

Liczba wyświetlonych wartości może się różnić między uruchomieniami,
ponieważ scheduler JVM i systemu operacyjnego może przydzielić wątkom różną ilość czasu procesora.
*/