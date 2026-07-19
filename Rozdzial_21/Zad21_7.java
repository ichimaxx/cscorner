import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (2) Experiment with different sleep times in Daemons.java to see what
happens.
*/
class Daemon implements Runnable {
    private Thread[] t = new Thread[10];
    public void run() {
        for(int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            println("DaemonSpawn " + i + " started, ");
        }
        for(int i = 0; i < t.length; i++)
            println("t[" + i + "].isDaemon() = " +
                    t[i].isDaemon() + ", ");
        while(true)
            Thread.yield();
    }
}
class DaemonSpawn implements Runnable {
    public void run() {
        while(true)
            Thread.yield();
    }
}
public class Zad21_7 {
    public static void main(String[] args) throws Exception {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        //dopóki nie wpiszesz d.start() program run() się nie uruchomi
        TimeUnit.SECONDS.sleep(1);
        d.start();
        //wątek śpi przez sekundę, dając daemonowi czas na utworzenie i uruchomienie wątków DaemonSpawn()
        //W tym czasie metoda run() nadal działa
        TimeUnit.SECONDS.sleep(1);
        println("d.isDaemon() = " + d.isDaemon() + ", ");
        //Urzymuje wątek main przy życiu jeszcze przez sekundę,
        //dzięki czemu wątki daemon mogą nadal działać.
        //Po zakończeniu main() JVM kończy program.
        TimeUnit.SECONDS.sleep(1);
    }

}

/*
Proces działania:
1. Tworzy wątek d
2. Ustawia d jako daemon.
3. main śpi przez 1 sekundę - d jeszcze nie działa
4. d.start() uruchamia Daemon.run()
5. main ponownie śpi przez sekundę
6. d tworzy 10 wątków DaemonSpawn
7. main wypisuje d.isDaemon() = true
8. main śpi jeszcze sekundę
9. main się kończy
10. JVM kończy program, ponieważ zostały tylko wątki daemon

Wątki daemon nie utrzymują JVM przy życiu. Kiedy zakończą się wszystkie wątki non-daemon, JVM zakończy program,
nawet jeśli wątki daemon nadal wykonują swoje metody run().
*/
