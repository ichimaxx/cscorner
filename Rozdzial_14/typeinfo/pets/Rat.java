//: typeinfo/pets/Rat.java
package Rozdzial_14.typeinfo.pets;

public class Rat extends Rodent {
	public Rat(String name) { super(name); }
	public Rat() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Rat> {
		public Rat create() {
			return new Rat();
		}
	}
} ///:~
