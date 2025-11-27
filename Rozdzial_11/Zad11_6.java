import java.util.*;
import static myutils.Skrocenie_Print.*;


public class Zad11_6 {

	public static void main(String[] args) {
		Random rand = new Random(47);
		List<String> strums = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			strums.add("string" + i);
		}
		println("1: " + strums);
		String h = "2";
		strums.add(h); // Automatically resizes
		println("2: " + strums);
		println("3: " + strums.contains(h));
		strums.remove(h); // Remove by object
		String p = strums.get(2);
		println("4: " + p + " " + strums.indexOf(p));
		String cymrik = "999"; // 
		strums.add(cymrik); // zeby wyswietlilo indeks tego numeru trzeba go najpierw dodac do listy, wtedy na output jest ze jest 10 w kolejnosci
		println("5: " + strums.indexOf(cymrik));
		println("6: " + strums.remove(cymrik));
		// Must be the exact object:
		println("7: " + strums.remove(p));
		println("8: " + strums); // w przypadku wersji z Integer usunelo pozycje trzecia i czwarta a tutaj usunelo pozycje trzecia i ostatnia ktora dodalem
		strums.add("String" + 4); // Insert at an index
		println("9: " + strums);
		List<String> sub = strums.subList(1, 4);
		println("subList: " + sub);
		println("10: " + strums.containsAll(sub));
		Collections.sort(sub); // In-place sort
		println("sorted subList: " + sub);
		// Order is not important in containsAll():
		println("11: " + strums.containsAll(sub));
		Collections.shuffle(sub, rand); // Mix it up
		println("shuffled subList: " + sub);
		println("12: " + strums.containsAll(sub));
		List<String> copy = new ArrayList<String>(strums);
		sub = Arrays.asList(strums.get(1), strums.get(4));
		println("sub: " + sub);
		copy.retainAll(sub);
		println("13: " + copy);
		copy = new ArrayList<String>(strums); // Get a fresh copy
		copy.remove(2); // Remove by index
		println("14: " + copy);
		copy.removeAll(sub); // Only removes exact objects
		println("15: " + copy);
		int g = 1515;
		copy.set(1, "Stringzmienionymetodaset"); // Replace an element
		println("16: " + copy);
		copy.addAll(2, sub); // Insert a list in the middle
		println("17: " + copy);
		println("18: " + strums.isEmpty());
		strums.clear(); // Remove all elements
		println("19: " + strums);
		println("20: " + strums.isEmpty());
		strums.addAll(new ArrayList<String>());
		for (int i = 0; i < 10; i++) {
			strums.add("string" + i);
		}
		println("21: " + strums);
		Object[] o = strums.toArray();
		println("22: " + o[3]);
		String[] pa = strums.toArray(new String[0]);
		println("23: " + pa[7]);
	}
}