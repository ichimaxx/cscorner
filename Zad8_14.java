import static myutils.Skrocenie_Print.*;

enum Sound {
	MEEP, EEP, SQUEAK;
}
class Odpalanie {
	String f;
	Odpalanie(String f) {
		this.f = f;
		println(f);
	}
}
class Rodent{
	private Shared shared;
	

	Rodent(Shared shared) {
		this.shared=shared;
		this.shared.addRef();
		println("Rodent() sharedzik");
		println("[after addRef] refcount=" + shared.refCount());
	}
	
	void voice(Sound s) {
		println("Rodent.sound()" + s);
	}
	
	private Odpalanie o = new Odpalanie("klasa drugam rodent");
	String what() {
		return "Rodent";
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

class Hamster extends Rodent {
	Hamster(Shared shared) {
		super(shared);
	}
	@Override
	void voice(Sound s) {
		println("Hamster.sound() is " + Sound.SQUEAK);
	}
	private Odpalanie o = new Odpalanie("klasa trzecia, hamster");

	@Override
	String what() {
		return "Hamster";
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

class Gerbil extends Rodent {
	Gerbil(Shared shared) {
		super(shared);
	}	
	@Override
	void voice(Sound s) {
		println("Gerbil.sound() is " + Sound.EEP);
	}
	private Odpalanie o = new Odpalanie("klasa czwarta, gerbil");	
	@Override
	String what() {
		return "Gerbil";
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

class Mouse extends Rodent {
	Mouse(Shared shared) {
		super(shared);
	}	
	@Override
	void voice(Sound s) {
		println("Mouse.sound() is " + Sound.MEEP);
	}
	private Odpalanie o = new Odpalanie("klasa piata, mouse");	
	@Override
	String what() {
		return "Mouse";
	}
	@Override
	void toy() {
		println("lovely toy is Mouse house");
	}
	
	@Override
	protected void dispose() {
		super.dispose();
	}
}

public class Zad8_14 {	
	private static Odpalanie o = new Odpalanie("klasa ostatnia, publiczna zad8_14");
	public static void mix(Rodent r) {
		print(r);
		r.toy();
		r.voice(Sound.MEEP);
	}
	public static void mixAll(Rodent[] e) {
		for(Rodent r : e)
			mix(r);
	}
	public static void main(String[] args) {
		Shared shared = new Shared();
		Rodent[] g = {
			new Hamster(shared),
			new Gerbil(shared),
			new Mouse(shared)
		};
	mixAll(g); 
	for (Rodent r : g)
	r.dispose();
	}
}