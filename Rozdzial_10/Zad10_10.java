import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (1) Repeat the previous exercise but define the inner class within a scope
within a method.
*/

interface Parcel7	 {
	void mogila();
}
	
public class Zad10_10 {
	public Parcel7 parcelos7(boolean b) {
		if(b) {
			class Wew implements Parcel7 {
				@Override
				public void mogila() {
					println("speak() z interface");
				}
			}
		return new Wew();
		}
	return null;
	}

	public static void main (String[] args) {
		Zad10_10 o = new Zad10_10();
		Parcel7 p = o.parcelos7(true);
		p.mogila();
	}
}
		
	