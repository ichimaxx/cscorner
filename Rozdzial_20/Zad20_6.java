import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 6: (1) Test LinkedList using the approach shown in HashSetTest.java.
*/
public class Zad20_6{
    LinkedList<String> testObject = new LinkedList<String>();
    @Test void initialization() {
        assert testObject.isEmpty();
    }
    @Test void _contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }
    @Test void _remove() {
        testObject.add("one");
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }
    public static void main(String[] args) throws Exception {
        //AtUnit.java MUSI BYĆ COMPILED!!!! JAKO AtUnit.class
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_6");
    }
}

/*
W zadaniu zamiast HashSet<String> testowany jest LinkedList<String>
HashSet<> testuje zbiór, a LinkedList<> testuje listę.
W przypadku dodania drugiego testObject.add("one");
@Test _remove() będzie failed, ponieważ lista w przeciwieństwie do Set przechowa oba te same elementy.
*/