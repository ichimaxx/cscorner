import static myutils.Skrocenie_Print.*;
/*
Exercise 9: (1) Create an interface with at least one method, and implement that
interface by defining an inner class within a method, which returns a reference to your
interface.
*/

interface Parcel6 {
	default void speak() {
		println("speak() z interface");
	}
}
	
public class Zad10_9 {
	public Parcel6 parcel6() {
		class Wewnetrzna implements Parcel6 {}
		return new Wewnetrzna();
	}
	public static void main (String[] args) {
		Zad10_9 o = new Zad10_9();
		Parcel6 op = o.parcel6();
		op.speak();
	}
}