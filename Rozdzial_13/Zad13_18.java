import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;
import java.io.File;

/* 
	Exercise 18: (8) Write a program that reads a Java source-code file (you provide the file name on the command line) and displays all the string literals in the code.   
*/

public class Zad13_18 {
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("Usage: java Zad13_18 file/directory [flags if u want]");
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
	Pattern p = Pattern.compile("\"([^\"\\\n]|\\\\(?:[btnfr\"'\\\\]))*\"", flags); // " xd "zeby stworzyc regex szukajacy "string literals" trzeba wpisac trzy backslash \\\ zeby zaczęło traktować " jako znak a nie część stringa
		// Iterate through the lines of the input file:
		Matcher m = p.matcher(content);
			while(m.find())
				System.out.println(m.group() + ": " + m.start() + "\n");
	}
}


/* jak chcesz odpalic kod dla przykładu możesz wpisać: java Zad13_18 Zad13_17.java 


REGEX : "\"([^\"\\\n]|\\\\(?:[btnfr\"'\\\\]))*\""

OPIS:

\" -- odpowiada za poczatek, otwierajacy cudzyslow
[^\\\"\\\n] -- sekwencja co jest dopuszczalne pomiedzy naszymi cudzyslowami:
^ - to oznacza ze dopuszcza wszystko oprócz wymienionych po nim, czyli: 
\" -- cudzyslow, w przypadku javowych regexow trzeba czasami uzyc kilka backslashy zeby dany zlepek kodu zamienil sie na znak który nas interesuje, w tym przypadku \" to w rzezcywistosci jeden cudzyslow "
\\\ -- jeden backslash to samo co w przypadku cudzyslowa, escaped
\n - newline czyli rozpoczecie nowej linii, i on to bedzie ignorowac
\\\\(?:[btnfr\"'\\\\] -- sekwencja dopuszczajaca dane znaki po backslashu \
\\\\ - 2 backslash escaped
i po nich moze byc: b, t, n, f, r, \, ", ', nastepny backslash ktory jest escaped
* -- star mowi ze zero lub wiecej razy moze sie pojawic znak lub sekwencja ktora wypisalismy
\" odpowiada za to czym sie bedzie konczyl nasz zakres poszukiwania i to w tym przypadku znow jest cudzyslow

*/