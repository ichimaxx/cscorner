import static myutils.Skrocenie_Print.*;
/*
Exercise 24: (2) In Beetle.java, inherit a specific type of beetle from class Beetle,
following the same format as the existing classes. Trace and explain the output.
*/
class Insectt {
	private int i = 9;
	protected int j;
	Insectt() {
		print("i = " + i + ", j = " + j +"\n");
		j = 39;
	}
	private static int x1 = printInit("static Rozdzial_7.Insectt.x1 initialized");
	
	static int printInit(String s) {        
		println(s);
		return 47;
	}
}
class PoopBeetle extends Zad7_24 {
	private static int f = printInit("Rozdzial_7.PoopBeetle.f initialized");
	private int h;
	public PoopBeetle(int h){
		this.h = h;
		println("h = " + h);
	}
}
public class Zad7_24 extends Insectt {
	private int k = printInit("Rozdzial_7.Zad7_24.k initialized");
	public Zad7_24() {
		println("k = " + k);
		println("j = " + j);
	}
	private static int x2 =
		printInit("static Rozdzial_7.Zad7_24.x2 initialized");
	public static void main(String[] args) {
		println("Rozdzial_7.Zad7_24 constructor");
		Zad7_24 b = new Zad7_24();
		new PoopBeetle(15);
	}
} 

/*
w output bedzie:

static Rozdzial_7.Insectt.x1 initialized
static Rozdzial_7.Zad7_24.x2 initialized
Rozdzial_7.Zad7_24 constructor
i = 9, j = 0
Rozdzial_7.Zad7_24.k initialized
k = 47
j = 39
Rozdzial_7.PoopBeetle.f initialized
i = 9, j = 0
Rozdzial_7.Zad7_24.k initialized
k = 47
j = 39
h = 15
 */
