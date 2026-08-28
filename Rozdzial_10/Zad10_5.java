import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (1) Create a class with an inner class. In a separate class, make an instance of
the inner class.
*/
class Outer {
	class Inner {
		void inner() {
			println("tekst z klasy Outer w klasie wewnetrznej Inner");
		}
	}
}
public class Zad10_5 {
	public static void main(String[] args) {
		Outer o = new Outer();
		Outer.Inner oi = o.new Inner();
		oi.inner();
	}
}