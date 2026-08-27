import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 5: (3) Implement a rotate(Shape) method in Shapes.java, such that it
checks to see if it is rotating a Circle (and, if so, doesn’t perform the operation).  
*/

abstract class Shapex_2 {
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex_2 extends Shapex_2 {
	public String toString() { 
		return "Circlex"; 
	}
}
class Squarex_2 extends Shapex_2 {
	public String toString() {
		return "Squarex"; 
	}
}
class Trianglex_2 extends Shapex_2 {
	public String toString() { 
		return "Trianglex"; 
	}
}
class Rhomboid_2 extends Shapex_2 {
	public String toString() { 
		return "Rhomboid"; 
	}
}
public class Zad14_5 {
	public static void rotate(Shapex_2 k) {  // metoda rotate(shape) w Shapes.java
		if (k instanceof Circlex_2){ // jesli k czyli klasa z array to Circlex wtedy wywoluje ze sie nie obraca, jesli inne to leci dalej. czyli znowu wykorzystujemy metodę RTTI która sprawdza typ obiektu, juz podczas uzycia programu
				println("Circle się nie obraca");
		} else {
			System.out.println(k + ".rotate()"); 
		}
	}
	public static void main(String[] args) {
		List<Shapex_2> shapeList = Arrays.asList(new Circlex_2(), new Squarex_2(), new Trianglex_2(), new Rhomboid_2());
		for(Shapex_2 shape : shapeList){
			shape.draw();	
			rotate(shape);
		}
	}	
}

