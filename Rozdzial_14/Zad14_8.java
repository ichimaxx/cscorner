import static myutils.Skrocenie_Print.*;
import java.util.*; 

/*
Exercise 8: (5) Write a method that takes an object and recursively prints all the classes
in that object’s hierarchy. 
*/

abstract class Shape {
	void draw() { System.out.println(this + ".draw()"); }
}
class Circle extends Shape {
	public String toString() { return "Circle"; }
}
class Square extends Shape {
	public String toString() { return "Square"; }
}
class Triangle extends Shape {
	public String toString() { return "Triangle"; }
}
public class Zad14_8{
	static void printInfo(Class cc) { 
		print("Class name: " + cc.getName());
	} 
	void getclass(Class cv) {
		if (cv != null){
			printInfo(cv);
			Class ck = cv.getSuperclass();
			println("");
			getclass(ck); // rekurencja po klasach az dojdzie do najwyzszej z hierarchii
		}
	} 
	public static void main(String[] args) {
		Object c = new Circle(); // tworzysz obiekt bo tak chce cwiczenie		
		Zad14_8 oz = new Zad14_8(); // obiekt zadania14_8
		oz.getclass(c.getClass());
		
	}
}

