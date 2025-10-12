import static myutils.Skrocenie_Print.*;

class Amphibian {
	public void swimming() {
		println("Amphibian can swim.");
	}
	static void swim(Amphibian f) {
		f.swimming();
	}
}

public class Zad7_16 extends Amphibian {

	public static void main (String[] args) {
		Zad7_16 frog = new Zad7_16();
		Amphibian.swim(frog); // czyli upcasting bierzemy froga z klasy Zad7_16 ale na metodach z klasy amphibian
	}
}
