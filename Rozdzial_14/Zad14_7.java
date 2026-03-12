
import static myutils.Skrocenie_Print.*;

/*
Exercise 7: (3) Modify SweetShop.java so that each type of object creation is
controlled by a command-line argument. That is, if your command line is "Java Sweetshop
Candy," then only the Candy object is created. Notice how you can control which Class
objects are loaded via the commandline argument. 
*/

class Candy {
	static { println("Loading Candy"); }
} 
class Gum {
	static { println("Loading Gum"); }
}
class Cookie {
	static { println("Loading Cookie"); }
}
public class Zad14_7 {
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: java Zad14_7 nazwaklasy");
			System.exit(0);
		}
		println("inside main");
		String f = args[0];
		String nazwa = null;
		if (f.equals("Candy")) nazwa = "Candy";
			else if (f.equals("Gum")) nazwa = "Gum";
			else if (f.equals("Cookie")) nazwa = "Cookie";
			else { 
				println("podana klasa jest nieznana wpisałes: " + f);
				System.exit(1);
			}
		try {
			Class.forName(nazwa);
		} catch(ClassNotFoundException e) {
			println("Couldn’t find " + nazwa);
		}
		println("After Class.forName (\"" + nazwa + "\")");
	}
}