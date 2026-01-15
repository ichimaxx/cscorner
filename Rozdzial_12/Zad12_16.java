import static net.mindview.util.Print.*;
import java.util.*; 
/*Exercise 16: (2) Modify reusing/CADSystem.java to demonstrate that returning
from the middle of a try-finally will still perform proper cleanup. */
class Shaper {
	protected Shaper(int i) { print("Shaper constructor"); }
	void dispose() { print("Shaper dispose"); }
}
class Circles extends Shaper {
	Circles(int i) {
		super(i);
		print("Drawing Circles");
	}
	void dispose() {
		print("Erasing Circles");
		super.dispose();
	}
}
class Triangles extends Shaper {
	Triangles(int i) {
		super(i);
		print("Drawing Triangles");
	}
	void dispose() {
		print("Erasing Triangles");
		super.dispose();
	}
}
class Lines extends Shaper {
	private int start, end;
	Lines(int start, int end) {
		super(start);
		this.start = start;
		this.end = end;
		print("Drawing Lines: " + start + ", " + end);
	}
	void dispose() {
		print("Erasing Lines: " + start + ", " + end);
		super.dispose();
	}
}
public class Zad12_16 extends Shaper {
	private Circles c;
	private Triangles t;
	private Lines[] lines = new Lines[3];
	public Zad12_16(int i) {
			super(i + 1); 
		for(int j = 0; j < lines.length; j++)
				lines[j] = new Lines(j, j*j);
				c = new Circles(1);
				t = new Triangles(1);
			try {
				print("Combined constructor");
				return;
			} finally {
				print("Finally z konstruktora");
			}
	}
	public void dispose() {
		print("Zad12_16.dispose()");
		// The order of cleanup is the reverse
		// of the order of initialization:
		t.dispose();
		c.dispose();
		for(int i = lines.length - 1; i >= 0; i--)
			lines[i].dispose();
		super.dispose();
	}
	public static void main(String[] args) {
		print("Initialization that requires cleanup");
		Zad12_16 x = new Zad12_16(47);
		try {
			print("Return from main try");
			return;
		} finally {
			x.dispose();
			print("Performing cleanup im main after dispose()");
		}
	}
}

// to ma udowodnic ze po return; w try i tak bedzie finally za kazdym razem