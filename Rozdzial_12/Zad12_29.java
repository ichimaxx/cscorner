import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 29: (1) Modify all the exception types in Stormylnning.java so that they
extend RuntimeException, and show that no exception specifications or try blocks are
necessary. Remove the ‘//!’ comments and show how the methods can be compiled without
specifications. */

class BaseballExceptiones extends RuntimeException {}
class Fouls extends BaseballExceptiones {}
class Strikes extends BaseballExceptiones {}
abstract class Innings {
	public Innings(){}
	public void event() {
		// Doesn’t actually have to throw anything
	}
	public abstract void atBat();
	public void walk() {} // Throws no checked exceptions
}
class StormExceptions extends RuntimeException {}
class RainedOuts extends StormExceptions {}
class PopFouls extends Fouls {}
interface Storms {
	public void event();
	public void rainHard();
}
public class Zad12_29 extends Innings implements Storms {
	// OK to add new exceptions for constructors, but you
	// must deal with the base constructor exceptions:
	public Zad12_29() {}
	public Zad12_29(String s) {}
	// Regular methods must conform to base class:
	public void walk(){} 
	public void event(){}
	// If the method doesn’t already exist in the
	// base class, the exception is OK:
	public void rainHard(){}
	// even if the base version does:
	// Overridden methods can throw inherited exceptions:
	public void atBat(){ 
		throw new Strikes(); // wrzucony wyjatek zeby stestowac bez try, powinno po odpaleniu wywalic StackTrace
	}
	public static void main(String[] args) {
		Zad12_29 si = new Zad12_29();
		si.atBat();
		// What happens if you upcast?
		Innings i = new Zad12_29(); // TEN PROGRAM TU NIE DOJDZIE BO si.atBat() robi throw wyjątku
		i.atBat();
		// You must catch the exceptions from the
		// base-class version of the method:
	}
}
		// You can choose to not throw any exceptions,
		
		// przerobione StormyInning z ksiazki na Unchecked po to zeby kompilator nie wymuszal try/catch i throws, jak widac usuniete sa wszystkie try/catch, co jest dozwolone przy UncheckedExceptions i throws z metod. Program sie kompiluje