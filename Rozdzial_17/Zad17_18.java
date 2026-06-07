import java.util.*;


/*
Exercise 18: (3) Using SlowMap.java for inspiration, create a SlowSet.
*/

//lista generująca integery od 0 do size -1
class CountingIntegerList extends AbstractList<Integer> {
    private int size;
    public CountingIntegerList(int size) {
        this.size = size < 0 ? 0 : size;
    }
    public Integer get(int index) {
        return Integer.valueOf(index);
    }
    public int size() { return size; }
}

public class Zad17_18<E> extends AbstractSet<E> {
    private List<E> keys = new ArrayList<E>();
    public boolean add(E key) {
        if(!keys.contains(key)) {
            keys.add(key);
            //jeżeli elementu nie ma, dodaje i zwraca true
            return true;
        }
        //jeżeli element już jest w set, zwraca false
        return false;
    }
    public int size() {
        return keys.size();
    }
    //Iterator zmodyfikowany z zadania Zad17_16.entrySet()
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int last = -1;

            public boolean hasNext() {
                return index < keys.size();
            }

            public E next() {
                last = index;
                E k = keys.get(index);
                index++;
                return k;
            }

            public void remove() {
                if (last == -1) // jeżeli last = -1 to oznacza, że nie ma aktualnego elementu do usunięcia
                    throw new IllegalStateException();
                keys.remove(last);
                index = last;
                last = -1;
            }
        };
    }
    public static void main(String[] args) {
        Zad17_18<Integer> m= new Zad17_18<Integer>();
        m.addAll(new CountingIntegerList(30));
        System.out.println("\nSet.addAll(new CountingIntegerList(30)): \n"+ m);
        m.add(9);
        System.out.println("\nSet.add(9): \n"+ m);
        m.add(100);
        System.out.println("\nSet.add(100): \n"+ m);
    }
}
