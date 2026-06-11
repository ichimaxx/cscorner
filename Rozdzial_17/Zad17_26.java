import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 26: (2) Add a char field to CountedString that is also initialized in the
constructor, and modify the hashCode( ) and equals( ) methods to include the value of
this char.
*/
public class Zad17_26 {
    private static List<String> created =
            new ArrayList<String>();
    private String s;
    // dodane pole wymagane przez Zad17_26,
    // musi być uwzględnione w hashCode() i equals
    private char c;
    private int id = 0;
    public Zad17_26(String str, char ch) {
        s = str;
        c = ch;
        created.add(s);
        // id is the total number of instances
        // of this string in use by CountedString:
        for(String s2 : created)
            if(s2.equals(s))
                id++;
    }
    public String toString() {
        return "String: " + s + " char: " + c + " id: " + id + " hashCode(): " + hashCode();
    }
    // hashcode musi uwzgledniać te same pola, które są sprawdzane w equals()
    // s, c, id
    // jeżeli dwa obiekty są równe equals, powinny mieć taki sam hashCode()
    public int hashCode() {
        int result = 17;
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch’s recipe:
        result = 37 * result + s.hashCode();
        // dodaje char do wyniku
        result = 37 * result + c;
        result = 37 * result + id;
        return result;
    }
    public boolean equals(Object o) {
        return o instanceof Zad17_26 &&
                // cast pozwalają się dostać do pól s,c i id drugiego obiektu
                s.equals(((Zad17_26)o).s) &&
                c == ((Zad17_26)o).c &&
                id == ((Zad17_26)o).id;
    }
    public static void main(String[] args) {
        Map<Zad17_26, Integer> map =
                new HashMap<Zad17_26, Integer>();
        Zad17_26[] cs = new Zad17_26[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new Zad17_26("hi", 'h');
            map.put(cs[i], i); // Autobox int -> Integer
        }
        println(map);
        for (Zad17_26 cstring : cs) {
            println("Looking up " + cstring);
            println(map.get(cstring));
        }
    }
}

// Zadanie ma pokazać jak można zmodyfikować hashCode() dodając nowe pole, np char.
// nowe pole musi również spełniać warunek equals() ponieważ hashCode() i equals() muszą
// opierać się na tych samych danych, aby obiekty poprawnie działały jako klucze w HashMap.