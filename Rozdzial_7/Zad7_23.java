import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (2) Prove that class loading takes place only once. Prove that loading may
be caused by either the creation of the first instance of that class or by the access of a static
member.
*/
class Insect {
	private int i = 9;
	protected int j;
	Insect() {
		print("i = " + i + ", j = " + j +"\n");
		j = 39;
	}
	private static int x1 = printInit("static Rozdzial_7.Insect.x1 initialized");
	
	static int printInit(String s) {        
		println(s);
		return 47;
	}
}
public class Zad7_23 extends Insect {
	private int k = printInit("Rozdzial_7.Zad7_23.k initialized");
	public Zad7_23() {
		println("k = " + k);
		println("j = " + j);
	}
	private int x2 =
		printInit("static Rozdzial_7.Zad7_23.x2 initialized");
	public static void main(String[] args) {
		println("Rozdzial_7.Zad7_23 constructor");
		Zad7_23 b = new Zad7_23();
		new Zad7_23();
	}
} 

/* dodalem druga inicjalizacje obiektu zeby pokazać ze metody statyczne odpalaja sie tylko raz */
