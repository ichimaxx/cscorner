//: typeinfo/pets/Cymric.java
package Rozdzial_14.typeinfo.pets;

public class Cymric extends Manx {
	public Cymric(String name) { super(name); }
	public Cymric() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Cymric> {
		public Cymric create() {
			return new Cymric();
		}
	}
} ///:~
