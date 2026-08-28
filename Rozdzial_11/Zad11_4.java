import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (3) Create a generator class that produces character names (as String
objects) from your favorite movie (you can use Snow White or Star Wars as a fallback) each
time you call next( ), and loops around to the beginning of the character list when it runs out
of names. Use this generator to fill an array, an ArrayList, a LinkedList, a HashSet, a
LinkedHashSet, and a TreeSet, then print each container.
*/
class Tablica {
	private int index = 0;
	private List<String> postacie = new ArrayList<>();
	public Tablica() {
		postacie.add("Deadpool");
		postacie.add("Darth Vader");
		postacie.add("Iron Man");
		postacie.add("Dumbledore");
	}
	public void fill(Collection<String> collection, int f) {
		for(int i = 0; i < f; i++) {
			collection.add(next());
		}
	}
	public void fill(String[] array, int f) {
		for(int i = 0; i < f; i++) {
			array[i] = next();
		}
	}
	public String next() {
		String aktor = postacie.get(index);
		index = (index + 1) % postacie.size();
		return aktor;
	}
}
public class Zad11_4 {
	public static void main(String[] args) {
		Tablica t = new Tablica();
		String[] z = new String[10];
		ArrayList<String> k = new ArrayList<>();
		LinkedList<String> l = new LinkedList<>();
		HashSet<String> h = new HashSet<>();
		LinkedHashSet<String> ll = new LinkedHashSet<>();
		TreeSet<String> tt = new TreeSet<>();
		t.fill(k, 10);
		t.fill(l, 10);
		t.fill(h, 10);
		t.fill(ll, 10);
		t.fill(tt, 10);
		t.fill(z, 10);
		println("Array: \n" + Arrays.toString(z));
		println("ArrayList: \n" + k);
		println("LinkedList: \n" + l);
		println("HashSet: \n" + h);
		println("LinkedHashSet: \n" + ll);
		println("TreeSet: \n" + tt);
	}
}