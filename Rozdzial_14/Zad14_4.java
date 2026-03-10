import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 4: (2) Modify the previous exercise so that it uses instanceof to check the type
before performing the downcast.  
*/

abstract class Shapex {
	Shapex() {}
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex extends Shapex {
	Circlex() {}
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
public class Zad14_4 {
	public static void main(String[] args) {
		List<Shapex> shapeList = Arrays.asList(new Circlex(), new Squarex(), new Trianglex(), new Rhomboid());
		for(Shapex shape : shapeList)
			shape.draw();
		Rhomboid romb = new Rhomboid();
		Shapex shape = romb; // upcasting
		Rhomboid rs = (Rhomboid) shape; // downcasting do rombu
		rs.draw(); // draw
		if (shape instanceof Circlex cl) { // instanceof(RTTI) sprawdza czy w tym momencie shape czyli Rhomboid() jest instanceof Circlex
			cl.draw();	
		} else {
			println("błędny downcasting");
		}	
	}
}