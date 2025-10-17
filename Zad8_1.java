import static myutils.Skrocenie_Print.*;

enum Rowery {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
class Cycle {
	public void drive(Rowery r) {
		println("Cycle.drive()");
	}
}

class Bicycle extends Cycle {
	@Override
	public void drive(Rowery r) {
		println("Bicycle.drive() jest to " + Rowery.DWUKOLOWIEC);
	}
}

class Unicycle extends Cycle {
	@Override
	public void drive(Rowery r) {
		println("Unicycle.drive() jest to " + Rowery.JEDNOKOLOWIEC);
	}
}

class Tricycle extends Cycle {
	@Override
	public void drive(Rowery r) {
		println("Tricycle.drive() jest to " + Rowery.TRZYKOLOWIEC);
	}
}

public class Zad8_1{
	public static void ride(Cycle k) {
		k.drive(Rowery.JEDNOKOLOWIEC);
	}
	public static void main (String[] args) {
		ride(new Unicycle());
		ride(new Bicycle());
		ride(new Tricycle());
	}
}