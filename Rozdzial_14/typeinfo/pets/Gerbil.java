//: typeinfo/pets/Gorbil.java
package Rozdzial_14.typeinfo.pets;

public class Gerbil extends Rodent {
	public Gerbil(String name) { super(name); }
	public Gerbil() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Gerbil> {
		public Gerbil create() {
			return new Gerbil();
		}
	}
} ///:~
