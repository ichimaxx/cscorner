import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.math.*;

/*Exercise 8: (2) Split the string Splitting.knights on the words "the" or “you."*/

class Splitting {
	public static String knights = "Then, when you have found the shrubbery, you must " + "cut down the mightiest tree in the forest... " + "with... a herring!";
	public static void split(String regex) {
		System.out.println(Arrays.toString(knights.split(regex)));
	}
}
public class Zad13_8 {
	static String s = Splitting.knights; 
	public static void main(String[] args) { 
		Splitting.split("the|you"); // potrzebny byl operator logiczny " | " czyli OR (nie jest to and jakby moglo sie wydawac)
	}
}

	