import java.util.regex.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 13: (2) Modify StartEnd.java so that it uses Groups.POEM as input, but
still produces positive outputs for find( ), lookingAt( ) and matches( ). */

public class Zad13_13 {
	public static String input = Zad13_12.POEM; // sciaga poem z Zad13_12 bez przepisywania poem 

	private static class Display {
		private boolean regexPrinted = false;
		private String regex;
		Display(String regex) { 
			this.regex = regex; 
		}
		void display(String message) {
			if(!regexPrinted) {
				println(regex);
				regexPrinted = true;
			}
			println(message);
		}
	}
	static void examine(String s, String regex) {
		Display d = new Display(regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		while(m.find())
			d.display("find() ‘" + m.group() + "‘ start = "+ m.start() + " end = " + m.end());
		if(m.lookingAt()) // No reset() necessary
			d.display("lookingAt() start = " + m.start() + " end = " + m.end()); // odpali sie kiedy start() bedzie 0 czyli jak bedzie match od poczatku Stringa, i wtedy zwraca true
		if(m.matches()) // No reset() necessary
			d.display("matches() start = " + m.start() + " end = " + m.end()); // odpala sie dopiero jak w cala linia bedzie pasowala do danego regex w iteracji(Z Beware.* najprosciej bo zaczyna sie od poczatku i leci do samego konca Stringa)
	}
	public static void main(String[] args) {
		for(String in : input.split("\n")) {
			println("input : " + in);
				for(String regex : new String[]{"\\w*oves\\w*", "\\w*ves", "T\\w+", "Beware.*"}) // zmodyfikowane pod Zad13_13
					examine(in, regex);
		}
	}
} 