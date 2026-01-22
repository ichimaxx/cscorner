import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 28: (1) Modify Exercise 4 so that the custom exception class inherits from
RuntimeException, and show that the compiler allows you to leave out the try block. 
*/
class SimpleExceptiones extends RuntimeException {
	private String stored;
	public SimpleExceptiones(String msg) {
		super(msg);
		this.stored = msg;
	}
	public void storedvoid() {
		println(stored);
	}
}

public class Zad12_28 {
	public String h;
	public void g(String h) {
		this.h = h;
		println(h);
		throw new SimpleExceptiones("WOO to SimpleExceptiones");
	}
	public static void main(String[] args) { 
		Zad12_28 kok = new Zad12_28();
			kok.g("LOL");
	}
}




// to samo co w poprzednim zadaniu, kompilator pozwolil mi skompilowac kod, bo wyjatek jest unchecked i nie trzeba try/catch, ani throws z metody. tyle ze jak bedzie exception to po prostu program sie wywali


