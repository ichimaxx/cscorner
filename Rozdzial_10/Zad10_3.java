import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (1) Modify Exercise 1 so that Outer has a private String field (initialized
by the constructor), and Inner has a toString( ) that displays this field. Create an object of
type Inner and display it.
*/
public class Zad10_3 {
	private String p;
	
	public Zad10_3(String p) {
		this.p = p;
	}
	class Inner {
		private int f = 25;
		public int value(){
			return f;
		}
		@Override
		public String toString(){
			return p;
		}
	}
	public Inner inner() {
		return new Inner();
	}

	
	public static void main(String[] args) {

		Zad10_3 g = new Zad10_3("lol");
		Zad10_3.Inner k = g.inner();
		println(k.value());
		println(k); // dlatego to dziala bo k.value wybiera przeciazenie, a samo k wybiera przeciazenie String.valueof(k) ktory z kolei wywoluje k.toString(); czyli OVERRIDED METHOD. jest to powiazane z automatycznym wybieraniem typów przez metode println
	}
}