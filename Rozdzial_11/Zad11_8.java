import static myutils.Skrocenie_Print.*;
import java.util.*; 


class Gerbil_7 {
	protected int gerbilNumber;
	public Gerbil_7 (int gerbilNumber) {
		this.gerbilNumber = gerbilNumber;
	}
	public void hop() {
		println("Gerbil " + gerbilNumber + " hops");
	}
}


public class Zad11_8 {
	public static ArrayList<Gerbil_7> arrayList(int size) {
		return new ArrayList<Gerbil_7>();
	}
	public static void display(Iterator<Gerbil_7> it) {
		while(it.hasNext()) {
			Gerbil_7 h = it.next();
			h.hop();
		} 
	}
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList<Gerbil_7> gerb = arrayList(5);
		for (int i = 0; i < 5; i++){
			gerb.add(new Gerbil_7(i));
		}
	display(gerb.iterator());
	}
}
