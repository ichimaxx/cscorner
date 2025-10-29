import static myutils.Skrocenie_Print.*;


abstract class Abstrakt {
	void test() {
		println("abstrakt class test");
	}
}

class Noabstrakt extends Abstrakt {
	@Override
	void test() {
		println("nieabstrakt class test");
	}
}
public class Zad9_2 {
	public static void main (String[] args) {
		Abstrakt ab = new Abstrakt();
		ab.test();
	}
}
		
		
		/* zADANIE NIE ODPALI BO TAKI BŁĄD:
		
		C:\Users\ichim\Desktop\cscorner>javac Zad9_2.java
Zad9_2.java:18: error: abstrakt is abstract; cannot be instantiated
                abstrakt ab = new abstrakt();
                              ^
1 error

ALE TAKI BYŁ CEL ZADANIA, METODA Z KLASY ABSTRACT NIE MOZE BYĆ ODPALONA KIEDY NIE JEST SAMA ABSTRACT, NADPISANA Z KLASY "NOABSTRAKT" JUZ MOZNA ODPALIC  */