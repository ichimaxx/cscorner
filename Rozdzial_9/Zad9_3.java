import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (2) Create a base class with an abstract print( ) method that is overridden
in a derived class. The overridden version of the method prints the value of an int variable
defined in the derived class. At the point of definition of this variable, give it a nonzero value.
In the base-class constructor, call this method. In main( ), create an object of the derived
type, and then call its print( ) method. Explain the results.
*/
abstract class AbstraktPrint {
	abstract void abstraktprint(int a);
	AbstraktPrint(){
		println("konstruktor bazowy przed");
		abstraktprint(25);
		println("konstruktor bazowy po");
	}
}

class NoabstraktPrint extends AbstraktPrint {
	int value = 45;
	
	@Override
	void abstraktprint(int a) {
		println(value);
	}
}
public class Zad9_3 {
	public static void main (String[] args) {
		AbstraktPrint ab = new NoabstraktPrint();
		ab.abstraktprint(999);
	}
}
/* podczas wykonania konstruktora bazowego jest wartosc domyslna czyli 0 a potem jak juz wejdzie klasa nadpisująca to dopiero wchodzi value 45 dlatego nie powinno sie wywolywac metody nadpisanej w konstruktorze

C:\Users\ichim\Desktop\cscorner>java Zad9_3
konstruktor bazowy przed
0
konstruktor bazowy po
45

*/
