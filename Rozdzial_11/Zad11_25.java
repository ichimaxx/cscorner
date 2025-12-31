import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/* Exercise 25: (3) Create a Map<String,ArrayList<Integer>>. Use
net.mindview.TextFile to open a text file and read it in a word at a time (use "\\W+" as
the second argument to the TextFile constructor). Count the words as you read them in, and
for each word in the file, record in the ArrayList<Integer> the word count associated with
that word—this is, in effect, the location in the file where that word was found. */

public class Zad11_25 {
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
		println(map);
	}
}