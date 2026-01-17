import static myutils.Skrocenie_Print.*;

/*Exercise 22: (2) Create a class called FailingConstructor with a constructor that
might fail partway through the construction process and throw an exception. In main( ),
write code that properly guards against this failure. */
class NeedsCleanup { // Construction can’t fail
	private static long counter = 1;
	private final long id = counter++;
	public void dispose() {
		System.out.println("NeedsCleanup " + id + " disposed");
	}
} 
class ConstructionException extends Exception {} 

class FailingConstructor extends NeedsCleanup {
	// Construction can fail:
	public FailingConstructor(boolean fail) throws ConstructionException {
		println("failing constructor");
		if (fail) {
			throw new ConstructionException();
		}
	}
	public void dispose() {
		System.out.println("Failing constructor disposed");
	}
} 

public class Zad12_22 {
	public static void main(String[] args) {
		FailingConstructor fc = null;
		try {
			fc = new FailingConstructor(true); // tutaj (true, false) wybieramy czy sie ma konstruktor wywalic i rzucic exception czy nie
			println("Uzywam obiekt...");
		} catch(ConstructionException e) {
			System.out.println("construction failed and catched: \nmessage:" + " '" + e.getMessage() + "' " );
		} finally {
			if (fc != null) 
				fc.dispose();
		}
	}
}

// jeśli konstruktor rzuci wyjątek, obiekt nie zostanie utworzony (fc zostaje null) wiec wyjatek lapie catch w maiN() w finally nie wywola sie dispose() bo jest w if