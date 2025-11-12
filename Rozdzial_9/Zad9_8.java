import static myutils.Skrocenie_Print.*;

interface FastFood {
	void fastfood();
}

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
public class Zad9_8 extends PortableLunch implements FastFood {
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	private Lettuce l = new Lettuce();
	private Pickle p = new Pickle();
	public Zad9_8() { 
		println("Sandwich()");
	}
	@Override
	public void fastfood() {
		println("FastFood()");
	}
	public static void main(String[] args) {
	Zad9_8 k = new Zad9_8();
	k.fastfood();
	}
}