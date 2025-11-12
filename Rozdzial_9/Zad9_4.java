import static myutils.Skrocenie_Print.*;

abstract class Klas {
	abstract void dodanyklas(); // bez tej metody trzeba zrobic downcast inaczej mozna zrobic bez downcastu
}
class KlasDerived extends Klas {
	void dodanyklas(){
		println("KlasDerived.dodanyklas()");
	}
}
public class Zad9_4 {
	static void kartkowka(Klas k) {
		// ((KlasDerived) k).dodanyklas();
		k.dodanyklas();
	}

	
	public static void main (String[] args) {
		Klas k = new KlasDerived();
		kartkowka(k);
	}
}