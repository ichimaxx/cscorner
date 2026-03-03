import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;
import java.io.File;

/*
	Exercise 16: (5) Modify JGrep.java to accept a directory name or a file name as
	argument (if a directory is provided, search should include all files in the directory). Hint:
	You can generate a list of file names with:
	
	File[] files = new File(".").listFiles(); 

*/

public class Zad13_16 {
	public static void main(String[] args) throws Exception {
		if(args.length < 2) {
			System.out.println("Usage: java Zad13_16 file/directory regex [flags if u want]");
			System.exit(0);
		}
		File path = new File(args[0]);
			
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
		if (path.isDirectory()) {
			File[] files = path.listFiles(); 
			for(File f : files) {
				// if (f.isDirectory()) continue; // to pomija podfoldery w danej scieżce, mozna dodać, nie trzeba
				for (String line : new TextFile(f.getPath())) {
					m.reset(line);
						while(m.find())
							System.out.println(index++ + ": " + m.group() + ": " + m.start() + " " + f.getName());
				}
			}
		}
		else {
			for(String line : new TextFile(args[0])) {
				m.reset(line);
				while(m.find())
					System.out.println(index++ + ": " + m.group() + ": " + m.start());
			}
		}
	}
}

// jak chcesz odpalic kod dla przykładu możesz wpisać: java Zad13_16 C:\Users\ichim\Desktop\cscorner\Rozdzial_13 "\b[Ssct]\w+" DOTALL