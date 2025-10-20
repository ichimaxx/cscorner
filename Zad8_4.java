import java.util.*;
import static myutils.Skrocenie_Print.*;

class Shape {
	public void draw() {}
	public void erase() {}
	public void show() {
		println("Shape.show()");
	}
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
	@Override
	public void show() {
		println("Circle.show()");
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
	@Override
	public void show() {
		println("Square.show()");
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
	@Override
	public void show() {
		println("Triangle.show()");
	}
}
class Trapez extends Shape { 
	@Override
	public void draw() { 
		println("Trapez.draw()");
	}
	@Override
	public void erase() {
		println("Trapez.erase()"); 
	}
	@Override
	public void show() {
		println("Trapez.show()");
	}
}
class RandomShapeGenerator {
	private Random rand = new Random();
	public Shape next() {
		switch(rand.nextInt(4)) {
			default:
			case 0: return new Circle();
			case 1: return new Square();
			case 2: return new Triangle();
			case 3: return new Trapez();
		}
	}
}
public class Zad8_4 {
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