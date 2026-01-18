import static myutils.Skrocenie_Print.*;

/*Exercise 23: (4) Add a class with a dispose( ) method to the previous exercise. Modify
FailingConstructor so that the constructor creates one of these disposable objects as a
member object, after which the constructor might throw an exception, after which it creates a
second disposable member object. Write code to properly guard against failure, and in
main( ) verify that all possible failure situations are covered. */

class NeedsCleanups { // Construction can’t fail
	private static long counter = 1;
	private final long id = counter++;
	public void dispose() {
		System.out.println("NeedsCleanups " + id + " disposed");
	}
} 
class ConstructionException extends Exception {} 

class FailingConstructors {
	// Construction can fail:
	private NeedsCleanups first;
	private NeedsCleanups second;
	
	public FailingConstructors(boolean fail) throws ConstructionException {
		println("FailingConstructors constructor start");
		first = new NeedsCleanups();
		try {
			if (fail) {
				throw new ConstructionException();
			}
				second = new NeedsCleanups();
		} catch(ConstructionException e) {
			first.dispose();
			throw e;
		}
		println("FailingConstructors constructor end");
	}
} 

public class Zad12_23 {
	public static void main(String[] args) {
		FailingConstructors fc1 = null;
		FailingConstructors fc2 = null;
		try {
			fc1 = new FailingConstructors(true); // tutaj (true, false) wybieramy czy sie ma konstruktor wywalic i rzucic exception czy nie
			println("Uzywam obiekt fc1...");
		} catch(ConstructionException e) {
			System.out.println("fc 1 construction failed and catched: \nmessage:" + " '" + e.getMessage() + "' " );
		} finally {
		}
		try {
			fc2 = new FailingConstructors(false); // tutaj (true, false) wybieramy czy sie ma konstruktor wywalic i rzucic exception czy nie
			println("Uzywam obiekt fc2...");
		} catch(ConstructionException e) {
			System.out.println("fc 2 construction failed and catched: \nmessage:" + " '" + e.getMessage() + "' " );
		} finally {
		}
	}
}
// jeśli konstruktor rzuci wyjątek po utworzeniu części pól, to obiekt docelowy nie powstaje (fc == null), więc sprzątanie tych pól musi się odbyć w samym konstruktorze (np. try/catch w konstruktorze), bo main() nie ma już jak wywołać dispose()

// teraz by sie to zrobilo z try-with-resources czyli autocloseable, czysci od razu po try