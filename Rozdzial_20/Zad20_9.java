import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 9: (2) Write basic @Unit tests for HashMap.
*/
class Hashimoto<K, V> {
    private HashMap<K, V> hmap = new HashMap<K, V>();
    public V grab(K v) { return hmap.get(v); }
    public V addd(K v, V z) { return hmap.put(v,z); }
    public void cler() { hmap.clear(); }
    public V remove(K v) { return hmap.remove(v);}
    public boolean isEmpty() { return hmap.isEmpty();}
}
public class Zad20_9 extends Hashimoto<String,String> {

    @Test void _grab() {
        addd("one", "ONEEE");
        assert grab("one").equals("ONEEE");
        addd("two", "TWOOO");
        assert grab("two").equals("TWOOO");
    }
    @Test void _addd() {
        addd("one", "one");
        addd("two", "two");
        assert remove("two").equals("two");
        assert remove("one").equals("one");
    }
    @Test void _cler() {
        addd("A", "AA");
        addd("B", "BB");
        cler();
        assert isEmpty();
    }
    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_9");
    }
}
/*
Zadanie wykonuje podstawowe testy dla HashMap.
Klasa Hashimoto opakowuje obiekt HashMap i udostępnia metody do dodawania,
pobierania, usuwania oraz czyszczenia elementów.
Każdy test jest wykonywany na nowym obiekcie Zad20_9
dlatego zmiany wykonane w jednym teście, nie wpływają na pozostałe.
*/