import static myutils.Skrocenie_Print.*;
/*
Exercise 13: (2) Create an interface, and inherit two new interfaces from that interface.
Multiply inherit a third interface from the second two.

*/

interface ones {
	void one();
}

interface twos extends ones {
	void one();
}

interface twos2 extends ones {
	void one();
}

interface threes extends twos, twos2 {
	void one();
}
interface threes2 extends twos, twos2 {
	void one();
}
interface threes3 extends twos, twos2 {
	void one();
}

class oness implements threes3  {
	public void one() {
		println("one from threes3");
	}
}
public class Zad9_13 {
	public static void main (String[] args) {
		threes3 n = new oness();
		n.one();
	}
}
		
		
