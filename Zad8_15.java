import static myutils.Skrocenie_Print.*;


class Glyph {
	void draw() { 
		print("Glyph.draw()"); 
	}
	Glyph() {
		println("Glyph() before draw()");
		draw();
		println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;
	RoundGlyph(int r) {
		radius = r;
		println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}
 
	@Override
	void draw() {
		println("RoundGlyph.draw(), radius = " + radius);
	}
}

class RectangularGlyph extends Glyph {
	private int top = 4;
	private int bot = 5;
	RectangularGlyph(int a, int b) {
		top = a;
		bot = b;
		println("RectangularGlyph.RectangularGlyph(), a = " + top + ", b = " + bot);
	}
	@Override
	void draw() {
		println("RectangularGlyph.draw(), a = " + top + ", b = " + bot);
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