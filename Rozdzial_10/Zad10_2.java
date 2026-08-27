import static myutils.Skrocenie_Print.*;

interface Selector_5 {
	boolean end();
	Object current();
	void next();
}
public class Zad10_2 {
	private Object[] items;
	private int next = 0;
	public Zad10_2(int size) { 
		items = new Object[size]; 
	}
	public void add(Object x) {
		if(next < items.length)
			items[next++] = x;
	}
	private class Koniec {
		String k = "koniec";
		Koniec(String k) { 
			this.k = k;
		}
		@Override
		public String toString() { 
			return k;
		}
	}	
	private class SequenceSelector implements Selector_5 {
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
	public Selector_5 selector() {
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Zad10_2 sequence = new Zad10_2(15);
		for(int i = 0; i < 10; i++)
			sequence.add(Integer.toString(i));
		Selector_5 selector = sequence.selector();
		sequence.add(sequence.new Koniec("koniec23"));
		sequence.add(sequence.new Koniec("a pozniej"));
		sequence.add(sequence.new Koniec("inny koniec"));
		sequence.add(sequence.new Koniec("chyba..."));
		sequence.add(sequence.new Koniec("albo nie..."));
		while(!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
	}
}