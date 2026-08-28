import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (3) Create an abstract class with no methods. Derive a class and add a
method. Create a static method that takes a reference to the base class, downcasts it to the
derived class, and calls the method. In main( ), demonstrate that it works. Now put the
abstract declaration for the method in the base class, thus eliminating the need for the
downcast. 
*/
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