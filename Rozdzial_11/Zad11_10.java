import static myutils.Skrocenie_Print.*;
import java.util.*; 

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

class Gerbilz extends Rodent {
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

public class Zad11_10 {	

	public static ArrayList<Rodent> arrayList(int size) {
		return new ArrayList<Rodent>(size);
	}
	
	public static void mix(Rodent r) {
		print(r);
		r.toy();
		r.voice(Sound.MEEP);
	}
	public static void mixAll(Iterator<Rodent> rodentz) {
		while (rodentz.hasNext()) {
            Rodent r = rodentz.next();
				mix(r);
        }
	}
	public static void main(String[] args) {
		ArrayList<Rodent> rodents = arrayList(3); 
		rodents.add(new Hamster());
		rodents.add(new Gerbilz());
		rodents.add(new Mouse());
		Iterator<Rodent> it = rodents.iterator();
		mixAll(it);
	}
}