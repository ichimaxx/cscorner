import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (2) Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle.
Demonstrate that an instance of each type can be upcast to Cycle via a ride( ) method.
*/
enum Rowery_1 {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
class Cycle_1 {
	public void drive(Rowery_1 r) {
		println("Rozdzial_8.Cycle.drive()");
	}
}

class Bicycle_1 extends Cycle_1 {
	@Override
	public void drive(Rowery_1 r) {
		println("Rozdzial_8.Bicycle.drive() jest to " + Rowery_1.DWUKOLOWIEC);
	}
}

class Unicycle_1 extends Cycle_1 {
	@Override
	public void drive(Rowery_1 r) {
		println("Rozdzial_8.Unicycle.drive() jest to " + Rowery_1.JEDNOKOLOWIEC);
	}
}

class Tricycle_1 extends Cycle_1 {
	@Override
	public void drive(Rowery_1 r) {
		println("Rozdzial_8.Tricycle.drive() jest to " + Rowery_1.TRZYKOLOWIEC);
	}
}

public class Zad8_1{
	public static void ride(Cycle_1 k) {
		k.drive(Rowery_1.JEDNOKOLOWIEC);
	}
	public static void main (String[] args) {
		ride(new Unicycle_1());
		ride(new Bicycle_1());
		ride(new Tricycle_1());
	}
}