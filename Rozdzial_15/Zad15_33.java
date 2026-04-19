import java.util.*;
/*
Exercise 33: (3) Repair GenericCast.java(Zad15_33) using an ArrayList.
*/

class FixedSizeStack2<T> {
    private int index = 0;
    private List<T> storage; // Zamiana Object[] na List. Dodano typ do List
    public FixedSizeStack2(int size) {
        storage = new ArrayList<T>(size); // dodano typ do List
    }
    public void push(T item) {
        storage.add(item);
        ++index;
    }
    public T pop() {
        return storage.remove(--index); } // dzięki zamianie Object[] na List<T> można było usunąć @SuppressWarnings i cast (T) ponieważ storage.remove(--index) zwraca już T.
        // remove(--index) od razu usuwa element z listy, więc struktura zachowuje się jak stos.
}
public class Zad15_33 {
    public static final int SIZE = 10;
    public static void main(String[] args) {
        List<String> h = new ArrayList<>(List.of("A B C D E F G H I J".split(" "))); // osobna lista string która zostanie wrzucona na "stos"
        FixedSizeStack2<String> strings = new FixedSizeStack2<String>(SIZE);
        for(String s : h) {
            strings.push(s);
        }
        for(int i = 0; i < SIZE; i++) {
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}