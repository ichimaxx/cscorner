import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 9: (2) Use RandomGenerator.String to fill a TreeSet, but use alphabetic
ordering. Print the TreeSet to verify the sort order.
*/
public class Zad17_9 {
    public static void main(String[] args) {
        Set<String> st = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        // z dodaniem String.CASE_INSENSITIVE_ORDER
        // do TreeSet będzie sortować nie patrząc na wielkość liter
        myutils.Generator<String> z = new myutils.RandomGenerator.String(3);
        for (int i = 0; i < 25 ; i++) {
            st.add(z.next()); // metoda next() z generatora RandomGenerator.String()
        }
        println(st);
    }
}

/*
Podczas użycia CASE_INSENSITIVE_ORDER w TreeSet, elementy, które różnią się tylko wielkością liter,
będą przez Comparator uznawane za takie same, więc pokaże się tylko jeden z nich.
Dla tego konkretnego TreeSet:
"abc" będzie traktowane tak samo jak "ABC",
więc w TreeSet będzie pokazana tylko jedna wersja.
*/