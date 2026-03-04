import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;
import java.io.File;

/* 
	Exercise 17: (8) Write a program that reads a Java source-code file (you provide the file name on the command line) and displays all the comments.  
*/

public class Zad13_17 {
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("Usage: java Zad13_17 file/directory [flags if u want]");
			System.exit(0);
		}
		int flags = 0;
		String content = TextFile.read(args[0]);
		
		for (int i = 1; i < args.length; i++) {
			String f = args[i];
			
			if (f.equals("CASE_INSENSITIVE")) flags |= Pattern.CASE_INSENSITIVE; // lub dopisac w regex ?i
			else if (f.equals("MULTILINE")) flags |= Pattern.MULTILINE; // lub dopisac w regex ?m
			else if (f.equals("DOTALL")) flags |= Pattern.DOTALL; // lub dopisac w regex ?s
			else if (f.equals("COMMENTS")) flags |= Pattern.COMMENTS; // lub dopisac w regex ?x
			else { 
				println("podana flaga jest nieznana wpisałes: " + f);
				System.exit(1);
			}
		}	
	Pattern p = Pattern.compile("(?m)//.*$|(?s)/\\*.*?\\*/", flags); // regex dwuczlonowy pierwszy czlon odpowiedzialny jest za komentarze z "//..." a drugi po | czyli po operatorze logicznym OR odpowiada za komentarze "/* ... */" flagi dodane sa z poziomu kodu zeby ten regex zawsze się odpalał prawidłowo
		// Iterate through the lines of the input file:
		Matcher m = p.matcher(content);
			while(m.find())
				System.out.println(m.group() + ": " + m.start() + "\n");
	}
}


// jak chcesz odpalic kod dla przykładu możesz wpisać: java Zad13_17 Zad13_17.java 