import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 4: (3) Verify that a new testObject is created before each test.
*/
public class Zad20_4 {
    HashSet<String> testObject = new HashSet<String>();
    @Test void initialization() {
        assert testObject.isEmpty();
    }
    @Test void _contains() {
        assert testObject.isEmpty();
        testObject.add("one");
        assert testObject.contains("one");
    }
    @Test void _remove() {
        assert testObject.isEmpty();
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }
    public static void main(String[] args) throws Exception {
        //AtUnit.java MUSI BYĆ COMPILED!!!! JAKO AtUnit.class
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_4");
    }
}
/*
Zadanie sprawdza, czy testObject faktycznie jest tworzony przed każdym testem.
Widać to na przykładzie _contains(), Zostawia "one" w zbiorze.
Gdyby następny test działał na tym samym obiekcie jego pierwsze:
assert testObject.isEmpty();
nie przeszłoby.
Skoro wszystkie testy są OK, udowadnia to, że faktycznie jest tworzony przed każdym testem.
*/