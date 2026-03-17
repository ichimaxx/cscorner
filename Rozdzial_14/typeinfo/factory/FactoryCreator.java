package Rozdzial_14.typeinfo.factory;
import static myutils.Skrocenie_Print.*;
import java.util.*; 


public class FactoryCreator {
	public static void main(String[] args) {
		TypeCounter counter = new TypeCounter(Part.class);
		for(int i = 0; i < 16; i++){
			Part p = Part.createRandom(); // tworzymy jedną zmiennę, zeby nie losowalo dwa razy tego samego
			println(p); // wypisujemy
			counter.count(p); // zliczamy
		}
		println();
		println(counter);
	}
}