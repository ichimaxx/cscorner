import static myutils.Skrocenie_Print.*;

enum Note {
	MIDDLE_C, C_SHARP, B_FLAT;
}
interface Playable {
	void play(Note n);
}
abstract class Instrument{
	String what() { 
		return "Instrument";
	}
	@Override
	public String toString() {
		return what();
	}
	abstract void adjust();
}
class Wind extends Instrument implements Playable  {
	@Override
	public void play(Note n) { 
		println("Wind.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Wind");
	}
}
class Percussion extends Instrument implements Playable  {
	@Override
	public void play(Note n) { 
		println("Percussion.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Percussion"); 
	}
}
class Stringed extends Instrument implements Playable  { 
	@Override
	public void play(Note n) { 
		println("Stringed.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Stringed"); 
	}
}
class Brass extends Wind {
	@Override
	public void play(Note n) { 
		println("Brass.play() " + n); 
	}
	@Override
	void adjust() { 
		print("Adjusting Brass"); 
	}
}
class Woodwind extends Wind {
	@Override
	public void play(Note n) { 
		println("Woodwind.play() " + n); 
	}
}
public class Zad9_10 {
 // Doesn’t care about type, so new types
 // added to the system still work right:
	public static void tune(Playable p) {
		println(p);
		p.play(Note.MIDDLE_C);
		
	}
	public static void tuneAll(Playable[] e) {
		for(Playable i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Playable[] orchestra = {
			new Wind(),
			new Percussion(),
			new Stringed(),
			new Brass(),
			new Woodwind(),
			};
		tuneAll(orchestra);
	}
}