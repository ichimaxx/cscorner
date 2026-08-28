import static myutils.Skrocenie_Print.*;
import java.util.*; 
/*
Exercise 1: (2) Create a new class called Gerbil with an int gerbilNumber that’s
initialized in the constructor. Give it a method called hop( ) that displays which gerbil
number this is, and that it’s hopping. Create an ArrayList and add Gerbil objects to the
List. Now use the get( ) method to move through the List and call hop( ) for each Gerbil.
*/
class Gerbil {
	private int gerbilNumber;
	public Gerbil (int gerbilNumber) {
		this.gerbilNumber = gerbilNumber;
	}
	public void hop() {
		println("Gerbil " + gerbilNumber + " hops");
	}
}
public class Zad11_1 {
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList ger = new ArrayList();
		for (int i = 0; i < 5; i++)
			ger.add(new Gerbil(i));
		for (int i = 0; i < ger.size(); i++)
			((Gerbil)ger.get(i)).hop();
	}
}
