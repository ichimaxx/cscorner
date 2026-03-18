import Rozdzial_14.typeinfo.factory.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 14: (4) A constructor is a kind of factory method. Modify
RegisteredFactories.java so that instead of using an explicit factory, the class object is
stored in the List, and newlnstance( ) is used to create each object. */


public class Zad14_14 {
	public static void main (String[] args) {
		println("\nSTART FACTORYCOUNT\n" + "....\n");
		RegisteredFactories1.main(args); // start programu RegisteredFactories1
	}
}

/* 
Do zadania stworzono osobny plik RegisteredFactories1.java który musiał zostać w package Rozdzial_14.typeinfo.factory, poniewaz nie wszystkie klasy są publiczne
Zad14_14 jest tylko osobnym launcherem uruchamiającym klase RegisteredFactories1
*/