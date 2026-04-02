import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 12: (1) Repeat the previous exercise using explicit type specification.
*/

class New2 {
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
class Ludzie2 { // wlasna nowa klasa
    String name;
    public Ludzie2(String name) { this.name = name; }
    public String toString() {
        return name;
    }
}
public class Zad15_12 {
    static List<Ludzie2> f(List<Ludzie2> lus) {// ExplicitTypeSpecification z moja nowa klasa przerobione z void na liste zeby mozna bylo wyprintowac wynik w main
        lus.add(new Ludzie2("Ludz"));
        lus.add(new Ludzie2("Ludz2"));
        return lus;
    }
    public static void main(String[] args) {
        println(f(New2.<Ludzie2>list())); // zamieniono na liste zamaist void aby łatwo wypisać wynik
    }
}
/*
type inference działa tylko dla przypisania typu np
List<Ludzie2> x = New2.list();

ale nie zadziala kiedy wynik wywolania tej metody przekażemy od razu do argumentu w innej metodzie np
f(New2.list());

wtedy trzeba uzyc wlasnie ExplicitTypeSpecification czyli dla tego przykladu bedzie
New2.<Ludzie2>list()

kompilator nie zawsze wywnioskuje typ gdy wynik metody generycznej przekazuje sie bezposrednio do innej metody dlatego trzeba podać typ
(tutaj <Ludzie2>)
*/