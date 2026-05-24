import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (2) Note that List has additional "optional" operations that are not included
in Collection. Write a version of Unsupported.java that tests these additional optional
operations.
*/
public class Zad17_6 {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1,8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<String>(subList);
        try {
            c.retainAll(c2);
        } catch(Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try {
            c.removeAll(c2);
        } catch(Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try {
            c.clear();
        } catch(Exception e) {
            System.out.println("clear(): " + e);
        }
        try {
            c.add("X");
        } catch(Exception e) {
            System.out.println("add(): " + e);
        }
        try {
            c.addAll(c2);
        } catch(Exception e) {
            System.out.println("addAll(): " + e);
        }
        try {
            c.remove("C");
        } catch(Exception e) {
            System.out.println("remove(element): " + e);
        }
        // The List.set() method modifies the value but
        // doesn’t change the size of the data structure:
        try {
            list.set(0, "X");
        } catch(Exception e) {
            System.out.println("List.set(): " + e);
        }
        // list.addAll() można użyć tylko w List,
        // Collection ma addAll(Collection), ale bez wskazywania indeksu
        try {
            list.addAll(2, c2);
        } catch(Exception e) {
            System.out.println("List.addAll(): " + e);
        }
        // list.get() można użyć tylko w List, ta metoda nie modyfikuje listy, tylko czyta elementy
        try {
            list.get(3);
        } catch(Exception e) {
            System.out.println("List.get(): " + e);
        }
        // list.indexOf() można użyć tylko w List, ta metoda nie modyfikuje listy, tylko czyta elementy
        try {
            list.indexOf("A");
        } catch(Exception e) {
            System.out.println("List.indexOf(): " + e);
        }
        // list.subList() można użyć tylko w List, ta metoda nie modyfikuje listy,
        // tylko zwraca widok fragmentu listy od indeksu startowego do końcowego (1 do 6),
        // taki zakres obejmuje indeksy 1,2,3,4,5, ponieważ ostatni indeks -1
        try {
            list.subList(1, 6);
        } catch(Exception e) {
            System.out.println("List.subList(): " + e);
        }
        // list.add() można użyć tylko w List,
        // Collection ma add(element), ale bez wskazania indeksu
        try {
            list.add(1, "O");
        } catch(Exception e) {
            System.out.println("List.add(): " + e);
        }
        try {
            list.remove(5);
            // W Collection remove() można usunąć tylko obiekt.
            // W List remove() usuwa konkretny element na podstawie indeksu (5 - index)
        } catch(Exception e) {
            System.out.println("List.remove(index): " + e);
        }

    }
    public static void main(String[] args) {
        List<String> list =
                Arrays.asList("A B C D E F G H I J K L".split(" "));
        test("Modifiable Copy", new ArrayList<String>(list));
        test("Arrays.asList()", list);
        test("unmodifiableList()",
                Collections.unmodifiableList(
                        new ArrayList<String>(list)));
    }
}
/*
Metody specyficzne dla List istnieją, ale są opcjonalne.
To czy zadziałają, zależy jaką listę implementujemy do testu.
ArrayList będzie modyfikowalna.
Array.asList() ma stały rozmiar więc można edytować elementy, ale nie można zmieniać rozmiaru kontenera.
Collections.unmodifiableList() blokuje jakiekolwiek modyfikacje na kontenerze.
*/