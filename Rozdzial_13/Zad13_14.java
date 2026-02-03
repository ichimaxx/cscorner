import java.util.regex.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 14: (1) Rewrite SplitDemo using String.split( ). */

public class Zad13_14 {
	public static void main(String[] args) {
		String input = "This!!unusual use!!of exclamation!!points";
		//println(Arrays.toString(Pattern.compile("!!").split(input))); // stare
		//println(Arrays.toString(Pattern.compile("!!").split(input, 3))); // stare
		println(Arrays.toString(input.split("!!"))); // mozna w ten sposob zrobic bez pisania pattern.compile(bo on tam w rzeczywistosci dalej jest uzywany)... Przy String.split() wywolujemy split() na Stringu, który chcemy splitnac (tu: input) czyli input.split(String regex)
		println(Arrays.toString(input.split("!!", 3))); // z limitem input.split(String regex, int limit)
		
	}
} 