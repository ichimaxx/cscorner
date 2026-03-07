import java.io.*;
import static myutils.Skrocenie_Print.*;
import net.mindview.util.*;
import java.util.*; 


/*
Exercise 20: (2) Create a class that contains int, long, float and double and String
fields. Create a constructor for this class that takes a single String argument, and scans that
string into the various fields. Add a toString( ) method and demonstrate that your class
works correctly. 
*/

public class Zad13_20 {
	String imie;
	int i;
	double d;
	float f;
	long l;
	Zad13_20(String input) {
		Scanner skn = new Scanner(input);
		this.imie = skn.nextLine(); // tu bierzemy cala linie ale ze wzgledu na to ze input ma slowa oddzielone \n to bedzie to tylko jedno slowo
		//this.l = skn.nextLong(); -- jakbhysmy dali np long jako drugi to wywali exception bo w kolejnosci naszego inputu drugi jest int a nie long
		this.i = skn.nextInt();
		this.d = skn.nextDouble();
		this.f = skn.nextFloat();
		this.l = skn.nextLong();
		// to przypisuje od razu dane ktore skanujesz do danego typu i to jest odpowiednia kolejnosc
	}
	public String toString() {
		return "imie=" + imie + " int:" + i + " double:" + d + " float:" + f + " long:" + l; // zamiana na string
	}
	public static void main(String[] args) {
		String input = "Jaceks \n22\n2,442424\n0,53667\n531950395150"; // wprowadzony input
		Zad13_20 ok = new Zad13_20(input);
		println(ok);
	}
}

// skaner generalnie leci po drodze Stringa token po tokenie(token to fragment tekstu(tu inputu) rozdzielony spacją(bialymi znakami bo są też inne niz spacja tzw "whitespace") w stringu) po drugim z kazdym wywolaniem skaner.next() tu pokazane jest jak mozna od razu przypisac dane pole idac skanerem dla przykladu do inta jesli pasuje