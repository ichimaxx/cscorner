import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 11: (1) Test New.java by creating your own classes and ensuring that New will
work properly with them.
*/

class New {
    public static <K,V> Map<K,V> map() {
        return new HashMap<K,V>();
    }
    public static <T> List<T> list() {
        return new ArrayList<T>();
    }
    public static <T> LinkedList<T> lList() {
        return new LinkedList<T>();
    }
    public static <T> Set<T> set() {
        return new HashSet<T>();
    }
    public static <T> Queue<T> queue() {
        return new LinkedList<T>();
    }
}
class Ludzie { // wlasna nowa klasa
    String name;
    public Ludzie(String name) { this.name = name; }
    public String toString() {
        return name;
    }
}
public class Zad15_11 {
    public static void main(String[] args) {
        List<Ludzie> lus = New.list(); // New with moja nowa klasa
        lus.add(new Ludzie("Ludz"));
        lus.add(new Ludzie("Ludz2"));
        lus.add(new Ludzie("Ludz3")); // dodane 3 wartości do List
        println(lus); //print List
    }
}