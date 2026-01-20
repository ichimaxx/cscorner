import static myutils.Skrocenie_Print.*;
import java.lang.*;
import java.util.*;

/*Exercise 25: (2) Create a three-level hierarchy of exceptions. Now create a base-class A
with a method that throws an exception at the base of your hierarchy. Inherit B from A and
override the method so it throws an exception at level two of your hierarchy. Repeat by
inheriting class C from B. In main( ), create a C and upcast it to A, then call the method. */

class Annoyance extends Exception {
	public Annoyance(String msgs) {
		super(msgs);
	}
}
class Sneeze extends Annoyance {
	public Sneeze(String msgs) {
		super(msgs);
	}
}

class Snorlax extends Sneeze {
	public Snorlax(String msgs) {
		super(msgs);
	}
}

class A1 {
	void c() throws Annoyance {
		throw new Annoyance("Annoyance from c() z klasy A");
	}
}
class B1 extends A1 {
	@Override
	void c() throws Sneeze {
		throw new Sneeze("Sneeze from c() z klasy B");
	}
}
class C1 extends B1 {
	@Override
	void c() throws Snorlax {
		throw new Snorlax("Snorlax from c() z klasy C");
	}
}

public class Zad12_25 {
	public static void main(String[] args) {
		A1 g = new C1(); // upcast z zadania z A do C
		try {
			g.c();
		} catch(Snorlax e) {
			println("Caught Snorlax: " + e.getMessage());
		} catch(Sneeze e) {
			println("Caught Sneeze: " + e.getMessage());
		} catch(Annoyance e) {
			println("Caught Annoyance: " + e.getMessage());
		} 
		
	}
}


/* w paragrafie pokazuja dopasowanie catch do hierarchii exception, ze nie da sie w odwrotnej kolejnosci lapac wyjątków, typu najpierw Annoyance a pozniej Sneeze
g ma typ A1 ale wywolanie g.c() uruchamia C1.c czyli rzuca snorlaxa
exception Snorlax mozna zlapac jako Snorlax/Sneeze/Annoyance, ale musi byc to w hierarchii od najbardziej szczegółowego do ogólnego chyba ze bedzie wypisany jako pojedynczy w try np catch(Sneeze e)
*/