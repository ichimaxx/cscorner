//: typeinfo/pets/Hamster.java
package Rozdzial_14.typeinfo.pets;

public class Hamster extends Rodent {
	public Hamster(String name) { super(name); }
	public Hamster() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Hamster> {
		public Hamster create() {
			return new Hamster();
		}
	}
} ///:~
