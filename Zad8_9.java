import static myutils.Skrocenie_Print.*;

enum Sound {
	MEEP, EEP, SQUEAK;
}

class Rodent {
	void voice(Sound s) {
		println("Rodent.sound()" + s);
	}
	String what() {
		return "Rodent";
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
		println("Hamster.sound() is " + Sound.SQUEAK);
	}
	@Override
	String what() {
		return "Hamster";
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
		println("Gerbil.sound() is " + Sound.EEP);
	}
	@Override
	String what() {
		return "Gerbil";
	}
	@Override
	void toy() {
		println("lovely toy is Tunnel");
	}
}	

class Mouse extends Rodent {
	@Override
	void voice(Sound s) {
		println("Mouse.sound() is " + Sound.MEEP);
	}
	@Override
	String what() {
		return "Mouse";
	}
	@Override
	void toy() {
		println("lovely toy is Mouse house");
	}
}

public class Zad8_9 {	
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