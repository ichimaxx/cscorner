//: generics/coffee/Cappuccino.java
package Rozdzial_14.generics.coffee;
public class Cappuccino extends Coffee {
	public static class Factory2 implements Rozdzial_14.generics.coffee.Factory2<Cappuccino> {
		public Cappuccino create() {
			return new Cappuccino();
		}
	}
} ///:~
