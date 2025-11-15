import static myutils.Skrocenie_Print.*;
class Inner107 {};

public class Zad10_12 {
	private int k = 60;
	private int wartosc() {
		return k;
	}
	public Inner107 kinner107() {
		return new Inner107() {
			{
				k = 76;
				wartosc();
			}
		};
	}
	public void test() {
		println("przed zmiana k " + wartosc());
		kinner107();
		println("po zmianie k " + wartosc());
	}
	public static void main(String[] args) {
		Zad10_12 o = new Zad10_12();
		o.test();
	}
}
		