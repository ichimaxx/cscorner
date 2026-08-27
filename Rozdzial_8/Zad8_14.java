import static myutils.Skrocenie_Print.*;
/*
Exercise 14: (4) Modify Exercise 12 so that one of the member objects is a shared object
with reference counting, and demonstrate that it works properly.
*/

class Shared_1 {
	// private boolean full = false;         // TO JEST DO FINALIZE
	public int refCount() { return refcount; }
	private int refcount = 0;
	private static final int MAX = 3; // próg full bez finalize
	private static long counter = 0;
	private final long id = counter++;
	public Shared_1() {
		println("Creating " + this);
	}

	public void addRef() {
		refcount++;
		if (refcount == MAX) {
			println("refcount maxed");
		}
	}

	protected void dispose() {
		if(--refcount == 0)
			println("Disposing " + this);
	}

	/* protected void finalize() {
		if(refcount == 3)
		full = true;

		if(full)
		System.out.println("refcount maxed");

	}
	OGÓLNIE DOWIEDZIALEM SIE ZE LEPIEJ PO PROSTU WYPISAC BO METODY FINALIZE JZU SIE NIE UZYWA W TYCVH CZASACH
	*/
	public String toString() {
		return "Rozdzial_8.Shared " + id;
	}
}
enum Sound_1 {
	MEEP, EEP, SQUEAK;
}
class Odpalanie_1 {
	String f;
	Odpalanie_1(String f) {
		this.f = f;
		println(f);
	}
}
class Rodent_1{
	private Shared_1 shared;


	Rodent_1(Shared_1 shared) {
		this.shared=shared;
		this.shared.addRef();
		println("Rozdzial_8.Rodent() sharedzik");
		println("[after addRef] refcount=" + shared.refCount());
	}
	
	void voice(Sound_1 s) {
		println("Rozdzial_8.Rodent.sound()" + s);
	}
	
	private Odpalanie_1 o = new Odpalanie_1("klasa drugam rodent");
	String what() {
		return "Rozdzial_8.Rodent";
	}
	
	@Override
	public String toString() {
		return what();
	}
	
	void toy() {
		println("Toy");
	}
	
	protected void dispose() {
		println("Disposing " + this);
		println("[before release] refcount=" + shared.refCount());	// PRZED zmniejszeniem
		shared.dispose();	// licznik -- 
		println("[after  release] refcount=" + shared.refCount()); // PO zmniejszeniu
	}
	
}

class Hamster_1 extends Rodent_1 {
	Hamster_1(Shared_1 shared) {
		super(shared);
	}
	@Override
	void voice(Sound_1 s) {
		println("Rozdzial_8.Hamster.sound() is " + Sound_1.SQUEAK);
	}
	private Odpalanie_1 o = new Odpalanie_1("klasa trzecia, hamster");

	@Override
	String what() {
		return "Rozdzial_8.Hamster";
	}
	@Override
	void toy()
	{
		println("lovely toy is Exercise wheel");
	}
	
	@Override
	protected void dispose() {
		super.dispose();
	}
}

class Gerbil_1 extends Rodent_1 {
	Gerbil_1(Shared_1 shared) {
		super(shared);
	}	
	@Override
	void voice(Sound_1 s) {
		println("Rozdzial_8.Gerbil.sound() is " + Sound_1.EEP);
	}
	private Odpalanie_1 o = new Odpalanie_1("klasa czwarta, gerbil");
	@Override
	String what() {
		return "Rozdzial_8.Gerbil";
	}
	@Override
	void toy() {
		println("lovely toy is Tunnel");
	}
	
	@Override
	protected void dispose() {
		super.dispose();
	}
}	

class Mouse_1 extends Rodent_1 {
	Mouse_1(Shared_1 shared) {
		super(shared);
	}	
	@Override
	void voice(Sound_1 s) {
		println("Rozdzial_8.Mouse.sound() is " + Sound_1.MEEP);
	}
	private Odpalanie_1 o = new Odpalanie_1("klasa piata, mouse");
	@Override
	String what() {
		return "Rozdzial_8.Mouse";
	}
	@Override
	void toy() {
		println("lovely toy is Rozdzial_8.Mouse house");
	}
	
	@Override
	protected void dispose() {
		super.dispose();
	}
}

public class Zad8_14 {	
	private static Odpalanie_1 o = new Odpalanie_1("klasa ostatnia, publiczna zad8_14");
	public static void mix(Rodent_1 r) {
		print(r);
		r.toy();
		r.voice(Sound_1.MEEP);
	}
	public static void mixAll(Rodent_1[] e) {
		for(Rodent_1 r : e)
			mix(r);
	}
	public static void main(String[] args) {
		Shared_1 shared = new Shared_1();
		Rodent_1[] g = {
			new Hamster_1(shared),
			new Gerbil_1(shared),
			new Mouse_1(shared)
		};
	mixAll(g); 
	for (Rodent_1 r : g)
	r.dispose();
	}
}