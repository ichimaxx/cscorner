import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (1) Write a class named Outer that contains an inner class named Inner.
Add a method to Outer that returns an object of type Inner. In main( ), create and
initialize a reference to an Inner.
*/
public class Zad10_1 {
	class Inner {
		private int f = 25;
		public int value(){
			return f;
		}
	}
	public Inner inner() {
		return new Inner();
	}

	
	public static void main(String[] args) {

		Zad10_1 g = new Zad10_1();
		Zad10_1.Inner k = g.inner();
		println(k.value());
	}
}