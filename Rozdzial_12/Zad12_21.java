import static myutils.Skrocenie_Print.*;
import java.util.*;
/*Exercise 21: (2) Demonstrate that a derived-class constructor cannot catch exceptions
thrown by its base-class constructor. */
class NewException  {
	NewException() throws Exception {
		throw new Exception("wyjatek z NewException");
	}
} 

class Przepust extends NewException {
	Przepust() {
        try {
            super(); 
		} catch (Exception e) {
            println("zlapane w Przepust: " + e.getMessage());
        }
    }
/* 
	NIE ODPALI BO super(); MUSI BYĆ PIERWSZĄ INSTRUKCJĄ W KONSTRUKTORZE, JAK BEDZIE COŚ INNEGO JAK NP W TEJ SYTUACJI TRY() TO KOMPILATOR WYRZUCI BŁĄD:
        C:\Users\ichim\Desktop\cscorner\Rozdzial_12>javac Zad12_21.java
		Zad12_21.java:14: error: explicit constructor invocation not allowed here
            super();
                 ^
	1 error
*/

//      POPRAWNE ROZWIAZANIE PONIZEJ V
	
/*  
	Przepust() throws Exception {
		super();
	}
*/
}

public class Zad12_21 {
	public static void main(String[] args) {
		
//		I DOPIERO TUTAJ ODPALAMY KONSTRUKTOR Z DERIVED CLASS
/*
		try {
			new Przepust();
		} catch(Exception e) {
			println("catched: " + e.getMessage());
		}
*/
		new Przepust(); // TO ZADZIALA DOPIERO GDY PRZEPUST MA THROWS EXCEPTION PRZY SYTUACJI KTORA TERAZ JEST (TRY/CATCH W PRZEPUST) TO KOMPILATOR NAWET TU NIE DOJDZIE
	}
}

