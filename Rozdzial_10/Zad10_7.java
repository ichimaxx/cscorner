import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (2) Create a class with a private field and a private method. Create an
inner class with a method that modifies the outer-class field and calls the outer-class method.
In a second outer-class method, create an object of the inner class and call its method, then
show the effect on the outer-class object.
*/
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
		