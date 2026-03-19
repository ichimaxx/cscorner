//: typeinfo/pets/EgyptianMau.java
package Rozdzial_14.typeinfo.pets;

public class EgyptianMau extends Cat{
	public EgyptianMau(String name) { super(name); }
	public EgyptianMau() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<EgyptianMau> {
		public EgyptianMau create() {
			return new EgyptianMau();
		}
	}
} ///:~
