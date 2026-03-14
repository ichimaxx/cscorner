import static myutils.Skrocenie_Print.*;
import java.util.*; 
import java.lang.reflect.*; 


/*
Exercise 9: (5) Modify the previous exercise so that it uses
Class.getDeclaredFields( ) to also display information about the fields in a class.  
*/

abstract class Shapez {
	String nazwa;
	int obwod;
	void draw() { System.out.println(this + ".draw()"); }
}
class Circlez extends Shapez {
	int promien;
	public String toString() { return "Circle"; }
}
class Squarez extends Shapez {
	int przekatna;
	public String toString() { return "Square"; }
}
class Trianglez extends Shapez {
	int przekatna;
	public String toString() { return "Triangle"; }
}
public class Zad14_9 {
	static void printInfo(Class cc) { 
		print("Class name: " + cc.getName());
	} 
	void getclass(Class cv) {
		if (cv != null){
			Field[] cs = cv.getDeclaredFields(); // robi array ktora zbiera declared fields z danej klasy
			printInfo(cv);
			println("");
			for(Field fields : cs) { // ta petla wypisuje pola z array cs
			println(fields);
			}
			Class ck = cv.getSuperclass();
			println("");
			getclass(ck); // rekurencja po klasach az dojdzie do najwyzszej z hierarchii
		}
	} 
	public static void main(String[] args) {
		Object c = new Circlez(); // 	
		Zad14_9 oz = new Zad14_9(); //
		oz.getclass(c.getClass());
		
	}
}

