import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/* Exercise 26: (4) Take the resulting Map from the previous exercise and re-create the
order of the words as they appeared in the original file.  */

public class Zad11_26 {
	public static void main(String[] args) {
		Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		TextFile tff = new TextFile("Zad11_20.java", "\\W+");
		Iterator<String> it = tff.iterator();
			Integer i = 0;
			while(it.hasNext()) {
				String word = it.next();
				if(word.isEmpty()) continue;
				i++;		
				if(!map.containsKey(word)) {
					map.put(word, new ArrayList<Integer>());
				}
				map.get(word).add(i);
			}
		ArrayList<String> output = new ArrayList<>();
		println(map);
		int razem = i;
		for(int k = 1 ; k <= razem; k++) {
			for (Map.Entry<String, ArrayList<Integer>> e : map.entrySet()) {
				if (e.getValue().contains(k)) {
					output.add(e.getKey());
					break; // PO TO ZEBY PO ZNALEZIENIU SLOWA LECIALO OD NOWA PETLE A NIE LECIALO DALEJ PO PETLI
				}
			}
		}
			
		println("\nPrzywrocenie tekstu po kolei: " + output);
	}
}