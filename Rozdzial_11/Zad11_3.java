import java.util.*;
import static myutils.Skrocenie_Print.*;

interface Selector_3 {
	boolean end();
	Object current();
	void next();
}
public class Zad11_3 {
	private List<Object> items = new ArrayList<Object>(); // zamiast tablicy object robimy List upcastujac do ArrayList
	public Zad11_3() {}
	public void add(Object x) {
		items.add(x); // zamieniamy metode length z tablicy na metode z interfejsu collection
	}
	private class SequenceSelector implements Selector_3 {
		private int i = 0;
		public boolean end() { return i == items.size(); } // zamieniamy metody length z tablicy na metody z interfejsu collection
		public Object current() { return items.get(i); } // zamieniamy metody length z tablicy na metody z interfejsu collection
		public void next() { if(i < items.size()) i++; } // zamieniamy metody length z tablicy na metody z interfejsu collection
	}
	public Selector_3 selector() {
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Zad11_3 sequence = new Zad11_3();
		for(int i = 0; i < 10; i++){
			sequence.add(i);
		}
		Selector_3 selector = sequence.selector();
		while(!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
	}
}