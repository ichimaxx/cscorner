import java.util.regex.*;
import static myutils.Skrocenie_Print.*;


public class Zad13_10 {
	public static String test = "Java now has regular expressions";
	static boolean matches(String regex, CharSequence input){
		boolean b = Pattern.compile(regex).matcher(input).find(); // uproszczona metoda
		return b;
		/*KIEDYŚ BY SIĘ NAPISALO:
		
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(input);
			if(m.find()) {
				return true;
			} 
			else 
				return false;
 
		*/
		
	}
	public static void main(String[] args) {
		println(matches("^Java",test));
		println(matches("\\Breg.*",test));
		println(matches("n.w\\s+h(a|i)s",test));
		println(matches("s?",test));
		println(matches("s*",test));
		println(matches("s+",test));
		println(matches("s{4}",test));
		println(matches("S{1}.",test));
		println(matches("s{0,3}",test));
	}
}