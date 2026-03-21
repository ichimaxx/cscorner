import java.lang.reflect.*;
import java.util.regex.*;
import static net.mindview.util.Print.*;

/*
Exercise 18: (1) Make ShowMethods a non-public class and verify that the
synthesized default constructor no longer shows up in the output.
*/

class Zad14_18 {
	private static String usage = "usage:\n" +
		 "Zad14_18 qualified.class.name\n" +
		 "To show all methods in class or:\n" +
		 "Zad14_18 qualified.class.name word\n" +
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
OUTPUT dla java Zad14_18 Zad14_18:


public static void main(String[])
public boolean equals(Object)
public String toString()
public int hashCode()
public Class getClass()
public void notify()
public void notifyAll()
public void wait(long) throws InterruptedException
public void wait(long,int) throws InterruptedException
public void wait() throws InterruptedException

po zrobieniu głównej klasy Zad14_18 non-public domyslny konstruktor Zad14_18 już się nie pokazuje w output tak jak to jest w przypadku klasy publicznej
jest to spowodowane hierarchia dostepu w javie, w momencie zmiany klasy na niepubliczna domyslny konstruktor Zad14_18 rowniez staje sie niepubliczny a metody getMethods() i getConstructors() wyciągają tylko publiczne metody i konstruktory 
w tym momencie jesli chcielibysmy zobaczyc z powrotem konstruktor Zad14_17() musielibysmy zxamienic getConstructors() na getDeclaredConstructors() 
*/