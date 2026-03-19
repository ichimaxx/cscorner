//: typeinfo/pets/Manx.java
package Rozdzial_14.typeinfo.pets;

public class Manx extends Cat {
	public Manx(String name) { super(name); }
	public Manx() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Manx> {
		public Manx create() {
			return new Manx();
		}
	}
} ///:~
