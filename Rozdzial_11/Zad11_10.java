import static myutils.Skrocenie_Print.*;
import java.util.*; 
/*
Exercise 10: (2) Change Exercise 9 in the Polymorphism chapter to use an ArrayList to
hold the Rodents and an Iterator to move through the sequence of Rodents.
*/
enum Sound_6 {
	MEEP, EEP, SQUEAK;
}

class Rodent_6 {
	void voice(Sound_6 s) {
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

class Hamster_6 extends Rodent_6 {
	@Override
	void voice(Sound_6 s) {
		println("Hamster.sound() is " + Sound_6.SQUEAK);
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

class Gerbil_6 extends Rodent_6 {
	@Override
	void voice(Sound_6 s) {
		println("Gerbil.sound() is " + Sound_6.EEP);
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

class Mouse_6 extends Rodent_6 {
	@Override
	void voice(Sound_6 s) {
		println("Mouse.sound() is " + Sound_6.MEEP);
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

	public static ArrayList<Rodent_6> arrayList(int size) {
		return new ArrayList<Rodent_6>(size);
	}
	
	public static void mix(Rodent_6 r) {
		print(r);
		r.toy();
		r.voice(Sound_6.MEEP);
	}
	public static void mixAll(Iterator<Rodent_6> rodentz) {
		while (rodentz.hasNext()) {
			Rodent_6 r = rodentz.next();
				mix(r);
        }
	}
	public static void main(String[] args) {
		ArrayList<Rodent_6> rodents = arrayList(3);
		rodents.add(new Hamster_6());
		rodents.add(new Gerbil_6());
		rodents.add(new Mouse_6());
		Iterator<Rodent_6> it = rodents.iterator();
		mixAll(it);
	}
}