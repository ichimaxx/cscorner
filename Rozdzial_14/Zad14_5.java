import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 5: (3) Implement a rotate(Shape) method in Shapes.java, such that it
checks to see if it is rotating a Circle (and, if so, doesn’t perform the operation).  
*/

abstract class Shapex {
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex extends Shapex {
	public String toString() { 
		return "Circlex"; 
	}
}
class Squarex extends Shapex {
	public String toString() {
		return "Squarex"; 
	}
}
class Trianglex extends Shapex {
	public String toString() { 
		return "Trianglex"; 
	}
}
class Rhomboid extends Shapex {
	public String toString() { 
		return "Rhomboid"; 
	}
}
public class Zad14_5 {
	public static void rotate(Shapex k) {  // metoda rotate(shape) w Shapes.java 
		if (k instanceof Circlex){ // jesli k czyli klasa z array to Circlex wtedy wywoluje ze sie nie obraca, jesli inne to leci dalej. czyli znowu wykorzystujemy metodę RTTI która sprawdza typ obiektu, juz podczas uzycia programu
				println("Circle się nie obraca");
		} else {
			System.out.println(k + ".rotate()"); 
		}
	}
	public static void main(String[] args) {
		List<Shapex> shapeList = Arrays.asList(new Circlex(), new Squarex(), new Trianglex(), new Rhomboid());
		for(Shapex shape : shapeList){
			shape.draw();	
			rotate(shape);
		}
	}	
}

