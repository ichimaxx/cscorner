import static myutils.Skrocenie_Print.*;

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