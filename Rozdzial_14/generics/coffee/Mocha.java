//: generics/coffee/Mocha.java
package Rozdzial_14.generics.coffee;
public class Mocha extends Coffee {
	public static class Factory2 implements Rozdzial_14.generics.coffee.Factory2<Mocha> {
		public Mocha create() {
			return new Mocha();
		}
	}
} ///:~
