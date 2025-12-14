import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;

/* Exercise 20: (3) Modify Exercise 16 so that you keep a count of the occurrence of each
vowel. */

public class Zad11_20 extends UniqueWords {
	public static void main (String[] args) {
		Set<String> words = new TreeSet<String>(new TextFile("SetOperations.java", "\\W+"));
		ArrayList<String> nwords = new ArrayList<String>();
		Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
		
		Iterator<String> it = words.iterator();
		
		int calosc = 0;
		int licznik_A = 0;
		int licznik_E = 0;
		int licznik_I = 0;
		int licznik_O = 0;
		int licznik_U = 0;
		
		while(it.hasNext()) {
			String w = it.next();
			int liczbasamp = 0;
			for (int i = 0; i < w.length(); i++) {
				switch(w.charAt(i)) {
					case 'A': case 'a': licznik_A++; break;
					case 'E': case 'e': licznik_E++; break;
					case 'I': case 'i': licznik_I++; break;
					case 'O': case 'o': licznik_O++; break;
					case 'U': case 'u': licznik_U++; break;
				}
				if (vowels.contains(w.charAt(i))) {
					liczbasamp++;
				} 
			}
			calosc += liczbasamp;
			println (w + " ma samoglosek: " + liczbasamp); 
		}
		println("\nrazem samoglosek A: " + licznik_A);
		println("razem samoglosek E: " + licznik_E);
		println("razem samoglosek I: " + licznik_I);
		println("razem samoglosek O: " + licznik_O);
		println("razem samoglosek U: " + licznik_U);
		println("suma samoglosek: " + calosc);
	}
}