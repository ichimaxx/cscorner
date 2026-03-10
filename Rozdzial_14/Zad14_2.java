// Testing class Class.
import static net.mindview.util.Print.*;
import java.lang.reflect.*;

/* Exercise 2: (2) Incorporate a new kind of interface into ToyTest.java and verify that it
is detected and displayed properly. */

interface HasBatteries {}

interface Waterproof {}

interface Shoots {}

interface Bulletproof {} // nowy interfejs

class Toy {
	Toy() {}
	Toy(int i) {}
}
class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, Bulletproof {
	FancyToy() { 
		super(1); 
	}
}
public class Zad14_2 {
	static void printInfo(Class cc) {
		print("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
		print("Simple name: " + cc.getSimpleName());
		print("Canonical name : " + cc.getCanonicalName());
	}
	public static void main(String[] args) {
		Class c = null;
		try {
			c = Class.forName("FancyToy");
		} catch(ClassNotFoundException e) {
			print("Can’t find FancyToy");
			System.exit(1);
		}
		printInfo(c);
		for(Class face : c.getInterfaces())
			printInfo(face); // pętla zbiera kazdy zaimplmentowany interface do FancyToy wiec doda tez nowy interface
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			// Requires default constructor:
			obj = up.getDeclaredConstructor().newInstance();
		} catch(NoSuchMethodException e) {
			print("No such constructor exists: " + e);
			System.exit(1); 
		} catch(InvocationTargetException e) {
			print("Cannot invoke: " + e);
			System.exit(1);
		} catch(InstantiationException e) {
			print("Cannot instantiate: " + e);
			System.exit(1);
		} catch(IllegalAccessException e) {
			print("Cannot access: " + e);
			System.exit(1);
		} 
		printInfo(obj.getClass());
	}
} 