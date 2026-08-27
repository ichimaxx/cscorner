import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 14: (2) Show that OnOffSwitch.java can fail by throwing a
RuntimeException inside the try block. */
class Switch_2 {
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
class OnOffException1_2 extends Exception {}
class OnOffException2_2 extends Exception {}

public class Zad12_14 {
	private static Switch_2 sw = new Switch_2();
	public static void f()
		throws OnOffException1_2,OnOffException2_2 {}
	public static void main(String[] args) {
		try {
			sw.on();
			// Code that can throw exceptions...
			f();
			throw new RuntimeException();
		} catch(OnOffException1_2 e) {
			System.out.println("OnOffException1");
			sw.off();
		} catch(OnOffException2_2 e) {
			System.out.println("OnOffException2");
			sw.off();
		} /*finally {
			sw.off();
		}*/ // jakby dopisac taki kod (finally czyli cleanup) to wtedy by wywola sw.off() niewazne czy zlapie ten wyjatek czy nie, finally zawsze sie odpali po wyjsciu z try
	}
} 