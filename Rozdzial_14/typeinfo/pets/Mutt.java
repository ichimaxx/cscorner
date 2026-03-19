//: typeinfo/pets/Mutt.java
package Rozdzial_14.typeinfo.pets;

public class Mutt extends Dog  {
	public Mutt(String name) { super(name); }
	public Mutt() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Mutt> {
		public Mutt create() {
			return new Mutt();
		}
	}
} ///:~
