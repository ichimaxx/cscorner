import static myutils.Skrocenie_Print.*;

interface Months {
	int JANUARY = 1, FEBRUARY = 2, MARCH = 3, APRIL = 4, MAY = 5, JUNE = 6, JULY = 7,
		AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12; 
}

public class Zad9_17 implements Months {
	int JANUARY = 76;
	public static void main (String[] args) {
		Zad9_17 kk = new Zad9_17();
		/* Months.JANUARY = 120;
		
		Zad9_17.java:12: error: cannot assign a value to static final variable JANUARY
                Months.JANUARY = 120;	
				NIE MOZNA TEGO DODAĆ BO INTERFACE OD BUTA DAJE FINAL I STATIC.
				
				POLE W KLASIE PUBLICZNEJ MOZNA ZMIENIC ALE JEST TO STWORZONE NOWE OSOBNE POLE, NIE NADPISANE Z INTERFEJSU(FINAL).
		*/		
		println(kk.JANUARY); // POLE Z KLASY ZAD9_17
		println(Months.JANUARY); // POLE Z INTERFEJSU MONTHS
	}
}
	