import static myutils.Skrocenie_Print.*;
/*
Exercise 8: (2) In polymorphism.Sandwich.java, create an interface called
FastFoo d (with appropriate methods) and change Sandwic h so that it also implements
FastFood.
*/
interface FastFood {
	void fastfood();
}

class Meal_2 {
	Meal_2() {
		println("Meal()"); 
	}
}
class Bread_2 {
	Bread_2() {
		println("Bread()"); 
	}
}
class Cheese_2 {
	Cheese_2() {
		println("Cheese()");
	}
}
class Lettuce_2 {
	Lettuce_2() {
		println("Lettuce()"); 
	}
}
class Pickle_2 {
	Pickle_2() {
		println("Pickle()");
	}
}
class Lunch_2 extends Meal_2 {
	Lunch_2() {
		println("Lunch()"); 
	}
}
class PortableLunch_2 extends Lunch_2 {
	PortableLunch_2() {
		println("PortableLunch()");
	}
}
public class Zad9_8 extends PortableLunch_2 implements FastFood {
	private Bread_2 b = new Bread_2();
	private Cheese_2 c = new Cheese_2();
	private Lettuce_2 l = new Lettuce_2();
	private Pickle_2 p = new Pickle_2();
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