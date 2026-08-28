import java.util.concurrent.*;
import java.util.*;
import myutils.*;
/*
Exercise 34: (1) Modify ExchangerDemo.java to use your own class instead of Fat.
*/
//producent tworzy obiekty i przekazuje pełną listę konsumentowi
    class ExchangerProducer<T> implements Runnable {
        //generator tworzy kolejne obiekty typu T
    private myutils.Generator<T> generator;
    //wspólny punkt wymiany pomiędzy consumentem i producerem
    private Exchanger<List<T>> exchanger;
    //lista aktualnie należąca do producera
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchg,
                      myutils.Generator<T> gen, List<T> holder) {
        exchanger = exchg;generator = gen;
        this.holder = holder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //wypełnia aktualną listę nowymi obiektami
                for(int i = 0; i < Zad21_34.size; i++)
                    holder.add(generator.next());
                // zamienia pełną liste i otrzymuje od consumenta pustą
                //exchange() czeka aż consument również dojdzie do swojego exchange()
                holder = exchanger.exchange(holder);
            }
        } catch(InterruptedException e) {
            // OK to terminate this way.
        }
    }
}
//
class ExchangerConsumer<T> implements Runnable {
        //ten sam exchanger którego używa producer
    private Exchanger<List<T>> exchanger;
    //lista aktualnie należąca do consumenta
    private List<T> holder;
    //ostatni przetworzony element
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder){
        exchanger = ex;
        this.holder = holder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //konsument przekazuje pustą listę i otrzymuje pełną od producenta
                holder = exchanger.exchange(holder);
                //pobiera i usuwa wszystkie elementy z listy
                for(T x : holder) {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
            }
        } catch(InterruptedException e) {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }
}
public class Zad21_34 {
        //własna klasa zamiast Fat
    public static class MojWlasnyFat {
        //pole zapobiega usunięciu obliczeń jako nieużywanych
        private volatile double d;
        private static int counter = 0;
        private final int id = counter++;
        public MojWlasnyFat() {
            for(int i = 1; i < 15; i++) {
                d += (Math.PI + Math.E) / (double)i;
            }
        }
        public void operation() { System.out.println(this); }
        public String toString() { return "MojWlasnyFat id: " + id; }
    }
    //liczba obiektów w jednej wymienianej liście
    static int size = 10;
    //czas działania programu
    static int delay = 5; // Seconds
    public static void main(String[] args) throws Exception {
        if(args.length > 0)
            size = Integer.parseInt(args[0]);
        if(args.length > 1)
            delay = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        //wspólny exchanger wymieniajacy całe listy obiektów
        Exchanger<List<MojWlasnyFat>> xc = new Exchanger<List<MojWlasnyFat>>();
        //producer i consument zaczynają z osobnymi pustymi listami
        //po każdym exchange() zamieniają się tymi listami
        List<MojWlasnyFat>
                producerList = new CopyOnWriteArrayList<MojWlasnyFat>(),
                consumerList = new CopyOnWriteArrayList<MojWlasnyFat>();
        //uruchomienie producera tworzącego obiekty
        exec.execute(new ExchangerProducer<MojWlasnyFat>(xc,
                myutils.BasicGenerator.create(MojWlasnyFat.class), producerList));
        //uruchomienie consumera opróżniającego pełne listy
        exec.execute(
                new ExchangerConsumer<MojWlasnyFat>(xc,consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}

/*
Exchanger jest wspólnym punktem spotkania producera i consumenta.
Oba wątki korzystają z tego samego obiektu Exchanger<List<MojWlasnyFat>>
Producer wypełnia listę nowymi obiektami, a consument opróżnia listę otrzymaną poprzednim razem.
Dzięki temu można tworzyc informacje jeszcze w momencie przetwarzania wcześniejszych.
Producer tworzy obiekty MojWlasnyFat
V
wypełnia nimi swoją listę
V
Exchanger zamienia pełną listę na pustą
V
Consument opróżnia otrzymaną listę
V
kolejny swap
*/