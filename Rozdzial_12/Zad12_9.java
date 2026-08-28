import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 9: (2) Create three new types of exceptions. Write a class with a method that
throws all three. In main( ), call the method but only use a single catch clause that will
catch all three types of exceptions. 
*/
class Wyjatek1_2 extends Exception {
	public Wyjatek1_2(String msgs) {
		super(msgs);
	}
}
class Wyjatek2_2 extends Exception {
	public Wyjatek2_2(String msg) {
		super(msg);
	}
}
class Wyjatek3_2 extends Exception {
	public Wyjatek3_2(String msgg) {
		super(msgg);
	}
}
public class Zad12_9 {
	public void test(int los) throws Wyjatek1_2, Wyjatek2_2, Wyjatek3_2 {
		switch(los) {
			case 1 -> throw new Wyjatek1_2("pierwszy exception");
			case 2 -> throw new Wyjatek2_2("drugi exception");
			case 3 -> throw new Wyjatek3_2("trzeci exception");
			default -> println("brak wyjatku");
		} // NIE MOZNA RZUCAC TRZECH WYJATKOW W JEDNEJ METODZIE POD RZAD WIEC UZYLEM SWITCHA ZEBY MOZNA BYLO WRZUCIC WSZYSTKIE
	}
	public static void main(String[] args) {
		Random rand = new Random();
		Zad12_9 ks = new Zad12_9();
		try {
			ks.test(rand.nextInt(4)); 
			// WYJATEK JEST LOSOWO WYBIERANY Z METODY TEST() GDZIE JEST SWITCH() KTORY WYBIERA CASE 
			// EWEWNTUALNIE JEST BRAK WYJATKU MOZNA TEZ UZYC PETLI FOR W TRY ZEBY WYRZUCILO KAZDY WYJATEK PO KOLEI, ALE WTEDY TEZ JEST POTRZEBNY SWITCH
		} catch(Exception e) {
			print("Zlapano exception, a mianowicie: ");
			e.printStackTrace();
		} finally {
			println("koniec try");
		}
	}
}