import Rozdzial_14.generics.coffee.*; 
import static myutils.Skrocenie_Print.*;
import java.util.*; 

/*Exercise 12: (3) Use TypeCounter with the CoffeeGenerator.java class in the Generics
chapter. */

public class Zad14_12 {
	public static void main(String[] args) {
		TypeCounter counter = new TypeCounter(Coffee.class);
			for(Coffee coffee : new CoffeeGenerator(15)) {
			println(coffee.getClass().getSimpleName() + " ");
			counter.count(coffee);
		}
		println();
		println(counter);
	}
}

/*
celem zadania było zaimplementować TypeCounter.java do CoffeeGenerator.java tak aby zliczało typy jakie pojawiły się w generatorze
zadanie wykonane w podobny sposób co 11

OUTPUT:

C:\Users\ichim\Desktop\cscorner\Rozdzial_14>java Zad14_12
Americano
Latte
Americano
Mocha
Mocha
Breve
Americano
Latte
Cappuccino
Cappuccino
Americano
Americano
Mocha
Breve
Breve

{Cappuccino=2, Coffee=15, Latte=2, Breve=3, Mocha=3, Americano=5}

*/