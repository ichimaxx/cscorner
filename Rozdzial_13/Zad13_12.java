import java.util.regex.*;
import java.util.*; 
import static net.mindview.util.Print.*;

/*Exercise 12: (5) Modify Groups.java to count all of the unique words that do not start
with a capital letter. 
*/

public class Zad13_12 {
	static public final String POEM =
		"Twas brillig, and the slithy toves\n" +
		"Did gyre and gimble in the wabe.\n" +
		"All mimsy were the borogoves,\n" +
		"And the mome raths outgrabe.\n\n" +
		"Beware the Jabberwock, my son,\n" +
		"The jaws that bite, the claws that catch.\n" +
		"Beware the Jubjub bird, and shun\n" +
		"The frumious Bandersnatch.";
	public static void main(String[] args) {
		Matcher m = Pattern.compile("\\b\\p{javaLowerCase}\\p{L}*\\b").matcher(POEM); 
		/* 
		do regexa uzywamy z dokumentacji javy: 
		\\b ... \\b to granica slowa miedzy znakiem slowowym \\w a znakiem nieslowowym czyli \\W po to zeby np nie wciagac kropek, przecinkow ale zlapac cale slowo 
		\\p{javaLowerCase} na rozpoczecie od malej litery, i 
		\\p{L}* tak aby bral pod uwage tylko dowolne litery UNICODE w tym tez np polskie znaki, dodatkowo * jest po to zeby lapac slowa w ktorych jest 0 lub wiecej niz jedna litera po znalezieniu slowa z mala litera jakby tego nei bylo to by lapal tylko 2 literowe slowa
		*/
		Set<String> stringset = new TreeSet<String>(); // uzywamy  set do wyodrebnienia unikatowych slow w grupie, bo się powtarzaja
		while(m.find()) 
				stringset.add(m.group());
		
		
		print(stringset); 
		print("\n");
		print("ALL UNIQUE WORDS... rozpoczynajace sie od malej litery: " + stringset.size()); // SIZE() DAJE ILE JEST SLOW W SET 
	 }
}