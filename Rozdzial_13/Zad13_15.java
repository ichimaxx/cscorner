import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;

/*Exercise 15: (5) Modify JGrep.java to accept flags as arguments (e.g.,
Pattern.CASE_INSENSITIVE, Pattern.MULTILINE). */

public class Zad13_15 {
	public static void main(String[] args) throws Exception {
		if(args.length < 2) {
			System.out.println("Usage: java Zad13_15 file regex [flags if u want]");
			System.exit(0);
		}
		int flags = 0;
		for (int i = 2; i < args.length; i++) {
			String f = args[i];
			
			if (f.equals("CASE_INSENSITIVE")) flags |= Pattern.CASE_INSENSITIVE;
			else if (f.equals("MULTILINE")) flags |= Pattern.MULTILINE;
			else if (f.equals("DOTALL")) flags |= Pattern.DOTALL;
			else { 
				println("podana flaga jest nieznana wpisałes: " + f);
				System.exit(1);
			}
		}
		// dodalem flags jako trzeci argument, sa dodane trzy flagi, DOTALL MULTILINE i CASEINSENSITIVE reszta bez zmian

		Pattern p = Pattern.compile(args[1], flags);
		// Iterate through the lines of the input file:
		int index = 0;
		Matcher m = p.matcher("");
		for(String line : new TextFile(args[0])) {
			m.reset(line);
			while(m.find())
				System.out.println(index++ + ": " +
				m.group() + ": " + m.start());
		}
	}
}

// dla przykladu jak chcesz odpalic kod wpisz: java Zad13_15 Zad13_15.java "\b[Ssct]\w+" DOTALL