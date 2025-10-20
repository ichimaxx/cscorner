import java.util.*;
import static myutils.Skrocenie_Print.*;

class Shape {
 public void draw() {}
 public void erase() {}
}
class Circle extends Shape { 
	@Override
	public void draw() { 
		println("Circle.draw()");
	}
	@Override
	public void erase() {
		println("Circle.erase()"); 
	}
}
class Square extends Shape {
	@Override
	public void draw() {
		println("Square.draw()"); 
	}
	@Override
	public void erase() {
		println("Square.erase()");
	}
}
class Triangle extends Shape {
	@Override
	public void draw() {
		println("Triangle.draw()");
	}
	@Override
	public void erase() {
		println("Triangle.erase()"); 
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
public class Zad8_2 {
	private static RandomShapeGenerator gen = new RandomShapeGenerator();
	public static void main(String[] args) {
		Shape[] s = new Shape[9];
		// Fill up the array with shapes:
		for(int i = 0; i < s.length; i++)
			s[i] = gen.next();
		// Make polymorphic method calls:
		for(Shape shp : s)
			shp.draw();
	}
} 