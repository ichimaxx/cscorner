import java.util.*; 
import static myutils.Skrocenie_Print.*;

/*Exercise 30: (2) Modify Human.java so that the exceptions inherit from
RuntimeException. Modify main( ) so that the technique in TurnOffChecking.java is
used to handle the different types of exceptions. */

class Annoyances extends RuntimeException {}
class Sneezes extends Annoyances {}

class WrapException {
	void throwRuntimeException(int type) {
		try {
			switch(type) {
				case 0: throw new Annoyances();
				case 1: throw new Sneezes();
				default: return;
			}
		} catch(RuntimeException e) { // Adapt to unchecked:
			throw new RuntimeException(e);
		}
	}
} 

public class Zad12_30 {
	public static void main(String[] args) {
		WrapException ex = new WrapException();
		for (int i = 0; i < 3; i++) 
	// Catch the exact type:
			try {
				if (i < 2)
					ex.throwRuntimeException(i);
				else {
					println("koniecC");
					break;
				}
			} catch(RuntimeException re) {
				try {
					throw re.getCause();
				} catch(Sneezes e) {
					println("Sneezes zlapane: " + e);
				} catch(Annoyances e) {
					println("Annoyances zlapane: " + e);
				} catch(Throwable e) {
					println("Throwable zlapane: " + e);
				} 
			}	 
	}
}

// przerobiony kod Human.java na wrapped exceptions, klasa WrapException ma switcha ktory leci w petli for w main i rozpakowuje tam exceptions i wyciaga przyczyne za pomoca re.getCause(); 
// generalnie ta metoda jest wykorzystywan do checked exceptions i wtedy nie trzeba pisac throws w metodach ani try/catch na kazdym poziomie, tylko przy wypakowaniu. 
// po prostu jak rozumiem to to cwiczenie kazalo zademonstrowac na podstawie Human.java gdzie i tak jest wszystko RuntimeExceptions, czyli w takiej sytuacjie obeszło by się bez tego.