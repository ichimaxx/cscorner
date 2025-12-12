import java.util.*;
import net.mindview.util.*; 
import static myutils.Skrocenie_Print.*; 

public class Zad11_16_2 extends UniqueWords { 
	public static void main (String[] args) { 
		Set<String> words = new TreeSet<String>(new TextFile("SetOperations.java", "\\W+")); 
		ArrayList<String> nwords = new ArrayList<String>(); 
		Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U') ); 
		int calosc = 0; 
		int k = 0; 
		Integer liczbasamp = 0; 
		for (int i = 0; i < words.toString().length(); i++) { 
			char g = words.toString().charAt(i); 
			if (vowels.contains(g)) { 
				liczbasamp++; 
				calosc++; 
			} 
			else if (g == ',') { 
				k++; 
				nwords.add("slowo " + k + " ma " + liczbasamp.toString() + " vowels ");
				liczbasamp = 0; 
			} 
		} 
		nwords.add(liczbasamp.toString());
		println("suma samoglosek: " + calosc);
		println(nwords); 
	} 
}