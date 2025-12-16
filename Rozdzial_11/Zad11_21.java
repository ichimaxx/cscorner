import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 21: (3) Using a Map<String,Integer>, follow the form of
UniqueWords.java to create a program that counts the occurrence of words in a file. Sort
the results using Collections.sort( ) with a second argument of
String.CASE_INSENSITIVE_ORDER (to produce an alphabetic sort), and display the
result. */


//program generalnie zlicza poszxczegolne String z programu Zad11_20.java, liczy sobie ile razy pojawilo sie slowo a pozniej przerzuca to w ArrayList ktory jest posortowany metoda sort() z collections.
public class Zad11_21 {
	public static void main(String[] args) {
		Map<String, Integer> mapson = new HashMap<String, Integer>();
		TextFile tf = new TextFile("Zad11_20.java", "\\W+");
		Iterator<String> it = tf.iterator();
		while(it.hasNext()) {
			String word = it.next();
			mapson.put(word, mapson.getOrDefault(word, 0) + 1);
		}
		System.out.println("MAPA NIEPOSORTOWANA Z POLICZONYMI SLOWAMI: " + mapson);
		ArrayList<String> ok = new ArrayList<String>(mapson.keySet());
		Collections.sort(ok, String.CASE_INSENSITIVE_ORDER);
		println("");
		System.out.println("ARRAYLIST POSORTOWANE SLOWA Z MAPY W KOLEJNOSCI ALFABETYCZNEJ: " + ok);
		println("");
		println("Wynik alfabetycznie sciagniety z ArrayList: ");
		for(String word : ok) {
			println(word + "=" + mapson.get(word));
		}
	}
} 