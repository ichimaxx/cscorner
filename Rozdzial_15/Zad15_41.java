import Rozdzial_15.typeinfo.pets.*;
import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 41: (1) Modify Fill2.java to use the classes in typeinfo.pets instead of the
Coffee classes.
*/
interface Addable<T> { void add(T t); }
class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void add(T t) { storage.offer(t); }
    public T get() { return storage.poll(); }
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
class Fill2 {
    // Classtoken version:
    public static <T> void fill(Addable<T> addable,
                                Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance()); // stara metoda, nowa to addable.add(classToken.getDeclaredConstructor().newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
    // Generator version:
    public static <T> void fill(Addable<T> addable,
                                Generator<T> generator, int size) {
        for(int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}
// To adapt a base type, you must use composition.
// Make any Collection Addable using composition:
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }
    public void add(T item) { c.add(item); }
}

// A Helper to capture the type automatically:
class Adapter {
    public static <T>
    Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}
    // To adapt a specific type, you can use inheritance.
// Make a SimpleQueue Addable using inheritance:
    class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
        public void add(T item) { super.add(item); }
    }

    public class Zad15_41 {
        private static long counter = 0;
        private final long id = counter++;
        public String toString() {
            return String.valueOf(id);
        }
        public static void main(String[] args) {
            // Adapt a Collection:
            List<Pet> carrier = new ArrayList<Pet>();
            Fill2.fill(new AddableCollectionAdapter<Pet>(carrier), Pet.class, 3);
            // Helper method captures the type:
            Fill2.fill(Adapter.collectionAdapter(carrier),
                    Mutt.class, 2);
            for(Pet c: carrier) {
                print(c);
                println(" " + new Zad15_41()); // licznik
            }
            println("----------------------");
            // Use an adapted class:
            AddableSimpleQueue<Pet> petQueue = new AddableSimpleQueue<Pet>();
            Fill2.fill(petQueue, Rat.class, 4);
            Fill2.fill(petQueue, Cat.class, 1);
            for(Pet c: petQueue) {
                print(c);
                println(" " + new Zad15_41()); // licznik
                }
        }
    }
/*
W zadaniu zamieniono klasę Coffee na Pet.
Zadanie pokazuje sposoby adaptowania istniejących klas do interfejsu Addable, bez zmieniania samych klas tak jak w przykładzie: Collection i SimpleQueue.
przez kompozycję przy AddableCollectionAdapter, opakowywanie Collection
przez dziedziczenie AddableSimpleQueue extends SimpleQueue
*/