import static myutils.Skrocenie_Print.*;
import java.util.*; 
import java.lang.reflect.*; 

/*
Exercise 10: (3) Write a program to determine whether an array of char is a primitive
type or a true Object. 
*/

public class Zad14_10 {
	public void primiob(Class g) {
		if (g.isPrimitive()) { // mozna znalezc ta metode w dokumentacji odnosnie Class
			println("PRIMITIVE TYPE!!");
		} else {
			println("TRUE OBJECT TYPE");
		}
	}
	public static void main (String[] args) {
		Zad14_10 ka = new Zad14_10();
		println("podkladanie pod checker: char.class");
		println("...");
		ka.primiob(char.class); // przekazujemy klase do metody
		println("\n");
		println("podkladanie pod checker: char[].class");
		println("...");
		ka.primiob(char[].class); // przekazujemy klase do metody
	}
}
		