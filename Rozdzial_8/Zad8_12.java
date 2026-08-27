import static myutils.Skrocenie_Print.*;
/*
Exercise 12: (3) Modify Exercise 9 so that it demonstrates the order of initialization of
the base classes and derived classes. Now add member objects to both the base and derived
classes and show the order in which their initialization occurs during construction.
*/
enum Sound {
	MEEP, EEP, SQUEAK;
}
class Odpalanie {
	String f;
	Odpalanie(String f) {
		this.f = f;
		println(f);
	}
}
class Rodent{
	void voice(Sound s) {
		println("Rozdzial_8.Rodent.sound()" + s);
	}
	private Odpalanie o = new Odpalanie("klasa druga, rodent");
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

class Hamster extends Rodent {
	@Override
	void voice(Sound s) {
		println("Rozdzial_8.Hamster.sound() is " + Sound.SQUEAK);
	}
	private Odpalanie o = new Odpalanie("klasa trzecia, hamster");

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

class Gerbil extends Rodent {
	@Override
	void voice(Sound s) {
		println("Rozdzial_8.Gerbil.sound() is " + Sound.EEP);
	}
	private Odpalanie o = new Odpalanie("klasa czwarta, gerbil");	
	@Override
	String what() {
		return "Rozdzial_8.Gerbil";
	}
	@Override
	void toy() {
		println("lovely toy is Tunnel");
	}
}	

class Mouse extends Rodent {
	@Override
	void voice(Sound s) {
		println("Rozdzial_8.Mouse.sound() is " + Sound.MEEP);
	}
	private Odpalanie o = new Odpalanie("klasa piata, mouse");	
	@Override
	String what() {
		return "Rozdzial_8.Mouse";
	}
	@Override
	void toy() {
		println("lovely toy is Rozdzial_8.Mouse house");
	}
}

public class Zad8_12 {	
	private static Odpalanie o = new Odpalanie("klasa ostatnia, publiczna zad8_12");
	public static void mix(Rodent r) {
		print(r);
		r.toy();
		r.voice(Sound.MEEP);
	}
	public static void mixAll(Rodent[] e) {
		for(Rodent r : e)
			mix(r);
	}
	public static void main(String[] args) {
		Rodent[] g = {
			new Hamster(),
			new Gerbil(),
			new Mouse()
		};
	mixAll(g);
	}
}