import static myutils.Skrocenie_Print.*;

enum Sound {
	MEEP, EEP, SQUEAK;
}

interface Rodent {
	void voice(Sound s);
	
	String what();
	
	void toy();
	
	public String toString();
}

class Hamster implements Rodent {
	@Override
	public void voice(Sound s) {
		println("Hamster.sound() is " + Sound.SQUEAK);
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

class Gerbil implements Rodent {
	@Override
	public void voice(Sound s) {
		println("Gerbil.sound() is " + Sound.EEP);
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

class Mouse implements Rodent {
	@Override
	public void voice(Sound s) {
		println("Mouse.sound() is " + Sound.MEEP);
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