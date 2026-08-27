import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 4: (2) Modify the previous exercise so that it uses instanceof to check the type
before performing the downcast.  
*/

abstract class Shapex_1 {
	Shapex_1() {}
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex_1 extends Shapex_1 {
	Circlex_1() {}
	public String toString() { 
		return "Circlex"; 
	}
}
class Squarex_1 extends Shapex_1 {
	public String toString() {
		return "Squarex"; 
	}
}
class Trianglex_1 extends Shapex_1 {
	public String toString() { 
		return "Trianglex"; 
	}
}
class Rhomboid_1 extends Shapex_1 {
	public String toString() { 
		return "Rhomboid"; 
	}
}
public class Zad14_4 {
	public static void main(String[] args) {
		List<Shapex_1> shapeList = Arrays.asList(new Circlex_1(), new Squarex_1(), new Trianglex_1(), new Rhomboid_1());
		for(Shapex_1 shape : shapeList)
			shape.draw();
		Rhomboid_1 romb = new Rhomboid_1();
		Shapex_1 shape = romb; // upcasting
		Rhomboid_1 rs = (Rhomboid_1) shape; // downcasting do rombu
		rs.draw(); // draw
		if (shape instanceof Circlex_1 cl) { // instanceof(RTTI) sprawdza czy w tym momencie shape czyli Rhomboid() jest instanceof Circlex
			cl.draw();	
		} else {
			println("błędny downcasting");
		}	
	}
}