import java.util.*;
import static myutils.Skrocenie_Print.*;

class Tablica {
	private List<String> aktorzy = new ArrayList<>();
	private int index = 0;
	
	Collection fill(Collection<String> collection) { 
		aktorzy.add("Deadpool");
		aktorzy.add("Darth Vader");
		aktorzy.add("Iron Man");
		aktorzy.add("Dumbledore");
	}
	public String next() {
		String aktor = aktorzy.get(index);
		index = (index + 1) % aktorzy.size();
		return aktor;
	}
}
public class Zad11_4 {
	public static void main(String[] args) {
		Tablica t = new Tablica();
		for (int i= 0; i<10; i++) {
			println(t.next());
		}
	}
}