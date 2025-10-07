import static myutils.Skrocenie_Print.*;
class A {
	protected String c;
		A(String c) {
		this.c = "J";
		println("A(" + c + ")");
	}
}
public class Zad7_8 extends A {
	private String d;
	// konstruktor domyslny czuyli ze bez argumentów
	public Zad7_8() {
		super("J");
		this.d = "DEFAULT CONSTR...";
		println(c + " " + d);
	}
	// konstruktor niedomyslny czyli z argumentami
	public Zad7_8(String g) {
		super("J");
		this.d = "NOTDEFAULT CONSTR";
		print(c + d);
	}
	public static void main(String[] args){
			new Zad7_8();
			new Zad7_8("dodany argumencik czyli niedomyslny argument");
		}
}

/* wniosek ze trzeba wrzucic super bo stworzylem konstruktor w klasie A tak zwany overloaded czyli z argumentem 
i dlatego java juz nie uzyje domyslnego A() który nie potrzebuje super()... */
