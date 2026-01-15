import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 15: (2) Show that WithFinally.java doesn’t fail by throwing a
RuntimeException inside the try block. */
class Switch {
	private boolean state = false;
	public boolean read() { 
		return state; 
	}
	public void on() { 
		state = true; 
		println(this); 
	}
	public void off() { 
		state = false; 
		println(this); 
	}
	public String toString() { 
		return state ? "on" : "off"; 
	}
} ///:~
//: exceptions/OnOffException1.java
class OnOffException1 extends Exception {}
class OnOffException2 extends Exception {}

public class Zad12_15 {
	private static Switch sw = new Switch();
	public static void f() throws OnOffException1,OnOffException2 {} 
	public static void main(String[] args) {
		try {
			sw.on();
			// Code that can throw exceptions...
			f();
			throw new RuntimeException();
		} catch(OnOffException1 e) {
			System.out.println("OnOffException1");
		} catch(OnOffException2 e) {
			System.out.println("OnOffException2");
		} finally {
			sw.off();
		}
	}
}
/* 
C:\Users\ichim\Desktop\cscorner\Rozdzial_12>java Zad12_15
on
off
Exception in thread "main" java.lang.RuntimeException
        at Zad12_15.main(Zad12_15.java:34)

jak widac po kompilacji nawet jak catch nie zlapalo runtimeexception() to finally(cleanup) sie wykona i zakonczy try{} */