import static myutils.Skrocenie_Print.*;
/*
Exercise 9: (3) Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster,
etc. In the base class, provide methods that are common to all Rodents, and override these
in the derived classes to perform different behaviors depending on the specific type of
Rodent. Create an array of Rodent, fill it with different specific types of Rodents, and call
your base-class methods to see what happens.
*/
enum Sound_3 {
	MEEP, EEP, SQUEAK;
}

class Rodent_3 {
	void voice(Sound_3 s) {
		println("Rozdzial_8.Rodent.sound()" + s);
	}
	String what() {
		return "Rozdzial_8.Rodent";
	}
	@Override
	public String toString() {
		return what();
	}
	void toy() {
		println("Toy");
	}
}

class Hamster_3 extends Rodent_3 {
	@Override
	void voice(Sound_3 s) {
		println("Rozdzial_8.Hamster.sound() is " + Sound_3.SQUEAK);
	}
	@Override
	String what() {
		return "Rozdzial_8.Hamster";
	}
	@Override
	void toy()
	{
		println("lovely toy is Exercise wheel");
	}
}

class Gerbil_3 extends Rodent_3 {
	@Override
	void voice(Sound_3 s) {
		println("Rozdzial_8.Gerbil.sound() is " + Sound_3.EEP);
	}
	@Override
	String what() {
		return "Rozdzial_8.Gerbil";
	}
	@Override
	void toy() {
		println("lovely toy is Tunnel");
	}
}	

class Mouse_3 extends Rodent_3 {
	@Override
	void voice(Sound_3 s) {
		println("Rozdzial_8.Mouse.sound() is " + Sound_3.MEEP);
	}
	@Override
	String what() {
		return "Rozdzial_8.Mouse";
	}
	@Override
	void toy() {
		println("lovely toy is Rozdzial_8.Mouse house");
	}
}

public class Zad8_9 {	
	public static void mix(Rodent_3 r) {
		print(r);
		r.toy();
		r.voice(Sound_3.MEEP);
	}
	public static void mixAll(Rodent_3[] e) {
		for(Rodent_3 r : e)
			mix(r);
	}
	public static void main(String[] args) {
		Rodent_3[] g = {
			new Hamster_3(),
			new Gerbil_3(),
			new Mouse_3()
		};
	mixAll(g);
	}
}