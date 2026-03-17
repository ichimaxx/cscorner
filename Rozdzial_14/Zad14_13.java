import Rozdzial_14.typeinfo.factory.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 13: (3) Use TypeCounter with the RegisteredFactories.java example in this
chapter. */

public class Zad14_13 {
	public static void main (String[] args) {
		println("\nSTART FACTORYCOUNT\n" + "....\n");
		FactoryCreator.main(args); // start programu FactoryCreator
	}
}

// do zadania stworzono osobny plik FactoryCreator.java który musiał zostać w package Rozdzial_14.typeinfo.factory poniewaz klasa Part nie jest public