import static myutils.Skrocenie_Print.*;
import java.util.*; 

public class Zad11_14 {	
	public static int dzialanie(LinkedList<Integer> intlist) {
		int size = intlist.size();
		int wynik = (int) Math.ceil(size / 2.0);
		return wynik;
	}
	public static void main (String[] args) {
		Integer t1 = 4;
		Integer t2 = 6;
		Integer t3 = 74;
		LinkedList<Integer> intlist = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			int f = dzialanie(intlist);	
			ListIterator<Integer> it = intlist.listIterator(f);
			it.add(i);
		}
		println(intlist);
	}
}
	