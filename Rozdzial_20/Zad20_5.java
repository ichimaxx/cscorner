import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 5: (1) Modify the above example to use the inheritance approach.
*/
public class Zad20_5 extends HashSet<String> {
    @Test void initialization() {
        assert isEmpty();
    }
    @Test void _contains() {
        assert isEmpty();
        add("one");
        assert contains("one");
    }
    @Test void _remove() {
        assert isEmpty();
        add("one");
        remove("one");
        assert isEmpty();
    }
    public static void main(String[] args) throws Exception {
        //AtUnit.java MUSI BYĆ COMPILED!!!! JAKO AtUnit.class
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_5");
    }
}

/*
Klasa Zad20_5 dziedziczy bezpośrednio po HashSet<String> (inheritance approach),
dlatego testy mogą wywoływać metody isEmpty(), add(),
constains() i remove() bez używania osobnego pola testObject.
*/