import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 4: (2) Create your own exception class using the extends keyword. Write a
constructor for this class that takes a String argument and stores it inside the object with a
String reference. Write a method that displays the stored String. Create a try-catch clause
to exercise your new exception. 
*/
class SimpleException extends Exception {
	private String stored;
	public SimpleException(String msg) {
		super(msg);
		this.stored = msg;
	}
	public void storedvoid() {
		println(stored);
	}
}

public class Zad12_4 {
	public String h;
	public void g(String h) throws SimpleException {
		this.h = h;
		println(h);
		throw new SimpleException("WOO to SimpleException");
	}
	public static void main(String[] args) { 
		Zad12_4 kok = new Zad12_4();
		try {
			kok.g("LOL");
			
		} catch(SimpleException e) {
			println("nie moge wykonac kodu poniewaz kok.g() jest: " + e.getMessage()); // wyciagnie String ktory zostal stworzony w metodzie g()
			e.storedvoid(); // zrobi to samo co e.getMessage() ale wyciagnie informacje z innego argumentu
		} catch(Exception e) {
			println("nie moge wykonac kodu poniewaz kok.g() jest: " + e.getMessage());
		}
		finally {
			println("koniec wyjatku");
		}
	}
}






