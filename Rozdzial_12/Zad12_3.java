import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 3: (1) Write code to generate and catch an
ArraylndexOutOfBoundsException. */
public class Zad12_3 {
	int[] f = {15, 100, 3566, 6490640};
	public static void main(String[] args) { 
		Zad12_3 kok = new Zad12_3();
		try {
			println(kok.f[7]);
		} catch(ArrayIndexOutOfBoundsException e) {
			println("nie moge wykonac kodu poniewaz kok.f[] jest: " + e.getMessage());
		} catch(Exception e) {
			println("nie moge wykonac kodu poniewaz kok.f[] jest: " + e.getMessage());
		}
		finally {
			println("koniec wyjatku");
		}
	}
}






