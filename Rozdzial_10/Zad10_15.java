import static myutils.Skrocenie_Print.*;
/*
Exercise 15: (2) Create a class with a non-default constructor (one with arguments) and
no default constructor (no "no-arg" constructor). Create a second class that has a method
that returns a reference to an object of the first class. Create the object that you return by
making an anonymous inner class that inherits from the first class.
*/
class Klaska {
	private int f;
	Klaska(int f) {
		this.f = f;
		println("konstruktor klasy Klaska i jego liczba: " + f);
	}
}

public class Zad10_15 {
	public Klaska klasa() { 
		return new Klaska(100) {
			{
			}
			
		};
	}
	public static void main(String[] args) {
		Zad10_15 o = new Zad10_15();
		o.klasa();
	}
}