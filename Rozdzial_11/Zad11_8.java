import static myutils.Skrocenie_Print.*;
import java.util.*; 


class Gerbil {
	protected int gerbilNumber;
	public Gerbil (int gerbilNumber) {
		this.gerbilNumber = gerbilNumber;
	}
	public void hop() {
		println("Gerbil " + gerbilNumber + " hops");
	}
}


public class Zad11_8 {
	public static ArrayList<Gerbil> arrayList(int size) {
		return new ArrayList<Gerbil>();
	}
	public static void display(Iterator<Gerbil> it) {
		while(it.hasNext()) {
			Gerbil h = it.next();
			h.hop();
		} 
	}
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList<Gerbil> gerb = arrayList(5); 
		for (int i = 0; i < 5; i++){
			gerb.add(new Gerbil(i));
		}
	display(gerb.iterator());
	}
}
