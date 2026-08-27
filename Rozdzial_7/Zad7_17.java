import static myutils.Skrocenie_Print.*;
/*
Exercise 17: (1) Modify Exercise 16 so that Frog overrides the method definitions from
the base class (provides new definitions using the same method signatures). Note what
happens in main( ).
*/
class Amphibian_1 {
	public void swimming() {
		println("Rozdzial_7.Amphibian can swim.");
	}
	static void swim(Amphibian_1 f) {
		f.swimming();
	}
}

public class Zad7_17 extends Amphibian_1 {
	@Override 
	public void swimming() {
		println("Frog is amphibian and can swim");
	}
	public static void main (String[] args) {
		Zad7_17 frog = new Zad7_17();
		Amphibian_1.swim(frog); // czyli upcasting bierzemy froga z klasy Rozdzial_7.Zad7_16 ale na metodach z klasy amphibian
	}
}
