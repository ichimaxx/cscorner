import static myutils.Skrocenie_Print.*;

/*Exercise 24: (3) Add a dispose( ) method to the FailingConstructor class and write
code to properly use this class. */

class NeedsCleanups { // Construction can’t fail
	private static long counter = 1;
	private final long id = counter++;
	public void dispose() {
		System.out.println("NeedsCleanups " + id + " disposed");
	}
} 
class ConstructionException extends Exception {} 

class FailingConstructors extends NeedsCleanups {
	// Construction can fail:
	private NeedsCleanups first;
	private NeedsCleanups second;
	
	public FailingConstructors(boolean fail) throws ConstructionException {
		println("failing constructor");
		first = new NeedsCleanups();
		try {
			if (fail)
				throw new ConstructionException();
			second = new NeedsCleanups();
		} catch (ConstructionException e) {
			if (first != null) first.dispose();
			throw e;
		}
		
	}
	public void dispose() {
		if (second != null) second.dispose();
		if (first != null) first.dispose();
		super.dispose();
		System.out.println("Failing constructor disposed");
	}
} 

public class Zad12_24 {
	public static void main(String[] args) {
		FailingConstructors fc1 = null;
		FailingConstructors fc2 = null;
		try {
			fc1 = new FailingConstructors(true); // tutaj (true, false) wybieramy czy sie ma konstruktor wywalic i rzucic exception czy nie
			println("Uzywam obiekt fc1...");
		} catch(ConstructionException e) {
			System.out.println("fc 1 construction failed and catched: \nmessage:" + " '" + e.getMessage() + "' " );
		} finally {
			if (fc1 != null) 
				fc1.dispose();
		}
		try {
			fc2 = new FailingConstructors(false); // tutaj (true, false) wybieramy czy sie ma konstruktor wywalic i rzucic exception czy nie
			println("Uzywam obiekt fc2...");
		} catch(ConstructionException e) {
			System.out.println("fc 2 construction failed and catched: \nmessage:" + " '" + e.getMessage() + "' " );
		} finally {
			if (fc2 != null) 
				fc2.dispose();
		}
	}
}
// jeśli konstruktor rzuci wyjątek po utworzeniu części pól, to obiekt docelowy nie powstaje (fc == null), więc sprzątanie tych pól musi się odbyć w samym konstruktorze (np. try/catch w konstruktorze), bo main() nie ma już jak wywołać dispose()

// teraz by sie to zrobilo z try-with-resources czyli autocloseable, czysci od razu po try