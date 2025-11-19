import static myutils.Skrocenie_Print.*;

interface Selector {
	boolean end();
	Object current();
	void next();
}
public class Zad10_22 {
	private Object[] items;
	private int next = 0;
	public Zad10_22(int size) { 
		items = new Object[size]; 
	}
	public void add(Object x) {
		if(next < items.length)
		items[next++] = x;
	}
	private class SequenceSelector implements Selector {
		private int i = 0;
		@Override
		public boolean end() { 
			return i == items.length; 
		}
		@Override
		public Object current() { 
			return items[i]; 
		}
		@Override
		public void next() { 
			if(i < items.length) i++; 
		}
		Selector reverseSelector() {
			return new Selector() {
				private int i = items.length - 1; // start od ostatniego indeksu czyli np 9 przy rozmiarrze 10
				@Override
				public boolean end() { 
					return i < 0; 
				} // koniec gdy zejdzie ponizej 0
				@Override
				public Object current() { 
					return items[i]; 
				}
				@Override
				public void next() { 
					if(i >= 0) i--; 
				}
			};
		}
		public Selector rev() {
		return reverseSelector();
	}
	}
	public Selector selector() {
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Zad10_22 sequence = new Zad10_22(10);
		for(int i = 0; i < 10; i++)
		sequence.add(Integer.toString(i));
		Selector selector = sequence.selector();
		while(!selector.end()) {
		System.out.print(selector.current() + " ");
		selector.next();
		}
		println("");
		Zad10_22.SequenceSelector go = sequence.new SequenceSelector();
		Selector revselector = go.rev();
		while(!revselector.end()) {
		System.out.print(revselector.current() + " ");
		revselector.next();
		}
	}
} 