import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call
yield( ). Repeat this three times, and then return from run( ). Put a startup message in the
constructor and a shutdown message when the task terminates. Create a number of these
tasks and drive them using threads.
*/
public class Zad21_1 implements Runnable {
    public Zad21_1 () {
        println("STARTUP MESSAGE");
    }
    @Override
    public void run() {
        println("message1");
        Thread.yield();
        println("message2");
        Thread.yield();
        println("message3");
        Thread.yield();
        println("SHUTDOWN");
    }
    public static void main(String[] args) {
        for(int i = 0; i < 7; i++) {
            Zad21_1 z = new Zad21_1();
            Thread watek = new Thread(z);
            watek.start();
        }
    }
}
/*
Zadanie pokazuje, że kilka obiektów Runnable jesty wykonywane w osobnych wątkach w ramach jednego procesu.

Każde wywołanie start() uruchamia metodę run() w nowym wątku.
Wątki są planowane niezależnie, dlatego komunikaty pochodzące z różnych zadań pojawiają się w różnej kolejności.

Thread.yield() sugeruje, że bieżący wątek może oddać czas wykonywania innemu wątkowi, ale nie gwarantuje przełączenia.
*/