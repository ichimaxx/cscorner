import static myutils.Skrocenie_Print.*;
/*
Exercise 13: (3) Add a finalize( ) method to ReferenceCounting.java to verify the
termination condition (see the Initialization & Cleanup chapter).
*/
class Shared {
	// private boolean full = false;         // TO JEST DO FINALIZE  
	public int refCount() { return refcount; }
	private int refcount = 0;
	private static final int MAX = 3; // próg full bez finalize
	private static long counter = 0;
	private final long id = counter++;
	public Shared() {
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
class Composing {
	private Shared shared;
	private static long counter = 0;
	private final long id = counter++;
	
	public Composing(Shared shared) {
		print("Creating " + this);
		this.shared = shared;
	this.shared.addRef();
	}
	
	protected void dispose() {
		print("disposing " + this);
		shared.dispose();
	}
	
	@Override
	public String toString() { 
		return "Rozdzial_8.Composing " + id + "\n";
	}
}
public class Zad8_13 {
	public static void main(String[] args) {
		Shared shared = new Shared();
		Composing[] composing = {
			new Composing(shared),
			new Composing(shared),
			new Composing(shared),
			new Composing(shared),
			new Composing(shared)
		};
		for(Composing c : composing)
			c.dispose();
	}
}