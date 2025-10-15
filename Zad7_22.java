import static myutils.Skrocenie_Print.*;

final class Klasa {
	public void f(){
		println("klasa final");
	}
}

class Klas1 extends Klasa {
	public void ff(){
	 f();
	}
}

public class Zad7_22 {
	public static void main(String[] args) {
		Klas1 xd = new Klas1();
		xd.ff();
	}
}
	
	
// NIE MOZNA DZIEDZICZYC PO KLASIE KTÓRA JEST FINAL ... DLATEGO TO ZADANIE SIE NIE SKOMPILUJE
