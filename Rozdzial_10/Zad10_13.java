import static myutils.Skrocenie_Print.*;


interface Parcel11 {
	default void speak() {
		println("speak() z interface");
	}
}
	
public class Zad10_13 {
	public Parcel11 parcels11() {
			return new Parcel11() {
		};
		
	}
	public static void main (String[] args) {
		Zad10_13 o = new Zad10_13();
		Parcel11 p = o.parcels11();
		p.speak();
	}
}