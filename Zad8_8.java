import static myutils.Skrocenie_Print.*;
import java.util.Random;

enum Note {
	MIDDLE_C, C_SHARP, B_FLAT;
}
class Instrument {
	void play(Note n) { 
		println("Instrument.play() " + n);
	}
	String what() { 
		return "Instrument";
	}
	@Override
	public String toString() {
		return what();
	}
	void adjust() {
		print("Adjusting Instrument");
	}
}
class Wind extends Instrument {
	@Override
	void play(Note n) { 
		println("Wind.play() " + n);
	}
	@Override
	String what() {
		return "Wind"; 
	}
	@Override
	void adjust() { 
		print("Adjusting Wind");
	}
}
class Percussion extends Instrument {
	@Override
	void play(Note n) { 
		println("Percussion.play() " + n);
	}
	@Override
	String what() { 
		return "Percussion"; 
	}
	@Override
	void adjust() { 
		print("Adjusting Percussion"); 
	}
}
class Stringed extends Instrument { 
	@Override
	void play(Note n) { 
		println("Stringed.play() " + n);
	}
	@Override
	String what() { 
		return "Stringed"; 
	}
	@Override
	void adjust() { 
		print("Adjusting Stringed"); 
	}
}
class Brass extends Wind {
	@Override
	void play(Note n) { 
		println("Brass.play() " + n); 
	}
	@Override
	void adjust() { 
		print("Adjusting Brass"); 
	}
}
class Woodwind extends Wind {
	@Override
	void play(Note n) { 
		println("Woodwind.play() " + n); 
	}
	@Override
	String what() { 
		return "Woodwind";
	}
}
class RandomInstrumentGenerator {
	private Random rand = new Random();
	public Instrument next() {
		switch(rand.nextInt(5)) {
			default:
			case 0: return new Wind();
			case 1: return new Woodwind();
			case 2: return new Brass();
			case 3: return new Stringed();
			case 4: return new Percussion();
		}
	}
}
public class Zad8_8 {
 // Doesn’t care about type, so new types
 // added to the system still work right:
	public static void tune(Instrument i) {
		println(i);
		i.play(Note.MIDDLE_C);
		
	}
	private static RandomInstrumentGenerator gen = new RandomInstrumentGenerator();
	
	
	public static void tuneAll(Instrument[] e) {
		for(Instrument i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Instrument[] orchestra = new Instrument[7];
		for(int i = 0; i < orchestra.length; i++) {
			orchestra[i] = gen.next();
		}
		tuneAll(orchestra);
	}
}