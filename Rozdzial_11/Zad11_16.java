import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;

public class Zad11_16 extends UniqueWords {
	public static void main (String[] args) {
		Set<String> words = new TreeSet<String>(new TextFile("SetOperations.java", "\\W+"));
		ArrayList<String> nwords = new ArrayList<String>();
		Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
		Iterator<String> it = words.iterator();
		int calosc = 0;
		while(it.hasNext()) {
			String w = it.next();
			int liczbasamp = 0;
			for (int i = 0; i < w.length(); i++) {
				
				if (vowels.contains(w.charAt(i))) {
					liczbasamp++;
				} 
			}
			calosc += liczbasamp;
			println (w + " ma samoglosek: " + liczbasamp); 
		}
		println("suma samoglosek: " + calosc);
	}
}