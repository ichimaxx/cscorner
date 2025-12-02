import static myutils.Skrocenie_Print.*;
import java.util.*; 


public class Zad11_9 {
	private Object[] items;
	private int next = 0;
	public Zad11_9(int size) { 
		items = new Object[size]; 
	}
	public void add(Object x) {
		if(next < items.length)
				items[next++] = x;
	}
	private class SequenceSelector implements Iterator<Object>{
		private int i = 0;
		public boolean hasNext() { 
			return i < next; 
		}
		public Object next() { 
			return items[i++]; 
		}
	}

	public Iterator<Object> iterator() {
		return new SequenceSelector();
	}
	public static void display(Iterator<Object> it) {
        while (it.hasNext()) {
            Object h = it.next();
            print(h + " ");
        }
    }
	
	public static void main(String[] args) {
		Zad11_9 sequence = new Zad11_9(10);
		for(int i = 0; i < 10; i++) {
			sequence.add(i);
		}
		Iterator<Object> it = sequence.iterator();
		display(it);
	}
}