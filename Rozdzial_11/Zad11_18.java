import static myutils.Skrocenie_Print.*;
import java.util.*; 

/*Exercise 18: (3) Fill a HashMap with key-value pairs. Print the results to show
ordering by hash code. Extract the pairs, sort by key, and place the result into a
LinkedHashMap. Show that the insertion order is maintained. */

public class
Zad11_18 {
	public static void main (String[] args) {
		Map<String,Integer> mapson = new HashMap<>();
		
		mapson.put("Liczba Pierwsza", 1);
		mapson.put("Liczba Zla", 666);
		mapson.put("Liczba Ratunku", 999);
		mapson.put("Liczba Klopotow", 997);
		mapson.put("Liczba Mojego Mieszkania", 10);
		
		println("hashmap: " + mapson);
		
		Map<String,Integer> posortowane = new TreeMap<>(mapson);
		println("treemap posortoane: " + posortowane);
		
		Map<String,Integer> wlinkedhash = new LinkedHashMap<>(posortowane);
		println("Linkedhashmap: " + wlinkedhash); 
	}
}