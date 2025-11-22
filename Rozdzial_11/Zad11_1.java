import static myutils.Skrocenie_Print.*;
import java.util.*; 

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
