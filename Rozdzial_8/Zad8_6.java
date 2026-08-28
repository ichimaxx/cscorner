import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Change Music3.java so that what( ) becomes the root Object method
toString( ). Try printing the Instrument objects using System.out.println( ) (without
any casting).
*/
enum Note_5 {
	MIDDLE_C, C_SHARP, B_FLAT;
}
class Instrument_5 {
	void play(Note_5 n) {
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
class Wind_5 extends Instrument_5 {
	@Override
	void play(Note_5 n) {
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
class Percussion_5 extends Instrument_5 {
	@Override
	void play(Note_5 n) {
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
class Stringed_5 extends Instrument_5 {
	@Override
	void play(Note_5 n) {
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
class Brass_5 extends Wind_5 {
	@Override
	void play(Note_5 n) {
		println("Rozdzial_8.Brass.play() " + n);
	}
	@Override
	void adjust() { 
		print("Adjusting Rozdzial_8.Brass");
	}
}
class Woodwind_5 extends Wind_5 {
	@Override
	void play(Note_5 n) {
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
	public static void tune(Instrument_5 i) {
		println(i);
		i.play(Note_5.MIDDLE_C);
		
	}
	public static void tuneAll(Instrument_5[] e) {
		for(Instrument_5 i : e)
			tune(i);
	}
	public static void main(String[] args) {
 // Upcasting during addition to the array:
		Instrument_5[] orchestra = {
			new Wind_5(),
			new Percussion_5(),
			new Stringed_5(),
			new Brass_5(),
			new Woodwind_5()
			};
		tuneAll(orchestra);
	}
}