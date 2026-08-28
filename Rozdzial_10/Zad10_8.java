import static myutils.Skrocenie_Print.*;
/*
Exercise 8: (2) Determine whether an outer class has access to the private elements of
its inner class.
*/
public class Zad10_8 {
	private int k = 60;
	private int wartosc() {
		return k;
	}
	class Inner108 {
		private int secretint = 150;
		
		private int inner() {
			k = 76;
			return wartosc();
		}
	}
	public void test() {
		println("przed zmiana k " + wartosc());
		new Inner108().inner(); // wywołanie prywatnej metody klasy wewnętrznej z kodu klasy zewnętrznej (dozwolone w każdym miejscu klasy zewnętrznej, pod warunkiem posiadania instancji Inner108)
		Inner108 j = new Inner108();
		println("po zmianie k " + wartosc());
		println("PRYWATNY SECRETINT " + j.secretint);
	}
	public static void main(String[] args) {
		Zad10_8 o = new Zad10_8();
		o.test();
	}
}


// KLASA ZEWNETRZNA MA ZAWSZE PRAWO DO WYWOLANIA METOD I PÓL PRIVATE KLASY WEWNETRZNEJ
		