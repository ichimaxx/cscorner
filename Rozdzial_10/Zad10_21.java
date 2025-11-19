import static myutils.Skrocenie_Print.*;

interface Interfejsik {
	public void go();
	class ClassInterface1 {
		public static void op(Interfejsik x) {
			x.go();
		}
	}
}

public class Zad10_21 implements Interfejsik {
	public void go() {
		println("void go() z Zad10_21 ale odpalony z klasy statycznej nestowanej w interfejsie Interfejsik");
	}
	public static void main (String[] args) {
		Interfejsik.ClassInterface1.op(new Zad10_21());
	}
}