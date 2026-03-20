import Rozdzial_14.generics.coffee.*; 
import static myutils.Skrocenie_Print.*;

/*Exercise 16: (4) Modify the Coffee hierarchy in the Generics chapter to use Registered
Factories.  */

public class Zad14_16 {
	public static void main (String[] args) {
		println("TEST CoffeeGen14_16.....................");
		println("\nSTART CoffeeGen14_16\n" + "....\n");
		CoffeeGen14_16.main(args); // start programu CoffeeGen14_16.java 
		println("\n\nCOMPLETED, SUCCESS!");
	}
}

/*
zamieniono CoffeGenerator.java na CoffeeGen14_16.java(glowne rozwiazanie zadania) usunieto stary mechanizm tworzenia obiektu i zastąpiono go fabrykami na podstawie RegisteredFactories.java
tak jak w poprzednich zadaniach z tego paragrafu dodano nowe klasy statyczne do każdej z klas kaw, Americano,Mocha itd.
*/