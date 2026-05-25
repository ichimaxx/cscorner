
import net.mindview.util.Countries;

import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (4) Create both an ArrayList and a LinkedList, and fill each using the
Countries.names( ) generator. Print each list using an ordinary Iterator, then insert one
list into the other by using a Listlterator, inserting at every other location. Now perform the
insertion starting at the end of the first list and moving backward.
*/
public class Zad17_7 {
    public static void main(String[] args) {
        List<String> z = new LinkedList<String>(Countries.names(5));
        List<String> zk = new ArrayList<String>(Countries.names(5));
        Iterator<String> x = z.iterator();
        Iterator<String> xp = zk.iterator();
        ListIterator<String> it1 = z.listIterator();
        ListIterator<String> it2 = zk.listIterator();
        while(x.hasNext())
            print(x.next() + " ");
        println("");
        while(xp.hasNext())
            print(xp.next() + " ");
        String s;
        while(it2.hasNext()) {
            s = it2.next();
            it1.next(); // przesuwanie się w liście do następnego elementu
            it1.add(s);
            }
        println("\nList<String> z + List<String> zk, co drugi element dodawanie:\n" + z);
        while(it2.hasPrevious()) {
            s = it2.previous();
            // cofanie jest o dwa miejsca,
            // ponieważ celem jest trafienie w co drugi element powiększonej listy
            // tak, aby trafiać iteratorem między kolejne pary
            it1.previous();
            it1.previous();
            it1.add(s);
        }
        println("\nList<String> z + List<String> zk, wstawianie od końca listy:\n" + z);
    }
}
