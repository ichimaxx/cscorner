import java.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 24: (2) Fill a LinkedHashMap with String keys and objects of your choice.
Now extract the pairs, sort them based on the keys, and reinsert them into the Map.*/

public class Zad11_24 { 
	private static Integer a = 50;
	private static Integer b = 150;
	private static Integer c = 20;
	private static Integer d = 40;
	private static Integer e = 70;
	private static String f = "ok";
	private static String g = "no";
	private static String h = "yes";
	private static String i = "nook";
	private static String j = "ofc";
	public static void main(String[] args) {
		Map<String, Integer> hk = new LinkedHashMap<>();
		hk.put(f, a);
		hk.put(g, b);
		hk.put(h, c);
		hk.put(i, d);
		hk.put(j, e);
		println("przed sortem: " + hk);
		// robie liste wpisow z mapy zeby mozna bylo ja sortowac po kluczu czyli po key, bo nie mozna uzyc sort na mapie taki triczek
		List<Map.Entry<String, Integer>> ok = new ArrayList<>(hk.entrySet());
		
		println("po ekstrakcji do listy: " + ok);
		
		ok.sort(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER));
		Map<String, Integer> hko = new LinkedHashMap<>();
		
		// petla po list zeby wrzucic wszystkie klucze i value z powrotem z listy do nowej mapy
		for (Map.Entry<String, Integer> ek : ok) {
			hko.put(ek.getKey(), ek.getValue());
		}
		println("posortowane wrzucone do nowej mapy" + hko);
		
		
			
	}
}