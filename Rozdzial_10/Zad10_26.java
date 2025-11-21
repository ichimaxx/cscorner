import static myutils.Skrocenie_Print.*;

class ZewInner {
	class WewInner {
		int wartosc;
		WewInner(int o) {
			wartosc = o;
			println(wartosc);
		}
	}
}

public class Zad10_26 {
	class Koksinio extends ZewInner.WewInner {
		Koksinio(ZewInner zew, int o) { // w tej sytuacji z cwiczenia musimy oddac dwa argumenty, zewnetrzna klase ZewInner ktora jest jako super, czyli nowa i "int o" ktory odpowiada za wewnetrzny konstruktor. 
			zew.super(o);
		}
	}
	public static void main(String[] args) {
		ZewInner zew = new ZewInner();
		Zad10_26 ok = new Zad10_26();
		Zad10_26.Koksinio wew = ok.new Koksinio(zew, 40); // ze wzgledu na to ze konstruktor zad10_26 ma dwa argumenty musimy pierwszym odpalic klase zewnetrzna i pozniej wewnetrzna metode czyli "int o"
	}
}

	