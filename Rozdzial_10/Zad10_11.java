import static myutils.Skrocenie_Print.*;

class Outers {
	public void outers() {}
	//po odkomentowaniu poniższej linijki, program się nie skompiluje
	//Zad10_11.Wewo g = new Zad10_11.Wewo();
}

public class Zad10_11 extends Outers {
	public Parcel8 parcelos8(boolean b) {
		return new Wewo();
		}
	private class Wewo implements Parcel8 {
		public void setup() {
			println("tekst w prywatnej klasie z klasy publicznej");
		}
	}

	public static void main (String[] args) {
		Zad10_11 o = new Zad10_11();
		Parcel8 p = o.parcelos8(true);
		p.setup();
		Outers se = new Outers();
		se.g.setup();
	}
}
		
/* NIE DA SIE SKOMPILOWAC 
		Zad10_11.java:5: error: Zad10_11.Wewo has private access in Zad10_11
			Zad10_11.Wewo g = new Zad10_11.Wewo();
	ZE WZGLEDU NA TO ZE KLASA JEST PRIVATE, DOSTEP MASZ TYLKO POD KLASĄ W KTÓREJ JEST TA KLASA, NA ZEWNATRZ JUZ NIE MOZESZ ZROBIC DO NIEJ REFERENCJI
*/