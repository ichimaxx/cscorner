import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 2: (1) Define an object reference and initialize it to null. Try to call a method
through this reference. Now wrap the code in a try-catch clause to catch the exception. */
public class Zad12_2 {
	private String k;
	public void o(String k) {
		this.k = k;
		println(k);
	}
	public static void main(String[] args) { 
		Zad12_2 ok = null;
		try {
			ok.o("ok");
		} catch(Exception e) {
			println("nie moge wykonac kodu poniewaz o() jest: " + e.getMessage());
		}
		finally {
			println("koniec wyjatku");
		}
	}
}