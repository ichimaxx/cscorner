import static myutils.Skrocenie_Print.*;
import java.util.*; 

/*Exercise 19: (2) Repeat the previous exercise with a HashSet and LinkedHashSet.*/

public class Zad11_19 {
	public static void main (String[] args) {
		Set<String> mapsonek = new HashSet<>();
		
		mapsonek.add("Liczba Pierwsza");
		mapsonek.add("Liczba Zla");
		mapsonek.add("Liczba Ratunku");
		mapsonek.add("Liczba Klopotow");
		mapsonek.add("Liczba Mojego Mieszkania");
		
		println("hashset: " + mapsonek);
		
		Set<String> posortowaned = new TreeSet<>(mapsonek);
		println("treeset posortowane: " + posortowaned);
		
		Set<String> wlinkedhashset = new LinkedHashSet<>(posortowaned);
		println("Linkedhashset: " + wlinkedhashset); 
	}
}