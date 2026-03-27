import static myutils.Skrocenie_Print.*;
//import java.lang.reflect.*; //potrzebne do reflect getDeclaredMethod()

enum Note3 {
    MIDDLE_C, C_SHARP, B_FLAT; // Etc.
}
/*
Exercise 26: (3) Implement clearSpitValve( ) as described in the summary. 
*/

abstract class Instrument3 {
	private int i; // Storage allocated for each
	public abstract void play(Note3 n);
	public String what() { return "Instrument"; }
	public abstract void adjust();
}

class Wind3 extends Instrument3 {
	public void play(Note3 n) {
		println("Wind.play() " + n);
	}
	public String what() { return "Wind"; }
	public void adjust() {}
	public void clearSpitValve() {
		println("Wind.clearSpitValve() ");
	}
}

class Percussion3 extends Instrument3 {
	public void play(Note3 n) {
		println("Percussion.play() " + n);
	}
	public String what() { return "Percussion"; }
	public void adjust() {}
}

class Stringed3 extends Instrument3 {
	public void play(Note3 n) {
		println("Stringed.play() " + n);
	}
	public String what() { return "Stringed"; }
	public void adjust() {}
}	

class Brass3 extends Wind3 {
	public void play(Note3 n) {
		println("Brass.play() " + n);
	}
	public void clearSpitValve() {
		println("Brass.clearSpitValve() ");
	}
	public void adjust() { println("Brass.adjust()"); }
}

class Woodwind3 extends Wind3 {
	public void play(Note3 n) {
		println("Woodwind.play() " + n);
	}
	public void clearSpitValve() {
		println("Woodwind.clearSpitValve() ");
	}
	public String what() { return "Woodwind"; }
}	

public class Zad14_26 {
	static void tune(Instrument3 i) {
		i.adjust();
		i.play(Note3.MIDDLE_C);
	}
	static void tuneAll(Instrument3[] e) {
		for(Instrument3 i : e)
			tune(i);
	}	
	public static void main(String[] args) throws Exception{
		Instrument3[] orchestra = {
			new Wind3(),
			new Percussion3(),
			new Stringed3(),
			new Brass3(),
			new Woodwind3()
		};
		tuneAll(orchestra);
		for(Instrument3 i : orchestra) {
			if(i instanceof Wind3) {
				((Wind3)i).clearSpitValve(); // wywołanie metod za pomoca RTTI + CAST
				//Method k = i.getClass().getDeclaredMethod("clearSpitValve");
				//k.invoke(i);   // wywołanie metod za pomocą refleksji
			}
		}
	}
} /* 
do instrumentow dentych dodano clearSpitValve() i za pomoca RTTI sprawdzono czy dany obiekt jest instanceof Wind a nastepnie jeśli tak, to wywołano metodę clearSpitValve() z tych klas, które są true
*/
