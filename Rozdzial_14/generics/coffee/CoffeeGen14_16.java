
package Rozdzial_14.generics.coffee;
import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;


/*Exercise 16: (4) Modify the Coffee hierarchy in the Generics chapter to use Registered
Factories. */

public class CoffeeGen14_16 extends CoffeeGenerator {
	static List<Factory2<? extends Coffee>> partCoffee = new ArrayList<Factory2<? extends Coffee>>();	
	static {
		// Collections.addAll() gives an "unchecked generic
		// array creation ... for varargs parameter" warning.
		partCoffee.add(new Latte.Factory2());
		partCoffee.add(new Mocha.Factory2());
		partCoffee.add(new Breve.Factory2());
		partCoffee.add(new Americano.Factory2());
		partCoffee.add(new Cappuccino.Factory2());
	} // lista fabryk
	private static Random rand = new Random();
	public CoffeeGen14_16() {}
	// For iteration:
	private int size = 0;
	public CoffeeGen14_16(int sz) { size = sz; }
	@Override
	public Coffee next() { // zamiast nextRandom()(poprzednie zadanie(14_15)) uzywamy uprzednio stworzonej metody next(), zamieniamy tylko konstruktor
		int n = rand.nextInt(partCoffee.size());
		return partCoffee.get(n).create();
	}
	public static void main(String[] args) {
		CoffeeGen14_16 gen = new CoffeeGen14_16();
		for(int i = 0; i < 21; i++)
			System.out.println(gen.next());
		println("\nPRINTED USING CoffeeGen14_16.JAVA"); // debug do zweryfikowania czy na pewno używamy tej wersji
	}
} /* 
ten sam case co poprzednie zadanie, przebudowanie klasy CoffeGenerator.java w której zamieniono mechanizm tworzenia obiektu na fabryki z przykładu RegisteredFactories.java

*///:~
