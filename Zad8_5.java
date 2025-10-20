import static myutils.Skrocenie_Print.*;

enum Rowery {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
enum Kola {
	Jedno, Dwa, Trzy;
}
class Cycle {
	public void drive() {}
	public void wheels(){}
}

class Bicycle extends Cycle {
	@Override
	public void drive() {
		print("Bicycle.drive() jest to " + Rowery.DWUKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("2 koła");
	}		
}

class Unicycle extends Cycle {
	@Override
	public void drive() {
		print("Unicycle.drive() jest to " + Rowery.JEDNOKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("1 koło");
	}	
}

class Tricycle extends Cycle {
	@Override
	public void drive() {
		print("Tricycle.drive() jest to " + Rowery.TRZYKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("3 koła");
	}
}

public class Zad8_5{
	public static void ride(Cycle k) {
		k.drive();
		k.wheels();
	}
	public static void main (String[] args) {
		ride(new Unicycle());
		ride(new Bicycle());
		ride(new Tricycle());
	}
}