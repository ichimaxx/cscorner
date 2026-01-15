import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 14: (2) Show that OnOffSwitch.java can fail by throwing a
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

public class Zad12_14 {
	private static Switch sw = new Switch();
	public static void f()
		throws OnOffException1,OnOffException2 {}
	public static void main(String[] args) {
		try {
			sw.on();
			// Code that can throw exceptions...
			f();
			throw new RuntimeException();
		} catch(OnOffException1 e) {
			System.out.println("OnOffException1");
			sw.off();
		} catch(OnOffException2 e) {
			System.out.println("OnOffException2");
			sw.off();
		} /*finally {
			sw.off();
		}*/ // jakby dopisac taki kod (finally czyli cleanup) to wtedy by wywola sw.off() niewazne czy zlapie ten wyjatek czy nie, finally zawsze sie odpali po wyjsciu z try
	}
} 