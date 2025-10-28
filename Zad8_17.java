import static myutils.Skrocenie_Print.*;

enum Rowery {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
class Cycle {
	public void drive() {
		println("Cycle.drive()");
	}
}

class Bicycle extends Cycle {
	@Override
	public void drive() {
		println("Bicycle.drive() jest to " + Rowery.DWUKOLOWIEC);
	}
	public void balance() {
		println("Bicycle balance is BIS");
	}
}

class Unicycle extends Cycle {
	@Override
	public void drive() {
		println("Unicycle.drive() jest to " + Rowery.JEDNOKOLOWIEC);
	}
	public void balance() {
		println("Unicycle balance is bad");
	}
}

class Tricycle extends Cycle {
	@Override
	public void drive() {
		println("Tricycle.drive() jest to " + Rowery.TRZYKOLOWIEC);
	}
}

public class Zad8_17{
	public static void ride(Cycle k) {
		k.drive();
	}
	public static void main (String[] args) {
		ride(new Unicycle());
		ride(new Bicycle());
		ride(new Tricycle());
	
	Cycle[] x = {
		new Unicycle(),
		new Bicycle(),
		new Tricycle()
	};
	((Unicycle)x[0]).balance();
	((Bicycle)x[1]).balance();
	((Bicycle)x[2]).balance();
	
	/* Jeśli zrobimy tak jak w zadaniu dostaniemy taki błąd:
	
	Exception in thread "main" java.lang.ClassCastException: class Tricycle cannot be cast to class Bicycle (Tricycle and Bicycle are in unnamed module of loader 'app')
        at Zad8_17.main(Zad8_17.java:55)
	
	Ze względu na to ze w Tricycle nie ma balance, Tzw ClassCastException
	
	
	for (Cycle c : x) {
		if (c instanceof Unicycle) {
			((Unicycle) c).balance();
		} else if (c instanceof Bicycle) {
			((Bicycle) c).balance();
		} else {
			println("Brak balance() dla: " + c.getClass().getSimpleName());
		}
	}	
			ALTERNATYWA ZEBY ODPALALO BEZ BLEDU*/
	}
}