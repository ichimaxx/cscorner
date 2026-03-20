//: generics/coffee/Americano.java
package Rozdzial_14.generics.coffee;
public class Americano extends Coffee {
	public static class Factory2 implements Rozdzial_14.generics.coffee.Factory2<Americano> {
		public Americano create() {
			return new Americano();
		}
	}
}	///:~
