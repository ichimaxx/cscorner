import static myutils.Skrocenie_Print.*;

public class Zad10_7 {
	private int k = 60;
	private int wartosc() {
		return k;
	}
	class Inner107 {
		public int inner() {
			k = 76;
			return wartosc();
		}
	}
	public void test() {
		println("przed zmiana k " + wartosc());
		new Inner107().inner(); // w tym momencie klasa zewnetrzna dostaje access 
		println("po zmianie k " + wartosc());
	}
	public static void main(String[] args) {
		Zad10_7 o = new Zad10_7();
		o.test();
	}
}
		