import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (2) Add a new type of Shape to Shapes.java and verify in main( ) that
polymorphism works for your new type as it does in the old types.
*/
class Shape_1 {
	public void draw() {}
	public void erase() {}
	public void show() {
		println("Rozdzial_8.Shape.show()");
	}
}
class Circle_1 extends Shape_1 {
	@Override
	public void draw() { 
		println("Rozdzial_8.Circle.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Circle.erase()");
	}
	@Override
	public void show() {
		println("Rozdzial_8.Circle.show()");
	}
}
class Square_1 extends Shape_1 {
	@Override
	public void draw() {
		println("Rozdzial_8.Square.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Square.erase()");
	}
	@Override
	public void show() {
		println("Rozdzial_8.Square.show()");
	}
}

class Triangle_1 extends Shape_1 {
	@Override
	public void draw() {
		println("Rozdzial_8.Triangle.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Triangle.erase()");
	}
	@Override
	public void show() {
		println("Rozdzial_8.Triangle.show()");
	}
}
class Trapez_1 extends Shape_1 {
	@Override
	public void draw() { 
		println("Rozdzial_8.Trapez.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Trapez.erase()");
	}
	@Override
	public void show() {
		println("Rozdzial_8.Trapez.show()");
	}
}
class RandomShapeGenerator_1 {
	private Random rand = new Random();
	public Shape_1 next() {
		switch(rand.nextInt(4)) {
			default:
			case 0: return new Circle_1();
			case 1: return new Square_1();
			case 2: return new Triangle_1();
			case 3: return new Trapez_1();
		}
	}
}
public class Zad8_4 {
	private static RandomShapeGenerator_1 gen = new RandomShapeGenerator_1();
	public static void main(String[] args) {
		Shape_1[] s = new Shape_1[9];
		// Fill up the array with shapes:
		for(int i = 0; i < s.length; i++)
			s[i] = gen.next();
		// Make polymorphic method calls:
		for(Shape_1 shp : s){
			shp.show();
			shp.draw();
			shp.erase();
		}
	}
} 