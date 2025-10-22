import static myutils.Skrocenie_Print.*;

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
public class Zad8_6 {
 // Doesn’t care about type, so new types
 // added to the system still work right:
	public static void tune(Instrument i) {
		println(i);
		i.play(Note.MIDDLE_C);
		
	}
	public static void tuneAll(Instrument[] e) {
		for(Instrument i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Instrument[] orchestra = {
			new Wind(),
			new Percussion(),
			new Stringed(),
			new Brass(),
			new Woodwind()
			};
		tuneAll(orchestra);
	}
}