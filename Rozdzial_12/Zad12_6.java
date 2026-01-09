import java.util.*; 
import static myutils.Skrocenie_Print.*;
import java.util.logging.*;
/*Exercise 6: (1) Create two exception classes, each of which performs its own logging
automatically. Demonstrate that these work.*/
class ProstyExceptiones extends Exception {
	protected static final Logger logger = Logger.getLogger(ProstyExceptiones.class.getName());
	public ProstyExceptiones() {
		logger.log(Level.SEVERE, "Zlapany wyjatek ProstyExceptiones: ", this);
	}
}
class ProstyExceptiones2 extends Exception {
	protected static final Logger logger = Logger.getLogger(ProstyExceptiones2.class.getName());
	public ProstyExceptiones2() {
		logger.log(Level.SEVERE, "Zlapany wyjatek ProstyExceptiones2: ", this);
	}
}

public class Zad12_6 {
	public void g() throws ProstyExceptiones {
		throw new ProstyExceptiones();
	}
	public void f() throws ProstyExceptiones2 {
		throw new ProstyExceptiones2();
	}
	public static void main(String[] args) { 
		Zad12_6 koks = new Zad12_6();
			try {
				koks.g();
			} catch(ProstyExceptiones e) {
				System.err.println("Zlapany wyjatek ProstyExceptiones: " + e);
			} catch(Exception e) {
				println("nie dziala poniewaz: " + e.getMessage());
			}
			try {
				koks.f();
			} catch(ProstyExceptiones2 e) {
				System.err.println("Zlapany wyjatek ProstyExceptiones2: " + e);
			} catch(Exception e) {
				println("nie dziala poniewaz: " + e.getMessage());
			}
	}
}






