import static myutils.Skrocenie_Print.*;


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