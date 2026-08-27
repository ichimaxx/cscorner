import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Change Music3.java so that what( ) becomes the root Object method
toString( ). Try printing the Instrument objects using System.out.println( ) (without
any casting).
*/
enum Note {
	MIDDLE_C, C_SHARP, B_FLAT;
}
class Instrument {
	void play(Note n) { 
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
class Wind extends Instrument {
	@Override
	void play(Note n) { 
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
class Percussion extends Instrument {
	@Override
	void play(Note n) { 
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
class Stringed extends Instrument { 
	@Override
	void play(Note n) { 
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
class Brass extends Wind {
	@Override
	void play(Note n) { 
		println("Rozdzial_8.Brass.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Brass");
	}
}
class Woodwind extends Wind {
	@Override
	void play(Note n) { 
		println("Rozdzial_8.Woodwind.play() " + n);
	}
	@Override
	String what() { 
		return "Rozdzial_8.Woodwind";
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