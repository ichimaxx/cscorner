import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 8: (2) Create a class with a private method and add a non-private
@TestProperty method as described above. Call this method in your test code.
*/
class StackL<T> {
    private LinkedList<T> list = new LinkedList<T>();
    private void adds(T z) { list.add(z); }
    public void push(T v) { list.addFirst(v); }
    //dodatkowy caller, aby można było wywołać private method z tej klasy
    @TestProperty void calladds(T z) {
        adds(z);
    }
    public T top() { return list.getFirst(); }
    public T pop() { return list.removeFirst(); }
}
public class Zad20_8 extends StackL<String> {

    @Test void _adds() {
        calladds("kkk");
    }
    @Test void _push() {
        push("one");
        assert top().equals("one");
        push("two");
        assert top().equals("two");
    }
    @Test void _pop() {
        push("one");
        push("two");
        assert pop().equals("two");
        assert pop().equals("one");
    }
    @Test void _top() {
        push("A");
        push("B");
        assert top().equals("B");
        assert top().equals("B");
    }
    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_8");
    }
}

/*
Przykład jest stworzony na podstawie dziedziczącej klasy po klasie generycznej.
Największym minusem tego zabiegu jest brak możliwości użycia metody prywatnej.
Można to obejść, robiąc tak, jak w przykładzie.
Stworzona została druga metoda nieprywatna, która wywołuje prywatną.
*/