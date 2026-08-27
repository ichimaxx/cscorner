import static myutils.Skrocenie_Print.*;
/*
Exercise 17: (2) Using the Cycle hierarchy from Exercise 1, add a balance( ) method to
Unicycle and Bicycle, but not to Tricycle. Create instances of all three types and upcast
them to an array of Cycle. Try to call balance( ) on each element of the array and observe
the results. Downcast and call balance( ) and observe what happens.
*/
enum Rowery {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
class Cycle_5 {
	public void drive() {
		println("Rozdzial_8.Cycle.drive()");
	}
}

class Bicycle_5 extends Cycle_5 {
	@Override
	public void drive() {
		println("Rozdzial_8.Bicycle.drive() jest to " + Rowery.DWUKOLOWIEC);
	}
	public void balance() {
		println("Rozdzial_8.Bicycle balance is BIS");
	}
}

class Unicycle_5 extends Cycle_5 {
	@Override
	public void drive() {
		println("Rozdzial_8.Unicycle.drive() jest to " + Rowery.JEDNOKOLOWIEC);
	}
	public void balance() {
		println("Rozdzial_8.Unicycle balance is bad");
	}
}

class Tricycle_5 extends Cycle_5 {
	@Override
	public void drive() {
		println("Rozdzial_8.Tricycle.drive() jest to " + Rowery.TRZYKOLOWIEC);
	}
}

public class Zad8_17{
	public static void ride(Cycle_5 k) {
		k.drive();
	}
	public static void main (String[] args) {
		ride(new Unicycle_5());
		ride(new Bicycle_5());
		ride(new Tricycle_5());

		Cycle_5[] x = {
		new Unicycle_5(),
		new Bicycle_5(),
		new Tricycle_5()
	};
	((Unicycle_5)x[0]).balance();
	((Bicycle_5)x[1]).balance();
	((Bicycle_5)x[2]).balance();
	
	/* Jeśli zrobimy tak jak w zadaniu dostaniemy taki błąd:
	
	Exception in thread "main" java.lang.ClassCastException: class Rozdzial_8.Tricycle cannot be cast to class Rozdzial_8.Bicycle (Rozdzial_8.Tricycle and Rozdzial_8.Bicycle are in unnamed module of loader 'app')
        at Rozdzial_8.Zad8_17.main(Rozdzial_8.Zad8_17.java:55)
	
	Ze względu na to ze w Rozdzial_8.Tricycle nie ma balance, Tzw ClassCastException
	
	
	for (Rozdzial_8.Cycle c : x) {
		if (c instanceof Rozdzial_8.Unicycle) {
			((Rozdzial_8.Unicycle) c).balance();
		} else if (c instanceof Rozdzial_8.Bicycle) {
			((Rozdzial_8.Bicycle) c).balance();
		} else {
			println("Brak balance() dla: " + c.getClass().getSimpleName());
		}
	}	
			ALTERNATYWA ZEBY ODPALALO BEZ BLEDU*/
	}
}