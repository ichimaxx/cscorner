import java.io.*;

/*Exercise 26: (1) Change the file name string in MainException.java to name a file
that doesn’t exist. Run the program and note the result. */

public class Zad12_26 {
	// Pass all exceptions to the console:
	public static void main(String[] args) throws Exception {
		// Open the file:
		FileInputStream file = new FileInputStream("MainException.java");
		// Use the file ...
		// Close the file:
		file.close();
	}
} 
/* Wyrzucilo Stack Trace w main bo on nie obsłużył tego wyjątku (nie ma try{}catch{}) tylko zadeklarował ze throws Exception. Jesli wyjatek nie bedzie obsluzony w main to dochodzi on do JVM i konczy program. 
Dzieki temu nie trzeba czasami pisać try{}catch{} ale ma to sens w malych cwiczeniach

C:\Users\ichim\Desktop\cscorner\Rozdzial_12>java Zad12_26
Exception in thread "main" java.io.FileNotFoundException: MainException.java (Nie można odnaleźć określonego pliku)
        at java.base/java.io.FileInputStream.open0(Native Method)
        at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
        at java.base/java.io.FileInputStream.<init>(FileInputStream.java:159)
        at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
        at Zad12_26.main(Zad12_26.java:6)


*/