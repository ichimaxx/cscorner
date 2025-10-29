import static myutils.Skrocenie_Print.*;

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
