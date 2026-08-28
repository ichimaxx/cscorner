import static myutils.Skrocenie_Print.*;
/*
Exercise 18: (1) Create a class containing a nested class. In main( ), create an instance
of the nested class.
*/
public class Zad10_18 {
	private static class NestedKlasa {
		private int f = 150;
		private int wielkosc() {
			return f;
		}
	}
	public static NestedKlasa klasa() {
		return new NestedKlasa();
	}
	public static void main (String[] args) {
		NestedKlasa o = klasa();
		println(o.wielkosc());
	}
}