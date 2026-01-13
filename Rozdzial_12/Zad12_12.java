//: innerclasses/Sequence.java
// Holds a sequence of Objects.
import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 12: (3) Modify innerclasses/Sequence.java so that it throws an appropriate
exception if you try to put in too many elements. */
interface Selector {
	boolean end();
	Object current();
	void next();
}
class TestowyException extends Exception {}
public class Zad12_12 {
	private Object[] items;
	private int next = 0;
	public Zad12_12(int size) { 
		items = new Object[size]; 
	}
	public void add(Object x) throws TestowyException { // wyjatek wyrzucany w metodzie add()
		if(next >= items.length) {
			throw new TestowyException();
		}
			items[next++] = x;
	}
	private class SequenceSelector implements Selector {
		private int i = 0;
		public boolean end() { 
			return i == items.length; 
		}
		public Object current() { 
			return items[i]; 
		}
		public void next() { 
			if(i < items.length) i++; 
		}
	}
			
	public Selector selector() {
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Zad12_12 sequence = new Zad12_12(10);
		try {
			for(int i = 0; i < 16; i++) {
				sequence.add(Integer.toString(i));
			}	
		} catch (TestowyException e) {
			println("too many elements, maximum in array is: " + sequence.items.length);
		}		
			Selector selector = sequence.selector();
			while(!selector.end()) {
				System.out.print(selector.current() + " ");
				selector.next();
			}
	}
}