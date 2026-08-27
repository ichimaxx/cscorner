import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (1) Starting from Exercise 1, add a wheels( ) method in Cycle, which
returns the number of wheels. Modify ride( ) to call wheels( ) and verify that
polymorphism works.
*/
enum Rowery_2 {
	JEDNOKOLOWIEC, DWUKOLOWIEC, TRZYKOLOWIEC;
}
enum Kola_2 {
	Jedno, Dwa, Trzy;
}
class Cycle_2 {
	public void drive() {}
	public void wheels(){}
}

class Bicycle_2 extends Cycle_2 {
	@Override
	public void drive() {
		print("Rozdzial_8.Bicycle.drive() jest to " + Rowery_2.DWUKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("2 koła");
	}		
}

class Unicycle_2 extends Cycle_2 {
	@Override
	public void drive() {
		print("Rozdzial_8.Unicycle.drive() jest to " + Rowery_2.JEDNOKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("1 koło");
	}	
}

class Tricycle_2 extends Cycle_2 {
	@Override
	public void drive() {
		print("Rozdzial_8.Tricycle.drive() jest to " + Rowery_2.TRZYKOLOWIEC + " i ma ");
	}
	@Override
	public void wheels() {
		println("3 koła");
	}
}

public class Zad8_5{
	public static void ride(Cycle_2 k) {
		k.drive();
		k.wheels();
	}
	public static void main (String[] args) {
		ride(new Unicycle_2());
		ride(new Bicycle_2());
		ride(new Tricycle_2());
	}
}