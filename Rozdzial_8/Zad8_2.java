import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 2: (1) Add the @Override annotation to the shapes example.
*/
class Shape8_2 {
 public void draw() {}
 public void erase() {}
}
class Circle8_2 extends Shape8_2 {
	@Override
	public void draw() { 
		println("Rozdzial_8.Circle.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Circle.erase()");
	}
}
class Square8_2 extends Shape8_2 {
	@Override
	public void draw() {
		println("Rozdzial_8.Square.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Square.erase()");
	}
}
class Triangle8_2 extends Shape8_2 {
	@Override
	public void draw() {
		println("Rozdzial_8.Triangle.draw()");
	}
	@Override
	public void erase() {
		println("Rozdzial_8.Triangle.erase()");
	}
}
class RandomShapeGenerator8_2 {
	private Random rand = new Random(47);
	public Shape8_2 next() {
		switch(rand.nextInt(3)) {
			default:
			case 0: return new Circle8_2();
			case 1: return new Square8_2();
			case 2: return new Triangle8_2();
		}
	}
}
public class Zad8_2 {
	private static RandomShapeGenerator8_2 gen = new RandomShapeGenerator8_2();
	public static void main(String[] args) {
		Shape8_2[] s = new Shape8_2[9];
		// Fill up the array with shapes:
		for(int i = 0; i < s.length; i++)
			s[i] = gen.next();
		// Make polymorphic method calls:
		for(Shape8_2 shp : s)
			shp.draw();
	}
} 