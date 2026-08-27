import static myutils.Skrocenie_Print.*;
/*
Exercise 11: (1) Add class Pickle to Sandwich.java.
*/
class Meal {
	Meal() {
		println("Rozdzial_8.Meal()");
	}
}
class Bread {
	Bread() {
		println("Rozdzial_8.Bread()");
	}
}
class Cheese {
	Cheese() {
		println("Rozdzial_8.Cheese()");
	}
}
class Lettuce {
	Lettuce() { 
		println("Rozdzial_8.Lettuce()");
	}
}
class Pickle {
	Pickle() {
		println("Rozdzial_8.Pickle()");
	}
}
class Lunch extends Meal {
	Lunch() { 
		println("Rozdzial_8.Lunch()");
	}
}
class PortableLunch extends Lunch {
	PortableLunch() { 
		println("Rozdzial_8.PortableLunch()");
	}
}
public class Zad8_11 extends PortableLunch {
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	private Lettuce l = new Lettuce();
	private Pickle p = new Pickle();
	public Zad8_11() { 
		println("Sandwich()");
	}
	public static void main(String[] args) {
	new Zad8_11();
	}
}