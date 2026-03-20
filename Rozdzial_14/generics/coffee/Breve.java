//: generics/coffee/Breve.java
package Rozdzial_14.generics.coffee;
public class Breve extends Coffee {
	public static class Factory2 implements Rozdzial_14.generics.coffee.Factory2<Breve> {
		public Breve create() {
			return new Breve();
		}
	}
} ///:~
