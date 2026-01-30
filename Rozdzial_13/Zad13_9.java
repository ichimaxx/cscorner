import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.math.*;

/*Exercise 9: (4) Using the documentation for java.util.regex.Pattern as a resource,
replace all the vowels in Splitting.knights with underscores. */

public class Zad13_9 {
	static String s = Splitting.knights; 
	public static void main(String[] args) { 
		print("zamienione z pomoca | : " + s.replaceAll("a|e|i|o|u|A|E|I|O|U", "_")); 
		print("\n");
		print("zamienione z pomoca [] : " + s.replaceAll("[aeiouAEIOU]", "_")); // [] DZIALA TYLKO NA CHARACTERS!! jakby trzeba bylo zamieniac tym slowa to by to nie zadzialalo bo w tej liscie [] moga być tylko characters.
		// mozna zrobic na dwa sposoby albo zgrupowac wszystkie z pomoca listy znaków[] albo z logicznym operatorem OR tak jak zrobilem we wczesniejsdzym przykladzie czyli |
	}
}

	