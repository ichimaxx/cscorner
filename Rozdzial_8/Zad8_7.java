import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (2) Add a new type of Instrument to Music3.java and verify that
polymorphism works for your new type.
*/
enum Note_2 {
	MIDDLE_C, C_SHARP, B_FLAT;
}
class Instrument_2 {
	void play(Note_2 n) {
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
class Wind_2 extends Instrument_2 {
	@Override
	void play(Note_2 n) {
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
class Percussion_2 extends Instrument_2 {
	@Override
	void play(Note_2 n) {
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
class Stringed_2 extends Instrument_2 {
	@Override
	void play(Note_2 n) {
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
class Brass_2 extends Wind_2 {
	@Override
	void play(Note_2 n) {
		println("Rozdzial_8.Brass.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Brass");
	}
}
class Woodwind_2 extends Wind_2 {
	@Override
	void play(Note_2 n) {
		println("Rozdzial_8.Woodwind.play() " + n);
	}
	@Override
	String what() { 
		return "Rozdzial_8.Woodwind";
	}
}
public class Zad8_7 {
 /* Doesn’t care about type, so new types
	added to the system still work right: */
	public static void tune(Instrument_2 i) {
		println(i);
		i.play(Note_2.MIDDLE_C);
		
	}
	static class Bass_2 extends Instrument_2 {
		@Override
		void play(Note_2 n) {
			println("Bass.play() " + n);
		}
		@Override
		String what() { 
			return "Bass"; 
		}
		@Override
		void adjust() { 
			print("Adjusting Bass"); 
		}
	}
	
	public static void tuneAll(Instrument_2[] e) {
		for(Instrument_2 i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Instrument_2[] orchestra = {
			new Wind_2(),
			new Percussion_2(),
			new Stringed_2(),
			new Brass_2(),
			new Woodwind_2(),
			new Bass_2()
			};
		tuneAll(orchestra);
	}
}