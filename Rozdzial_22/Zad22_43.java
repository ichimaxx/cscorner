import myutils.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/*
Exercise 43: (6) Choose any one of the Swing examples that wasn’t translated in this
section, and translate it to SWT.
*/
//Program pokazuje obsługę długotrwałych zadań wykonywanych w tle.
//Oryginalny przykład wykorzystuje Swing, został przepisany na SWT

//Zadanie wykonywane w osobnym wątku
//Każdy Task2 dostaje własne id ze wspólnego statycznego licznika counter
class Task2 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    public void run() {
        System.out.println(this + " started");
        try {
            //symulacja długotrwałego zadania
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            //zadanie może zostać przerwane np. przez Future.cancel(true)
            System.out.println(this + " interrupted");
            return;
        }
        System.out.println(this + " completed");
    }
    public String toString() { return "Task " + id; }
    public long id() { return id; }
}
//łączy konkretne zadanie Callable z odpowiadającym mu Future
//Future pozwala później sprawdzić stan zadania, pobrać wynik
//lub spróbować je anulować
class TaskItem2<R,C extends Callable<R>> {
    public final Future<R> future;
    public final C task;
    public TaskItem2(Future<R> future, C task) {
        this.future = future;
        this.task = task;
    }
}
//Zarządza kolejką zadań Callable oraz ich obiektami Future
//Dziedziczy po ArrayList, przechowuje kolejne TaskItem2
class TaskManager2<R,C extends Callable<R>>
        extends ArrayList<TaskItem2<R,C>> {
    private ExecutorService exec =
            Executors.newSingleThreadExecutor();
    public void add(C task) {
        add(new TaskItem2<R,C>(exec.submit(task),task));
    }
    //pobiera wyniki zadań, które zakończyły wykonywanie.
    public List<R> getResults() {
        Iterator<TaskItem2<R,C>> items = iterator();
        List<R> results = new ArrayList<R>();
        while(items.hasNext()) {
            TaskItem2<R,C> item = items.next();
            if(item.future.isDone()) {
                try {
                    results.add(item.future.get());
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
                items.remove();
            }
        }
        return results;
    }
    //anuluje zadania które jeszcze się nie zakończyły
    public List<String> purge() {
        Iterator<TaskItem2<R,C>> items = iterator();
        List<String> results = new ArrayList<String>();
        while(items.hasNext()) {
            TaskItem2<R,C> item = items.next();
            // Leave completed tasks for results reporting:
            if(!item.future.isDone()) {
                results.add("Cancelling " + item.task);
                item.future.cancel(true); // May interrupt
                items.remove();
            }
        }
        return results;
    }
}
//Rozszerza zwykłe Task2 o możliwość zwracania wyniku
//Callable<String> oznacza, że call() zwraca String
class CallableTask2 extends Task2
        implements Callable<String> {
    public String call() {
        //wykonuje tę samą pracę co zwykły Task2
        run();
        //po zakończeniu zwraca wynik, który można pobrać z Future
        return "Return value of " + this;
    }
}
public class Zad22_43 implements SWTApplication {
    //Manager przechowuje CallableTask2 oraz ich Future
    //i zarządza ich wykonywaniem
    private TaskManager2<String,CallableTask2> manager =
            new TaskManager2<String,CallableTask2>();
    //Listener przycisku Start,  Tworzy nowe zadanie i dodaje do kolejki managera
    private Listener listenerb = new Listener() {
        public void handleEvent(Event e) {
            CallableTask2 task = new CallableTask2();
            manager.add(task);
            System.out.println(task + " added to the queue");
        }
    };
    //Listener przycisku end, purge() anuluje wszystkie zadania, które
    //jeszcze się nie zakończyły
    private Listener listenerc = new Listener() {
        public void handleEvent(Event e) {
            for(String result : manager.purge())
                System.out.println(result);
        }
    };
    //Listener przycisku Get results
    //pobiera wyniki zadań, które zdążyły się zakończyć
    private Listener listenerd = new Listener() {
        public void handleEvent(Event e) {
            //pokazuje, że ponieważ TaskItem2 zachowuje konkretny typ zadania
            //możemy wywołać metodę Task2 bez dodatkowego rzutowania
            for(TaskItem2<String,CallableTask2> tt :
                    manager)
                tt.task.id(); // No cast required
            //getResults() zwraca wyniki zakończonych CallableTask2
            for(String result : manager.getResults())
                System.out.println(result);
        }
    };
    public void createContents(Composite parent) {
        //trzy kolumny dla trzech przycisków
        parent.setLayout(new GridLayout(3, true));
        //SWT tworzy Button przez podanie rodzica i stylu,
        //a tekst ustawiany jest osobno przez setText()
        Button b = new Button(parent, SWT.PUSH);
        b.setText("Start Long Running Task");
        Button c = new Button(parent, SWT.PUSH);
        c.setText("End Long Running Task");
        Button d = new Button(parent, SWT.PUSH);
        d.setText("Get results");
        //każdy przycisk otrzymuje odpowiedni listener
        b.addListener(SWT.MouseDown, listenerb);
        c.addListener(SWT.MouseDown, listenerc);
        d.addListener(SWT.MouseDown, listenerd);

    }
    public static void main(String[] args) {
        SWTConsole.run(new Zad22_43(), 400, 300);
    }
}

/*
Zadanie polega na przepisaniu wybranego przykładu ze Swing na SWT.
Wybrano InterruptableLongRunningCallable, który obsługuje długotrwałe zadania
uruchamiane w tle.

Logika związana z Callable, Future, TaskManager i ExecutorService pozostaje
bez zmian. Zmieniona została głównie warstwa GUI:
zamiast JButton jest SWT Button, a ActionListener na Listener.

Logika programu zostaje niezależna od GUI,
przy zmianie Swing na SWT zmodyfikowana została głównie obsługa interfejsu.
*/