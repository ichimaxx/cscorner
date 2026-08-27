import static myutils.Skrocenie_Print.*;
/*
Exercise 22: (1) Create a final class and attempt to inherit from it.
*/
final class Klasa_5 {
	public void f(){
		println("klasa final");
	}
}

class Klas1
		//extends Klasa_5
{
	public void ff(){
		//f();
	}
}

public class Zad7_22 {
	public static void main(String[] args) {
		Klas1 xd = new Klas1();
		xd.ff();
	}
}
	
	
// JEŻELI ODKOMENTUJEMY "extends Klasa_5" ZADANIE SIĘ NIE SKOMPILUJE,
// PONIEWAŻ NIE MOZNA DZIEDZICZYC PO KLASIE KTÓRA JEST FINAL ...
