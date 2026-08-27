import static myutils.Skrocenie_Print.*;
import java.util.Random;
/*
Exercise 8: (2) Modify Music3.java so that it randomly creates Instrument objects
the way Shapes.java does.
*/
enum Note_3 {
	MIDDLE_C, C_SHARP, B_FLAT;
}
class Instrument_3 {
	void play(Note_3 n) {
		println("Rozdzial_8.Instrument.play() " + n);
	}
	String what() { 
		return "Rozdzial_8.Instrument";
	}
	@Override
	public String toString() {
		return what();
	}
	void adjust() {
		print("Adjusting Rozdzial_8.Instrument");
	}
}
class Wind_3 extends Instrument_3 {
	@Override
	void play(Note_3 n) {
		println("Rozdzial_8.Wind.play() " + n);
	}
	@Override
	String what() {
		return "Rozdzial_8.Wind";
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Wind");
	}
}
class Percussion_3 extends Instrument_3 {
	@Override
	void play(Note_3 n) {
		println("Rozdzial_8.Percussion.play() " + n);
	}
	@Override
	String what() { 
		return "Rozdzial_8.Percussion";
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Percussion");
	}
}
class Stringed_3 extends Instrument_3 {
	@Override
	void play(Note_3 n) {
		println("Rozdzial_8.Stringed.play() " + n);
	}
	@Override
	String what() { 
		return "Rozdzial_8.Stringed";
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Stringed");
	}
}
class Brass_3 extends Wind_3 {
	void play(Note_3 n) {
		println("Rozdzial_8.Brass.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Brass");
	}
}
class Woodwind_3 extends Wind_3 {
	void play(Note_3 n) {
		println("Rozdzial_8.Woodwind.play() " + n);
	}
	@Override
	String what() { 
		return "Rozdzial_8.Woodwind";
	}
}
class RandomInstrumentGenerator_3 {
	private Random rand = new Random();
	public Instrument_3 next() {
		switch(rand.nextInt(5)) {
			default:
			case 0: return new Wind_3();
			case 1: return new Woodwind_3();
			case 2: return new Brass_3();
			case 3: return new Stringed_3();
			case 4: return new Percussion_3();
		}
	}
}
public class Zad8_8 {
 // Doesn’t care about type, so new types
 // added to the system still work right:
	public static void tune(Instrument_3 i) {
		println(i);
		i.play(Note_3.MIDDLE_C);
		
	}
	private static RandomInstrumentGenerator_3 gen = new RandomInstrumentGenerator_3();
	
	
	public static void tuneAll(Instrument_3[] e) {
		for(Instrument_3 i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Instrument_3[] orchestra = new Instrument_3[7];
		for(int i = 0; i < orchestra.length; i++) {
			orchestra[i] = gen.next();
		}
		tuneAll(orchestra);
	}
}