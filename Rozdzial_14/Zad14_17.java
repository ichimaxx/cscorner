import java.lang.reflect.*;
import java.util.regex.*;
import static net.mindview.util.Print.*;

/*
Exercise 17: (2) Modify the regular expression in ShowMethods.java to additionally
strip off the keywords native and final (hint: use the OR operator’|’)- 
*/

public class Zad14_17 {
	private static String usage = "usage:\n" +
		 "Zad14_17 qualified.class.name\n" +
		 "To show all methods in class or:\n" +
		 "Zad14_17 qualified.class.name word\n" +
		 "To search for methods involving ‘word’";
private static Pattern p = Pattern.compile("\\w+\\.|\\bfinal\\b\\s*|\\bnative\\b\\s*"); // zmodyfikowano regex 
	public static void main(String[] args) {
		if(args.length < 1) {
			print(usage);
			System.exit(0);
		}
		int lines = 0;
		try {
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor[] ctors = c.getConstructors();
			if(args.length == 1) {
				for(Method method : methods)
					print(p.matcher(method.toString()).replaceAll(""));
				for(Constructor ctor : ctors)
					print(p.matcher(ctor.toString()).replaceAll(""));
				lines = methods.length + ctors.length;
			} else {
				for(Method method : methods)
					if(method.toString().indexOf(args[1]) != -1) {
						print(p.matcher(method.toString()).replaceAll(""));
						lines++;
					}
				for(Constructor ctor : ctors)
					if(ctor.toString().indexOf(args[1]) != -1) {
						print(p.matcher(ctor.toString()).replaceAll(""));
						lines++;
					}
			}
		} catch(ClassNotFoundException e) {
			print("No such class: " + e);
		}
	}
}

/*
REGEX: \\w+\\.|\\bfinal\\b\\s*|\\bnative\\b\\s*

\\w+\\.| mozna powiedziec ze oznacza: ZNAJDZ SLOWO ZAKONCZONE KROPKĄ

w -- znak slowowy typu cyfra/litera/_
+ -- jeden lub wiecej
\. -- prawdziwa kropka, jakby byla bez backslash to sama kropka oznacza "dowolny znak"
| -- operator OR

\\bfinal\\b\\s*|\\bnative\\b\\s*  ZNAJDZ CALE SLOWO FINAL OR NATIVE I EWENTUALNIE SPACJE PO NIM JESLI JEST

\\b --  granica slowa, czyli final ma być traktowany jako całe słowo a nie kawałek innego słowa np finalok
\\b --  zamykane całe słowo final, granica z drugiej strony słowa
\\s -- biały znak np spacja/tab
* -- zero lub wiecej takich(w tym przypadku białych znaków)
 
*/