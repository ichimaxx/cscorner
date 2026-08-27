import static myutils.Skrocenie_Print.*;
/*
Exercise 13: (2) Create a class with a method that is overloaded three times. Inherit a
new class, add a new overloading of the method, and show that all four methods are available
in the derived class.
*/
class Miarka {
	int vol(int c) {
		println("vol(int)in a wartość: " + c);
		return 1;
	}
	char vol(char b) {
		println("vol(char)i a wartość: " + b);
		return 'i';
	}
	
	float vol(float f) {
		println("vol(float)inch a wartość: " + f);
		return 1.0f;
	}
}
class Laser extends Miarka {
	String vol(String k) {
		println("vol(String)INCZE a wartość: " + k);
		return "incze";
	}
	@Override	
	int vol(int c) {
		println("Rozdzial_7.Laser.vol po override(int) -> " + c);
		return super.vol(5);
	}
}
public class Zad7_13 {
	public static void main(String[] args) {
		Laser m = new Laser();
		m.vol(155);
		m.vol(2.0f);
		m.vol("inczebnincze");
		m.vol('i');
	}
}