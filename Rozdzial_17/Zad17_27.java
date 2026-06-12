import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 27: (3) Modify the hashCode() in CountedString.java(Zad17_27) by removing the
combination with id, and demonstrate that CountedString still works as a key. What is the
problem with this approach?
*/

public class Zad17_27 {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;
    public Zad17_27(String str) {
        s = str;
        created.add(s);
        // id is the total number of instances
        // of this string in use by CountedString:
        for(String s2 : created)
            if(s2.equals(s))
                id++;
    }
    public String toString() {
        return "String: " + s + " id: " + id +
                " hashCode(): " + hashCode();
    }
    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch’s recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result;
        return result;
    }
    public boolean equals(Object o) {
        return o instanceof Zad17_27 &&
                s.equals(((Zad17_27)o).s) &&
                id == ((Zad17_27)o).id;
    }
    public static void main(String[] args) {
        Map<Zad17_27,Integer> map =
                new HashMap<Zad17_27,Integer>();
        Zad17_27[] cs = new Zad17_27[5];
        for(int i = 0; i < cs.length; i++) {
            cs[i] = new Zad17_27("hi");
            map.put(cs[i], i); // Autobox int -> Integer
        }
        println(map);
        for(Zad17_27 cstring : cs) {
            println("Looking up " + cstring);
            println(map.get(cstring));
        }
    }
}
/*
HashMap nadal działa, pomimo usunięcia unikatowych hashcodes, ponieważ equals uwzględnia id
(id == ((Zad17_27)o).id;)

To znaczy, że obiekty Z takim samym Stringiem "hi", ale innym id, nadal są traktowane jako różne klucze.

Problem z tym podejściem jest taki, że wszystkie obiekty "hi" mają teraz taki sam hashCode(),
więc trafiają do tego samego bucketa. Hashmap działa dalej poprawnie, ale może działać wolnej,
ponieważ będzie iterować po jednym buckecie.

Gdyby equals() porównywałoby tylko String s, bez id to wtedy wszystkie "hi" byłyby traktowane jako ten sam klucz
i kolejne put() nadpisywałyby kolejną wartość.
*/