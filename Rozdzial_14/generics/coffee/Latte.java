//: generics/coffee/Latte.java
package Rozdzial_14.generics.coffee;
public class Latte extends Coffee {
	public static class Factory2 implements Rozdzial_14.generics.coffee.Factory2<Latte> {
		public Latte create() {
			return new Latte();
		}
	}
} ///:
