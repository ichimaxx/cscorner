import static net.mindview.util.Print.*; 
import java.util.*;

/* Exercise 31: (3) Modify polymorphism/shape/RandomShapeGenerator.java to
make it Iterable. You’ll need to add a constructor that takes the number of elements that
you want the iterator to produce before stopping. Verify that it works. 
*/

class Shape {

	public void draw() {}
	public void erase() {}
}
class Circle extends Shape { 
	public void draw() { print("Circle.draw()"); }
	public void erase() { print("Circle.erase()"); }
}
class Square extends Shape {
	public void draw() { print("Square.draw()"); }
	public void erase() { print("Square.erase()"); }
}
class Triangle extends Shape {
	public void draw() { print("Triangle.draw()"); }
	public void erase() { print("Triangle.erase()"); }
} 
//totalna przerobka 
class Rsg implements Iterable<Shape> {
	private Random rand = new Random();
	private int ile;
	private Shape[] shapes = { new Circle(), new Square(), new Triangle() };
	//wymagane w zadaniu musi byc konstruktor ;/
	public Rsg(int ile) {
		this.ile = ile;
	}
	// iterator zamiast switch
	public Iterator<Shape> iterator() {
		return new Iterator<Shape>() {
			private int index = 0;
			
			public boolean hasNext() {
				return index < ile;
			}
			public Shape next() { 
			index++;
			return shapes[rand.nextInt(3)];
			}
			public void remove() { // Not implemented
				throw new UnsupportedOperationException();
			}
		};
	} 
}
public class Zad11_31 {
	public static void main(String[] args) {
		for(Shape o : new Rsg(30)){
			o.draw(); 
		}
	}
}