import inner_2.*;
import inner_1.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (2) Create an interface with at least one method, in its own package. Create a
class in a separate package. Add a protected inner class that implements the interface. In a
third package, inherit from your class and, inside a method, return an object of the
protected inner class, upcasting to the interface during the return.
*/
public class Zad10_6 extends Inner2 {
	public static void main(String[] args) {
		Inner2 o = new Inner2();
		Inner1 op = o.inner1();
		op.inner1();
	}
}
		