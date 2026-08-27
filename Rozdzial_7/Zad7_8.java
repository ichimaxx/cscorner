import static myutils.Skrocenie_Print.*;
/*
Exercise 8: (1) Create a base class with only a non-default constructor, and a derived
class with both a default (no-arg) and non-default constructor. In the derived-class
constructors, call the base-class constructor.
*/
class A1 {
	protected String c;
	A1(String c) {
		this.c = "J";
		println("Rozdzial_7.A(" + c + ")");
	}
}
public class Zad7_8 extends A1 {
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

/* wniosek ze trzeba wrzucic super bo stworzylem konstruktor w klasie Rozdzial_7.A tak zwany overloaded czyli z argumentem
i dlatego java juz nie uzyje domyslnego Rozdzial_7.A() który nie potrzebuje super()... */
