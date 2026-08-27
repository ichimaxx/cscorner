import static myutils.Skrocenie_Print.*;
/*
Exercise 19: (2) Create a class with a blank final reference to an object. Perform the
initialization of the blank final inside all constructors. Demonstrate the guarantee that the
final must be initialized before use, and that it cannot be changed once initialized.
*/
class Tescik {
	public int i;
	Tescik(int i) {
		this.i = i;
	}
}
public class Zad7_19 {
	private final Tescik t;
	public Zad7_19() {
		t = new Tescik(4); // inicjalizacja blank final
	}
	public Zad7_19(int x) {
		t = new Tescik(x); // inicjalizacja blank final po raz drugi z argumentem
	}
	void zmianastanuobiektu(int nowei){
		t.i = nowei;
	}
	void probazmianyreferencji(){
		// t = new Rozdzial_7.Tescik(999);   // TO NIE PRZEJDZIE
	}
	
	
	@Override
	public String toString() {
		return "t = " + t.i;
	}
	public static void main (String[] args) {
		Zad7_19 a = new Zad7_19();
		println("Rozdzial_7.A: " + a);
		a.zmianastanuobiektu(150);
		println("Rozdzial_7.A po zmianie stanu obiektu: " + a);
		Zad7_19 b = new Zad7_19(45); // inicjowanie wartości w konstruktorze
		println("Rozdzial_7.B: " + b);
	}
}