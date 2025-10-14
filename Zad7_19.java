import static myutils.Skrocenie_Print.*;
import java.util.*;

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
		// t = new Tescik(999);   // TO NIE PRZEJDZIE
	}
	
	
	@Override
	public String toString() {
		return "t = " + t.i;
	}
	public static void main (String[] args) {
		Zad7_19 a = new Zad7_19();
		println("A: " + a);
		a.zmianastanuobiektu(150);
		println("A po zmianie stanu obiektu: " + a);
		Zad7_19 b = new Zad7_19(45); // inicjowanie wartości w konstruktorze
		println("B: " + b);
	}
}