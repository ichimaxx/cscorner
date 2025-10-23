import static myutils.Skrocenie_Print.*;

class Meal {
	Meal() {
		println("Meal()"); 
	}
}
class Bread {
	Bread() {
		println("Bread()"); 
	}
}
class Cheese {
	Cheese() {
		println("Cheese()");
	}
}
class Lettuce {
	Lettuce() { 
		println("Lettuce()"); 
	}
}
class Pickle {
	Pickle() {
		println("Pickle()");
	}
}
class Lunch extends Meal {
	Lunch() { 
		println("Lunch()"); 
	}
}
class PortableLunch extends Lunch {
	PortableLunch() { 
		println("PortableLunch()");
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