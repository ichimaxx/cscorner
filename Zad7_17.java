import static myutils.Skrocenie_Print.*;

class Amphibian {
	public void swimming() {
		println("Amphibian can swim.");
	}
	static void swim(Amphibian f) {
		f.swimming();
	}
}

public class Zad7_17 extends Amphibian {
	@Override 
	public void swimming() {
		println("Frog is amphibian and can swim");
	}
	public static void main (String[] args) {
		Zad7_17 frog = new Zad7_17();
		Amphibian.swim(frog); // czyli upcasting bierzemy froga z klasy Zad7_16 ale na metodach z klasy amphibian
	}
}
