import static net.mindview.util.Print.*;
import java.util.*;

/*Exercise 20: (3) Modify StormyInning.java by adding an UmpireArgument
exception type and methods that throw this exception. Test the modified hierarchy. */

class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}
class UmpireArgument extends BaseballException {}
abstract class Inning {
	public Inning() throws BaseballException {}
	public void event() throws BaseballException {
		// Doesn’t actually have to throw anything
	}
	public void newumpire() throws UmpireArgument {
		throw new UmpireArgument();
	}
	public abstract void atBat() throws Strike, Foul;
	public void walk() {} // Throws no checked exceptions
}
class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}
interface Storm {
	public void event() throws RainedOut;
	public void rainHard() throws RainedOut;
}

public class Zad12_20 extends Inning implements Storm {
	// OK to add new exceptions for constructors, but you
	// must deal with the base constructor exceptions:
	public Zad12_20() throws RainedOut, BaseballException {}
	public Zad12_20(String s) throws Foul, BaseballException {}
	// Regular methods must conform to base class:
	//! void walk() throws PopFoul {} //Compile error
	// Interface CANNOT add exceptions to existing
	// methods from the base class:
	//! public void event() throws RainedOut {}
	// If the method doesn’t already exist in the
	// base class, the exception is OK:
	public void rainHard() throws RainedOut {}
	// You can choose to not throw any exceptions,
	// even if the base version does:
	public void event() {}
	// Overridden methods can throw inherited exceptions:
	public void atBat() throws PopFoul {}
	public static void main(String[] args) {
		try {
			Zad12_20 si = new Zad12_20();
			si.atBat();
		} catch(PopFoul e) {
			System.out.println("Pop foul");
		} catch(RainedOut e) {
			System.out.println("Rained out");
		} catch(BaseballException e) {
			System.out.println("Generic baseball exception");
		}
		// Strike not thrown in derived version.
		try {
			// What happens if you upcast?
			Inning i = new Zad12_20();
			i.atBat();
			i.newumpire();
			// You must catch the exceptions from the
			// base-class version of the method:
		} catch(Strike e) {
			System.out.println("Strike");
		} catch(Foul e) {
			System.out.println("Foul");
		} catch(RainedOut e) {
			System.out.println("Rained out");
		} catch(UmpireArgument e) {
			System.out.println("UmpireArgument Exception"); // tu trzeba dodac w hierarchi w tej sytuacji jak chcemy zlapac ten konkretny wyjatek, mozna tez tego nie pisac i BaseballException go zlapie
		} catch(BaseballException e) {
			System.out.println("Generic baseball // UmpireArgument exception");
		}
	}
}

/*
- w metodzie ktora jest override nie mozna dodac nowego checked exception, trzeba uzyc tego z klasy bazowej. 
- nowa metoda w inherited class moze miec dowolne throws bo nie jest override ^ wiec nie obowiazuja ją ograniczenia z metody bazowej
- jak robisz upcasting typu Inning i = new Zad12_20(); to wymusza zeby lapac wyjatki wedlug Inning a nie wg obiektu Zad12_20
*/