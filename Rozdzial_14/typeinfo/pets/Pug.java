//: typeinfo/pets/Pug.java
package Rozdzial_14.typeinfo.pets;

public class Pug extends Dog {
  public Pug(String name) { super(name); }
  public Pug() { super(); }
  public static class Factory1 implements Rozdzial_14.typeinfo.pets.Factory1<Pug> {
		public Pug create() {
			return new Pug();
		}
	}
} ///:~
