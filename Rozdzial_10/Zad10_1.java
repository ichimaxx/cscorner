import static myutils.Skrocenie_Print.*;

public class Zad10_1 {
	class Inner {
		private int f = 25;
		public int value(){
			return f;
		}
	}
	public Inner inner() {
		return new Inner();
	}

	
	public static void main(String[] args) {

		Zad10_1 g = new Zad10_1();
		Zad10_1.Inner k = g.inner();
		println(k.value());
	}
}