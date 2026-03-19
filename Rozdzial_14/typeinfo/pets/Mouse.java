//: typeinfo/pets/Mouse.java
package Rozdzial_14.typeinfo.pets;

public class Mouse extends Rodent {
	public Mouse(String name) { super(name); }
	public Mouse() { super(); }
	public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Mouse> {
		public Mouse create() {
			return new Mouse();
		}
	}
}	///:~
