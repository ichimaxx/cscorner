import static myutils.Skrocenie_Print.*;
/*
Exercise 16: (3) Create a class that produces a sequence of chars. Adapt this class so
that it can be an input to a Scanner object.
*/
import java.util.*;
import java.nio.*;
public class Zad9_16 implements Readable {
	private static Random rand = new Random();
	private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private int count; 
	public Zad9_16(int count) { 
		this.count = count; 
	} 
	public int read(CharBuffer cb) {
		if(count-- == 0) return -1; // Indicates end of input 
		for(int i = 0; i < 10; i++) {
			cb.append(capitals[rand.nextInt(capitals.length)]); 
		} 
		cb.append(" ");
		return 10; 
	} 
	public static void main(String[] args) { 
		Scanner s = new Scanner(new Zad9_16(10));
		while(s.hasNext())
			System.out.println(s.next()); 
	} 
}