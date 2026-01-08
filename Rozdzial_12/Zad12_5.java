import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 5: (3) Create your own resumption-like behavior using a while loop that
repeats until an exception is no longer thrown. 
*/
class ProstyException extends Exception {
}

public class Zad12_5 {
	public void g() throws ProstyException {
		throw new ProstyException();
	}
	public static int stored = 0;
	public static int i;
	public static String test = null;
	public static void main(String[] args) { 
		Zad12_5 kok = new Zad12_5();
		while(test == null) {
			try {
				kok.g();
			} catch(ProstyException e) {
				println("dodawanie...... +1");
				stored++;
				if(stored == 5) { 
				test = "koniec";
				}
				println(stored);
			} catch(Exception e) {
				println("nie moge wykonac kodu poniewaz kok.g() jest: " + e.getMessage());
			}
		}
			
		println(test);
		
	}
}






