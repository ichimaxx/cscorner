import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (7) Using a LinkedList as your underlying implementation, define your
own SortedSet.
*/
public class Zad17_10<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T>  { // elementy T będą musiały potrafić porównywać się przez compareTo()
    // potrzebne do sortowania
    private LinkedList<T> ok = new LinkedList<T>(); //underlying implementation,
    //klasa zachowuje się jak Set z posortowanym dodawaniem, jednak dane przechowywane są w LinkedList
    public String toString() { return ok.toString();
    }
    public boolean isEmpty() {
        return ok.isEmpty();
    }
    @Override
    public int size() {
        return ok.size();
    }
    @Override
    public int hashCode() {
        int h = 0;
        Iterator<T> i = iterator();
        while (i.hasNext()) {
            T obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h;
    }
    @Override
    public T first() {
        if(ok.isEmpty()) {
            throw new NoSuchElementException();
        } else
        return ok.getFirst();
    }
    @Override
    public T last() {
        if(ok.isEmpty()) {
            throw new NoSuchElementException();
        } else
            return ok.getLast();
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }
    @Override
    // Ze względu na to, ze LinkedList nie ma subset, headset i tailset trzeba stworzyć te metody
    public Zad17_10<T> subSet(T fromElement, T toElement) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
            //jeżeli jeden z argumentów jest null, zwraca wyjątek NullPointerException()
        }
        if (fromElement.compareTo(toElement) > 0) {
            throw new IllegalArgumentException();
        } // jeśli argument toElement jest mniejszy niz fromElement zwraca wyjątek IllegalArgumentException()
    // subSet zwraca elementy od fromElement do toElement z wyłączeniem toElement
        Zad17_10<T> nowy = new Zad17_10<T>();
        for (T x : ok) {
            if (x.compareTo(fromElement) >= 0 && x.compareTo(toElement) < 0) {
                nowy.add(x);
            }
        }
        return nowy;
    }
    @Override
    public Zad17_10<T> headSet(T toElement) {
        if (toElement == null) {
            throw new NullPointerException();
        }
    // headSet zwraca elementy mniejsze od toElement
        Zad17_10<T> nowy = new Zad17_10<T>();
        for (T x : ok) {
            if (x.compareTo(toElement) < 0) {
                nowy.add(x);
            }
        }
        return nowy;
    }
    @Override
    public Zad17_10<T> tailSet(T fromElement) {
        if (fromElement == null) {
            throw new NullPointerException();
        }
    // tailSet zwraca elementy większe lub RÓWNE fromElement
        Zad17_10<T> nowy = new Zad17_10<T>();
        for (T x : ok) {
            if (x.compareTo(fromElement) >= 0) {
                nowy.add(x);
            }
        }
        return nowy;
    }
    @Override
    public boolean add(T i) {
        if (i == null) { // jeśli i będzie null, wyrzuci wyjątek
            throw new NullPointerException();
        }
        if (ok.isEmpty()) { // jeśli pusta lista dodaje
            ok.add(i);
            return true; // informacja, że element został dodany
            // bez return true; metoda nie będzie mogła zwrócić wartości
        }
        ListIterator<T> it = ok.listIterator(); // do dodawania użyto listIterator,
        // pozwala cofać się w liście, dodawać w jej środku
        while (it.hasNext()) {
            T check = it.next();
            if (check.compareTo(i) == 0) { // jeśli check i dodawany element są równe nie dodaje się elementu
                // compareTo() zwraca int i porównuje dwa elementy tego samego typu T
                return false;
            } else if (check.compareTo(i) > 0) { // check jest większe od dodawanego elementu
                it.previous();// cofa iterator przed sprawdzony element
                it.add(i); // i przed nim dodaje element
                return true;
            }
        }
        it.add(i);// jeśli nie znaleziono duplikatu i większego elementu(while), dodaje element na końcu listy
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends T> c) {
        // argument Collection<? extends T> umożliwia przyjęcie kolekcji typu T albo dowolnego podtypu T
        if (c == null) {
            throw new NullPointerException();
        } // jeśli kolekcja jest null wyrzuci wyjątek NullPointerException();
        for (T x : c) {
            if (x == null) { // jeżeli któryś z elementów kolekcji jest null wyrzuci NullPointerException();
                throw new NullPointerException();
            }
        }
        return super.addAll(c); // używa gotowego addAll(), które wywołuje nadpisane w Zad17_10 add()
    }
    @Override
    public Iterator<T> iterator() {
        return ok.iterator();
    }


    public static void main (String[] args) {
        Zad17_10<Integer> k = new Zad17_10<>();
        for(int i = 0; i < 10; i++) {
            k.add(i + 5);
        }
        k.add(1); // dodane 1 na końcu mimo wszystko wyprowadzone jest na początku listy
        println(k);

    }
}
