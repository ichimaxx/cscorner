import java.util.*; 
import java.util.logging.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 7: (1) Modify Exercise 3 so that the catch clause logs the results.  */
public class Zad12_7 {
	private static final Logger logger = Logger.getLogger(Zad12_7.class.getName());
	int[] f = {15, 100, 3566, 6490640};
	public static void main(String[] args) { 
		Zad12_7 kok = new Zad12_7();
		try {
			println(kok.f[7]);
		} catch(ArrayIndexOutOfBoundsException e) {
			logger.log(Level.SEVERE, "Zlapany wyjatek ArrayIndexOutOfBoundsException: ", e);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Zlapany wyjatek: ", e);
		}
		finally {
			println("koniec wyjatku");
		}
	}
}






