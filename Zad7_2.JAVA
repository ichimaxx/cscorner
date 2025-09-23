import static myutils.Skrocenie_Print.*;

class Cleanser { 
	private String s = "Cleanser"; 
	public void append(String a) { s += a; } 
	public void dilute() { append(" dilute()"); } 
	public void apply() { append(" apply()"); } 
	public void scrub() { append(" scrub()"); } 
	@Override public String toString() { return s; } 
	public static void main(String[] args) { 
		Cleanser x = new Cleanser(); 
		x.dilute(); x.apply(); x.scrub(); 
		print(x); 
		} 
} 
class Detergent extends Cleanser { 
	// Change a method: 
	@Override
	public void scrub() { 
		append(" Detergent.scrub()"); 
		super.scrub(); // Call base-class version 
		}
	// Add methods to the interface:
	public void foam() {
		append(" foam()"); 
	} 
} 
public class Zad7_2 extends Detergent {
	@Override
	public void scrub() {
		append(" Domestos.scrub()");
		super.scrub();
	}

	public void sterilize() { 
		append(" sterilize()");
		}

	// Test the new class: 
	public static void main(String[] args) { 
		Zad7_2 x = new Zad7_2(); 
		x.dilute(); 
		x.apply(); 
		x.scrub(); 
		x.foam();
		x.sterilize();
		print(x); 
		print("Testing base class:"); 
		Cleanser.main(args); 
	}
}
