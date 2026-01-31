import java.util.regex.*;
import static myutils.Skrocenie_Print.*;


public class Zad13_11 {
	public static String test3 = "Arline ate eight apples and one orange while Anita hadn’t any";
	static void matchess(String regex, CharSequence input){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while(m.find()) { 
			println(m.group());
		}
	}
	public static void main(String[] args) {
		Zad13_11 k = new Zad13_11();
		k.matchess("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b", test3); // ze wzgledu na to ze uzywamy String expression trzy miejsca z backslash trzeba podwoić, pierwsza \\s było \s druga \\w+? była \w+? a trzecia \\b
		
	}
}