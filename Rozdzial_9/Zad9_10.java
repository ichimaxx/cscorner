import static myutils.Skrocenie_Print.*;

enum Note_4 {
	MIDDLE_C, C_SHARP, B_FLAT;
}
interface Playable {
	void play(Note n);
}
abstract class Instrument_4{
	String what() { 
		return "Instrument";
	}
	@Override
	public String toString() {
		return what();
	}
	abstract void adjust();
}
class Wind_4 extends Instrument_4 implements Playable  {
	@Override
	public void play(Note_4 n) {
		println("Wind.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Wind");
	}
}
class Percussion_4 extends Instrument_4 implements Playable  {
	@Override
	public void play(Note_4 n) {
		println("Percussion.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Percussion"); 
	}
}
class Stringed_4 extends Instrument_4 implements Playable  {
	@Override
	public void play(Note_4 n) {
		println("Stringed.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Stringed"); 
	}
}
class Brass_4 extends Wind_4 {
	@Override
	public void play(Note_4 n) {
		println("Brass.play() " + n); 
	}
	@Override
	void adjust() { 
		print("Adjusting Brass"); 
	}
}
class Woodwind_4 extends Wind_4 {
	@Override
	public void play(Note_4 n) {
		println("Woodwind.play() " + n); 
	}
}
public class Zad9_10 {
 // Doesn’t care about type, so new types
 // added to the system still work right:
	public static void tune(Playable p) {
		println(p);
		p.play(Note_4.MIDDLE_C);
		
	}
	public static void tuneAll(Playable[] e) {
		for(Playable i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Playable[] orchestra = {
			new Wind_4(),
			new Percussion_4(),
			new Stringed_4(),
			new Brass_4(),
			new Woodwind_4(),
			};
		tuneAll(orchestra);
	}
}