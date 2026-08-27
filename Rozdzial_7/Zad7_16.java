import static myutils.Skrocenie_Print.*;
/*
Exercise 16: (2) Create a class called Amphibian. From this, inherit a class called
Frog. Put appropriate methods in the base class. In main( ), create a Frog and upcast it to
Amphibian and demonstrate that all the methods still work.
*/
class Amphibian {
	public void swimming() {
		println("Rozdzial_7.Amphibian can swim.");
	}
	static void swim(Amphibian f) {
		f.swimming();
	}
}

public class Zad7_16 extends Amphibian {

	public static void main (String[] args) {
		Zad7_16 frog = new Zad7_16();
		Amphibian.swim(frog); // czyli upcasting bierzemy froga z klasy Rozdzial_7.Zad7_16 ale na metodach z klasy amphibian
	}
}
