import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (1) Change Exercise 9 in the Polymorphism chapter so that Rodent is an
interface.
*/
enum Sound_5 {
	MEEP, EEP, SQUEAK;
}

interface Rodent_5 {
	void voice(Sound_5 s);
	
	String what();
	
	void toy();
	
	public String toString();
}

class Hamster_5 implements Rodent_5 {
	@Override
	public void voice(Sound_5 s) {
		println("Hamster.sound() is " + Sound_5.SQUEAK);
	}
	@Override
	public String what() {
		return "Hamster";
	}
	
	@Override
	public String toString() {
		return what();
	}
	@Override
	public void toy() {
		println("lovely toy is Exercise wheel");
	}
}

class Gerbil_5 implements Rodent_5 {
	@Override
	public void voice(Sound_5 s) {
		println("Gerbil.sound() is " + Sound_5.EEP);
	}
	@Override
	public String what() {
		return "Gerbil";
	}
	@Override
	public String toString() {
		return what();
	}
	@Override
	public void toy() {
		println("lovely toy is Tunnel");
	}
}	

class Mouse_5 implements Rodent_5 {
	@Override
	public void voice(Sound_5 s) {
		println("Mouse.sound() is " + Sound_5.MEEP);
	}
	@Override
	public String what() {
		return "Mouse";
	}
	@Override
	public String toString() {
		return what();
	}
	@Override
	public void toy() {
		println("lovely toy is Mouse house");
	}
}

public class Zad9_7 {	
	public static void mix(Rodent_5 r) {
		print(r);
		r.toy();
		r.voice(Sound_5.MEEP);
	}
	public static void mixAll(Rodent_5[] e) {
		for(Rodent_5 r : e)
			mix(r);
	}
	public static void main(String[] args) {
		Rodent_5[] g = {
			new Hamster_5(),
			new Gerbil_5(),
			new Mouse_5()
		};
	mixAll(g);
	}
}