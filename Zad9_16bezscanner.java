import static myutils.Skrocenie_Print.*;
import java.util.*; 
import java.nio.*;

public class Zad9_16bezscanner {
	private static Random rand = new Random();
	private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private int count;
	public Zad9_16bezscanner(int count) {
		this.count = count;
	}
	public int read(CharBuffer cb) {
		if(count-- == 0)
			return -1; // Indicates end of input
		int appended = 0;
		for(int i = 0; i < 11; i++) {
			cb.append(capitals[rand.nextInt(capitals.length)]);
			appended++;
		}
		if(count >0) {
			cb.append("\n");
			appended++;
		}
		return appended;
	}
	
	public static void main(String[] args) {
		Zad9_16bezscanner lol = new Zad9_16bezscanner(10);
		CharBuffer buf = CharBuffer.allocate(10 * (11 + 1));
		while (lol.read(buf) != -1) {}
		buf.flip();
		println(buf.toString());

	}
}