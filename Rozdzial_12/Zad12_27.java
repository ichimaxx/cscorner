import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 27: (1) Modify Exercise 3 to convert the exception to a RuntimeException. */
public class Zad12_27 {
	int[] f = {15, 100, 3566, 6490640};
	public static void main(String[] args) { 
		Zad12_27 kok = new Zad12_27();
		try {
			println(kok.f[7]);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("Blad indexu w tablicy", e);
		}
		finally {
			println("koniec wyjatku");
		}
	}
}


// spakowalismy wyjatek w RuntimeException, dzieki temu mozna odpalac go bez try/catch poniewaz RuntimeException jest unchecked czyli kompilator nie wymusza try/catch ani throws, jak go nie zlapie to po prostu sie program wywali





