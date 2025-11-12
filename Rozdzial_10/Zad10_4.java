import static myutils.Skrocenie_Print.*;

interface Selector {
	boolean end();
	Object current();
	void next();
}
public class Zad10_4 {
	private Object[] items;
	private int next = 0;
	public Zad10_4(int size) { 
		items = new Object[size]; 
	}
	public SequenceSelector inner() {
		return new SequenceSelector();
	}
	void h() {
		println("Zad10_4 outer");
	}
	public void add(Object x) {
		if(next < items.length)
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
		public Zad10_4 outer() {
			return Zad10_4.this;
		}
	}	
	public Selector selector() {
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Zad10_4 sequence = new Zad10_4(10);
		for(int i = 0; i < 10; i++)
			sequence.add(Integer.toString(i));
			Selector selector = sequence.selector();
		while(!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
		Zad10_4.SequenceSelector seqin = sequence.inner();
		seqin.outer().h();
	}
}