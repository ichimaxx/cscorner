import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (1) Add a new method in the base class of Shapes.java that prints a
message, but don’t override it in the derived classes. Explain what happens. Now override it
in one of the derived classes but not the others, and see what happens. Finally, override it in
all the derived classes.

*/
class Shape {
	public void draw() {}
	public void erase() {}
	public void show() {
		println("Rozdzial_8.Shape.show()");
	}
}
class Circle extends Shape { 
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
class Square extends Shape {
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
class Triangle extends Shape {
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
	public Shape next() {
		switch(rand.nextInt(3)) {
			default:
			case 0: return new Circle();
			case 1: return new Square();
			case 2: return new Triangle();
		}
	}
}
public class Zad8_3 {
	private static RandomShapeGenerator gen = new RandomShapeGenerator();
	public static void main(String[] args) {
		Shape[] s = new Shape[9];
		// Fill up the array with shapes:
		for(int i = 0; i < s.length; i++)
			s[i] = gen.next();
		// Make polymorphic method calls:
		for(Shape shp : s){
			shp.show();
			shp.draw();
			shp.erase();
		}
	}
} 