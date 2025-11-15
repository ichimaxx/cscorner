import static myutils.Skrocenie_Print.*;

class Klaska {
	private int f;
	Klaska(int f) {
		this.f = f;
		println("konstruktor klasy Klaska i jego liczba: " + f);
	}
}

public class Zad10_15 {
	public Klaska klasa() { 
		return new Klaska(100) {
			{
			}
			
		};
	}
	public static void main(String[] args) {
		Zad10_15 o = new Zad10_15();
		o.klasa();
	}
}