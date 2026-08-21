import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import myutils.SwingConsole;
import net.mindview.util.*;
import myutils.*;
/*
Exercise 33: (6) Modify InterruptableLongRunningCallable.java so that it runs
all the tasks in parallel rather than sequentially.
*/
//długotrwałe zadanie
class Task implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    public void run() {
        System.out.println(this + " started");
        try {
            //symulacja dłużej wykonywanej operacji
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            System.out.println(this + " interrupted");
            return;
        }
        System.out.println(this + " completed");
    }
    public String toString() { return "Task " + id; }
    public long id() { return id; }
}
//TaskItem przechowuje razem oryginalny Callable i Future reprezentujący jego wykonanie
class TaskItem<R,C extends Callable<R>> {
    public final Future<R> future;
    public final C task;
    public TaskItem(Future<R> future, C task) {
        this.future = future;
        this.task = task;
    }
}
//TaskManager przechowuje wszystkie uruchomione zadania w postaci obiektów TaskItem
class TaskManager<R,C extends Callable<R>>
        extends ArrayList<TaskItem<R,C>> {
    //zmiana newSingleThreadExecutor() (oryginalny przykład) na newCachedThreadPool()
    //co pozwala na uruchamianie kilku tasków jednocześnie
    private ExecutorService exec =
            Executors.newCachedThreadPool();
    //dodaje nowe zadanie do executora
    public void add(C task) {
        //exec.submit(task) przekazuje Callable do ExecutorService
        //i zwraca Future<R> reprezentujące wykonanie tego zadania
        //Future oraz oryginalny task są od razu przekazywane do
        //konstruktora TaskItem i zapisywane w TaskManager
        add(new TaskItem<R,C>(exec.submit(task),task));
    }
    //pobiera wyniki wszystkich zakończonych tasków
    public List<R> getResults() {
        //iterator przechodzący po taskach z TaskManager
        Iterator<TaskItem<R,C>> items = iterator();
        //lista na wyniki
        List<R> results = new ArrayList<R>();
        while(items.hasNext()) {
            //pobiera następny TaskItem który zawiera task i jego Future
            TaskItem<R,C> item = items.next();
            if(item.future.isDone()) {
                try {
                    //jeżeli task isDone(), to get() pobiera wynik zwrócony przez metodę call()
                    results.add(item.future.get());
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
                items.remove();
            }
        }
        return results;}
    //anuluje wszystkie taski, które jeszcze się nie zakończyły
    public List<String> purge() {
        Iterator<TaskItem<R,C>> items = iterator();
        //lista komunikatów o anulowanych zadaniach
        List<String> results = new ArrayList<String>();
        while(items.hasNext()) {
            TaskItem<R,C> item = items.next();
            // Leave completed tasks for results reporting:
            //zakończone zadania nie sa usuwane,
            //ponieważ ich wyniki mogą zostać później pobrane przez getResults()
            if(!item.future.isDone()) {
                results.add("Cancelling " + item.task);
                //anulowanie konkretnego zadania
                //true pozwala na przerwanie działającego wątku
                item.future.cancel(true); // May interrupt
                //anulowane zadanie usuwamy z managera
                items.remove();
            }
        }
        return results;
    }
}
//CallableTask jest zwykłym Task, ale dodatkowo implementuje Callable<String>,
//czyli po zakończeniu może zwrócić wynik typu String
class CallableTask extends Task implements Callable<String> {
    public String call() {
        //wykonanie kodu odziedziczonego z Task
        run();
        //wartość zwracana przez call() będzie dostępna przez Future.get()
        return "Return value of " + this;
    }
}
public class Zad22_33 extends JFrame {
    private JButton
            b1 = new JButton("Start Long Running Task"),
            b2 = new JButton("End Long Running Task"),
            b3 = new JButton("Get results");
    private TaskManager<String,CallableTask> manager =
            new TaskManager<String,CallableTask>();
    public Zad22_33() {
        //dodaje nowe zadanie
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
            }
        });
        //anulowanie wszystkich nieskończonych zadań
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(String result : manager.purge())
                    System.out.println(result);
            }
        });
        //pobranie wyników zakończonych zadań
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sample call to a Task method:
                for(TaskItem<String,CallableTask> tt :
                        manager)
                    tt.task.id(); // No cast required
                for(String result : manager.getResults())
                    System.out.println(result);
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_33(), 200, 150);
    }
}

/*
Rodzaj ExecutorService decyduje o sposobie wykonywania zadań.

newSingleThreadExecutor() wykonuje taski kolejno,
natomiast newCachedThreadPool() (zamieniony) pozwala wykonywać wiele zadań równolegle.

Callable i Future pozwalają dodatkowo zachować kontrolę
nad każdym zadaniem osobno, bez wyłączenia całego ExecutorService.
*/