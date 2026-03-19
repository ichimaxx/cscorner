import Rozdzial_14.typeinfo.*; 
import static myutils.Skrocenie_Print.*;

/*Exercise 15: (4) Implement a new PetCreator using Registered Factories, and modify
the Pets Facade so that it uses this one instead of the other two. Ensure that the rest of the
examples that use Pets .Java still work correctly.  */

public class Zad14_15 {
	public static void main (String[] args) {
		println("TEST FASADY PETS.....................");
		println("\nSTART PETCOUNT2\n" + "....\n");
		PetCount2.main(args); // start programu PetCount2.java 
		println("\nSTART PETCOUNT3\n" + "....\n");
		PetCount3.main(args); // start programu PetCount3.java 
		println("\nSTART PETCOUNT4\n" + "....\n");
		PetCount4.main(args); // start programu PetCount4.java 
		println("\n\nWSZYSTKIE KLASY IMPLEMENTUJĄCE PETS DZIALAJA!");
	}
}

/*
Do stworzenia zadania trzeba bylo wykorzystac existing klasy Pets i PetCreator.
Stworzona zostala nowa klasa PetCreator2.java(sprawdź aby zobaczyć jak wygląda rozwiązanie) która dziedziczy po klasie PetCreator i nadpisuje create randomPet(),
ponieważ ta część teraz współpracuje z fabrykami, które trzeba było zaimplementować do zadania, wszystko na podstawie klasy RegisteredFactories.java

*/