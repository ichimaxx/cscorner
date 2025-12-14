import static myutils.Skrocenie_Print.*;
import java.util.*; 

/* Exercise 17: (2) Take the Gerbil class in Exercise 1 and put it into a Map instead,
associating each Gerbil’s name (e.g. "Fuzzy" or "Spot") as a String (the key) for each
Gerbil (the value) you put in the table. Get an Iterator for the keySet( ) and use it to move
through the Map, looking up the Gerbil for each key and printing out the key and telling the
Gerbil to hop( ). */

class Gerbiles {
	private Integer gerbilNumber;
	Gerbiles(Integer n) {
		this.gerbilNumber = n;
	}
	public void hop() {
		print(gerbilNumber + " hops");
	}
}
public class Zad11_17 {
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Map<String,Gerbiles> germap = new TreeMap<>();
		{
		germap.put("Anthony", new Gerbiles(1));
		germap.put("Zlomi", new Gerbiles(2));
		germap.put("Kiko", new Gerbiles(3));
		germap.put("Ramek", new Gerbiles(4));
		germap.put("Donkich", new Gerbiles(5));
		}
		Iterator<String> it = germap.keySet().iterator();
		ArrayList ger = new ArrayList();
		int f = 0;
		while(it.hasNext()) {
			String key = it.next();
			Gerbiles g = germap.get(key);
			print(key + " is doing " );
			g.hop();
			print("\n");
		}
	}
}