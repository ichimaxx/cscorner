import static myutils.Skrocenie_Print.*;
/*
Exercise 15: (2) Add a RectangularGlyph to PolyConstructors.java and
demonstrate the problem described in this section.
*/

class Glyph {
	void draw() { 
		print("Rozdzial_8.Glyph.draw()");
	}
	Glyph() {
		println("Rozdzial_8.Glyph() before draw()");
		draw();
		println("Rozdzial_8.Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;
	RoundGlyph(int r) {
		radius = r;
		println("Rozdzial_8.RoundGlyph.Rozdzial_8.RoundGlyph(), radius = " + radius);
	}
 
	@Override
	void draw() {
		println("Rozdzial_8.RoundGlyph.draw(), radius = " + radius);
	}
}

class RectangularGlyph extends Glyph {
	private int top = 4;
	private int bot = 5;
	RectangularGlyph(int a, int b) {
		top = a;
		bot = b;
		println("Rozdzial_8.RectangularGlyph.Rozdzial_8.RectangularGlyph(), a = " + top + ", b = " + bot);
	}
	@Override
	void draw() {
		println("Rozdzial_8.RectangularGlyph.draw(), a = " + top + ", b = " + bot);
	}
}
	
public class Zad8_15 {
	public static void main(String[] args) {
		Glyph g = new RoundGlyph(5);
		print("\n");
		new RectangularGlyph(6,3);
	}
}

// WNIOSEK: BASE CLASS CONSTRUCTOR CALL DRAW() METHOD AND CUZ OF OVERRIDE IT INITIALIZING OVERRIDEN METHOD FIRST BUT THERES NO VALUES FROM CLASSES INITIALIZED YET SO ITS LEFT AS DEFAULT VALUE 0,FALSE,NULL  THATS WHY ITS 0 IN RECTANGULARGLYPH() METHOD AND ROUNDGLYPH().