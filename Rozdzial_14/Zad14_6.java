import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 6: (4) Modify Shapes.java so that it can "highlight" (set a flag in) all shapes of
a particular type. The toString( ) method for each derived Shape should indicate whether
that Shape is "highlighted."  
*/

abstract class Shapex {
	boolean highlighted;
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex extends Shapex {
	public String toString() { 
		if (highlighted == true){  // if do flagi w kazdej klasie
			return "Circlex.highlighted";	
		} else {
			return "Circlex";
		} 
	}
}
class Squarex extends Shapex {
	public String toString() {
		if (highlighted == true){ 
			return "Squarex.highlighted";	
		} else {
			return "Squarex";
		} 
	}
}
class Trianglex extends Shapex {
	public String toString() { 
		if (highlighted == true){ 
			return "Trianglex.highlighted";	
		} else {
			return "Trianglex";
		} 
	}
}
class Rhomboid extends Shapex {
	public String toString() { 
		if (highlighted == true){ 
			return "Rhomboid.highlighted";	
		} else {
			return "Rhomboid";
		} 
	}
}
public class Zad14_6 {
	static void highlight(Shapex k) {
		if (k instanceof Circlex) { // wybralem Circlex do highlightowania 
			k.highlighted = true;
		}
	}
	public static void main(String[] args) {
		List<Shapex> shapeList = Arrays.asList(new Circlex(), new Squarex(), new Trianglex(), new Rhomboid());
		for(Shapex shape : shapeList){
			highlight(shape); // jesli któras z figur z listy to Circlex, to zrobi jej highlight
			shape.draw();
		}	
	}
}

