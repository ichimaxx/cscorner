import static myutils.Skrocenie_Print.*;

class Insectt {
	private int i = 9;
	protected int j;
	Insectt() {
		print("i = " + i + ", j = " + j +"\n");
		j = 39;
	}
	private static int x1 = printInit("static Insectt.x1 initialized");
	
	static int printInit(String s) {        
		println(s);
		return 47;
	}
}
class PoopBeetle extends Zad7_24 {
	private static int f = printInit("PoopBeetle.f initialized");
	private int h;
	public PoopBeetle(int h){
		this.h = h;
		println("h = " + h);
	}
}
public class Zad7_24 extends Insectt {
	private int k = printInit("Zad7_24.k initialized");
	public Zad7_24() {
		println("k = " + k);
		println("j = " + j);
	}
	private static int x2 =
		printInit("static Zad7_24.x2 initialized");
	public static void main(String[] args) {
		println("Zad7_24 constructor");
		Zad7_24 b = new Zad7_24();
		new PoopBeetle(15);
	}
} 

/*
w output bedzie:

static Insectt.x1 initialized
static Zad7_24.x2 initialized
Zad7_24 constructor
i = 9, j = 0
Zad7_24.k initialized
k = 47
j = 39
PoopBeetle.f initialized
i = 9, j = 0
Zad7_24.k initialized
k = 47
j = 39
h = 15
 */
