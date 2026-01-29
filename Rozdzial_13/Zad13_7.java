import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.math.*;

/*Exercise 7: (5) Using the documentation for java.util.regex.Pattern as a resource,
write and test a regular expression that checks a sentence to see that it begins with a capital
letter and ends with a period. */

public class Zad13_7 {
	public static String knights = "Then, when you have found the shrubbery, you must " + "cut down the mightiest tree in the forest... " + "with... a herring!";
	public static void split(String regex) {
		System.out.println(
		Arrays.toString(knights.split(regex)));
	}
	static String s = Zad13_7.knights; 
	public static void main(String[] args) {
		split(" "); // Doesn’t have to contain regex chars
		split("\\W+"); // Non-word characters
		split("n\\W+"); // ‘n’ followed by non-word characters
		print(s.replaceFirst("f\\w+", "located"));
		print(s.replaceAll("shrubbery|tree|herring","banana")); 
		print("\n\nIs this sentence starts with upper case? : ");
		print(Character.toString(knights.charAt(0)).matches("\\p{javaUpperCase}")); // sprawdza czy pierwszy znak w tekscie jest wielką literą
		print("\nIs this sentence ends with an period? : ");
		print(Character.toString(knights.charAt(knights.length() - 1)).matches("\\.")); // sprawdza czy na konc zdania jest kropka
		print("\nRight now there is: " + "'" + Character.toString(knights.charAt(knights.length() - 1)) + "'"); // p[okazuje co jest rzeczywiscie na koncu zdania
	}
} 
