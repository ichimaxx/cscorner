import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 7: (1) Modify the previous exercise to use the inheritance approach.
*/
public class Zad20_7 extends LinkedList<String> {
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
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_7");
    }
}

/*
Klasa Zad20_7 dziedziczy bezpośrednio po LinkedList<String>,
dlatego metody isEmpty(), add(), constains() i remove()
są wywoływane bez używania osobnego obiektu testObject.

Framework @Unit tworzy nowy obiekt Zad20_7 przed każdym testem.
*/