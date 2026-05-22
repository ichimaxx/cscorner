import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (1) Using Countries, fill a Set multiple times with the same data and verify
that the Set ends up with only one of each instance. Try this with HashSet,
LinkedHashSet, and TreeSet.
*/
public class Zad17_3 {
    public static void main(String[] args){
        Set<String> sk = new HashSet<String>(Countries.names());
        Set<String> st = new TreeSet<String>(Countries.names());
        Set<String> slh = new LinkedHashSet<String>(Countries.names());
        sk.addAll(Countries.names());
        sk.addAll(Countries.names());
        sk.addAll(Countries.names());
        println("\nHashSet wypełniony kilka razy tymi samymi danymi z Countries.names(): \n\n" + sk);
        println("HashSet size: " + sk.size());
        println("Countries.names() size: " + Countries.names().size());
        // HashSet nie gwarantuje kolejności, ale jest najmniej zasobożerny
        st.addAll(Countries.names());
        st.addAll(Countries.names());
        st.addAll(Countries.names());
        println("\nTreeSet wypełniony kilka razy tymi samymi danymi z Countries.names(): \n\n" + st);
        println("TreeSet size: " + st.size());
        println("Countries.names() size: " + Countries.names().size());
        // TreeSet zachowuje kolejność alfabetyczną
        slh.addAll(Countries.names());
        slh.addAll(Countries.names());
        slh.addAll(Countries.names());
        println("\nLinkedHashSet wypełniony kilka razy tymi samymi danymi z Countries.names(): \n\n" + slh);
        println("LinkedHashSet size: " + slh.size());
        println("Countries.names() size: " + Countries.names().size());
        // LinkedHashSet zachowuje kolejność dodawania
    }
}
// Set zawsze zostawia w swojej kolekcji tylko jeden taki sam element,
// wielokrotnie dodawane te same dane nie zwiększają jego rozmiaru.
