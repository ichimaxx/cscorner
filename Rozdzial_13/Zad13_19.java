import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;
import java.io.File;

/* 
	Exercise 19: (8) Building on the previous two exercises, write a program that examines Java source code and produces all the class names used in a particular program. 
*/

public class Zad13_19 {
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("Usage: java Zad13_19 file/directory [flags if u want]");
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
	Pattern p = Pattern.compile("\\b(?:(?:class|implements|extends|new|throws|catch)\\s+)([A-Za-z_]\\w*)|\\bimport\\s+(?:static\\s+)?([\\w.]+)\\s*;", flags); // " xd "zeby stworzyc regex szukajacy "string literals" trzeba wpisac trzy backslash \\\ zeby zaczęło traktować " jako znak a nie część stringa
		// Iterate through the lines of the input file:
		Matcher m = p.matcher(content);
			while(m.find()){ // jesli zmatchuje ktoras grupe to za kazdym razem wypisuje obie ale pojawi sie tylko ta ktora nie jest null. Jeśli jest null to po prostu danej grupy nie wyprintuje.
				if (m.group(1) != null) {System.out.println(m.group(1) + ": " + m.start(1) + "\n");}
				if (m.group(2) != null) {System.out.println(m.group(2) + ": " + m.start(2) + "\n");} 
			}

	}
}


/* jak chcesz odpalic kod dla przykładu możesz wpisać: java Zad13_19 Zad13_17.java 


REGEX :"\\b(?:(?:class|implements|extends|new|throws|catch)\\s+)([A-Za-z_]\\w*)|\\bimport\\s+(?:static\\s+)?([\\w.]+)\\s*;"


OPIS: 
\\b(?:(?:class|implements|extends|new|throws|catch)\\s+)([A-Za-z_]\\w*)

\\b(?:(?:class|implements|extends|new|throws|catch)\\s+) -- pierwsza grupa non capturing
\\b -- to granica slowa czyli przed jednym ze slow class,iplements etc. nie ma innego znaku slowowego
?: -- to powoduje ze ta grupa jest non capturing wiec nie bedzie mozna jej wywolac m.group(np. 1)
\\s+ -- po tym wyrazie class OR implements OR extends etc. musi byc przynajmniej jedna spacja

([A-Za-z_]\\w*) -- sekwencja jak ma wygladac środek ktory nas w tym przypadku interesuje najbardziej (grupa 1)
A-Za-z_ -- musi sie rozpoczynać od dużej lub malej litery, ewentualnie od _
\\w* -- to oznacza ze po znalezieniu tego pierwszego znaku nastepne musza byc litery,cyfry lub _ bo ten znak w regexie odpowiada za te oznaczenia, * oznacza ze wyswietli 0 lub wiecej znakow, az do znaku ktorego nie zawiera w - czyli word (litera,cyfra,underscore) - [A-Za-z0-9_]

\\bimport\\s+(?:static\\s+)?([\\w.]+)\\s*;

\\bimport\\s+(?:static\\s+)
\\b -- granica slowa
import -- w grupie 2 szukamy co jest po slowie import(import nie jest w grupie2)
\\s+ -- przynajmniej jedna spacja
(?:static\\s+)? -- moze byc ale nie musi(za to odpowiada ? po grupie) slowo static po import, i ono tez jest skipowane (grupa non capturing) i s+ po nim czyli jedna spacja
([\\w.]+)\\s*; -- docelowa m.group(2) musi byc ciag znakow word(litera,cyfra,underscore) lub kropka, + mowi ze musi byc co najmniej jeden taki znak
\\s*; -- nastepnie jest 0 lub wiecej spacji i koniec zakresu jest na ; 


*/