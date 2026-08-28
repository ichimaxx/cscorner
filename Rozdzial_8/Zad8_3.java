import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (1) Add a new method in the base class of Shapes.java that prints a
message, but don’t override it in the derived classes. Explain what happens. Now override it
in one of the derived classes but not the others, and see what happens. Finally, override it in
all the derived classes.

*/
class Shape_4 {
	public void draw() {}
	public void erase() {}
	public void show() {
		println("Rozdzial_8.Shape.show()");
	}
}
class Circle_4 extends Shape_4 {
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
class Square_4 extends Shape_4 {
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
class Triangle_4 extends Shape_4 {
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
class RandomShapeGenerator {
	private Random rand = new Random(47);
	public Shape_4 next() {
		switch(rand.nextInt(3)) {
			default:
			case 0: return new Circle_4();
			case 1: return new Square_4();
			case 2: return new Triangle_4();
		}
	}
}
public class Zad8_3 {
	private static RandomShapeGenerator gen = new RandomShapeGenerator();
	public static void main(String[] args) {
		Shape_4[] s = new Shape_4[9];
		// Fill up the array with shapes:
		for(int i = 0; i < s.length; i++)
			s[i] = gen.next();
		// Make polymorphic method calls:
		for(Shape_4 shp : s){
			shp.show();
			shp.draw();
			shp.erase();
		}
	}
} 