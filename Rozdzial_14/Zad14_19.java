import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*; // potrzebny do uzycia getDeclaredConstructor()


/*
Exercise 19: (4) In ToyTest.java, use reflection to create a Toy object using the nondefault constructor. 
*/


interface HasBatteriess {}
interface Waterproofs {}
interface Shootss {}
class Toys {

	Toys(int i) {}
}
class FancyToys extends Toys implements HasBatteriess, Waterproofs, Shootss {
	FancyToys() { super(1); }
}
public class Zad14_19 {
	static void printInfo(Class cc) {
		println("Class name: " + cc.getName() +
		" is interface? [" + cc.isInterface() + "]");
		println("Simple name: " + cc.getSimpleName());
		println("Canonical name : " + cc.getCanonicalName());
	}
	public static void main(String[] args) {
		Class c = null;
		try {
			c = Class.forName("FancyToys");
		} catch(ClassNotFoundException e) {
			println("Can’t find FancyToys");
			System.exit(1);
		}
		printInfo(c);
		for(Class face : c.getInterfaces())
			printInfo(face);
		Class up = c.getSuperclass();
		Object obj = null;
		Class[] paramtype = {int.class}; // tablica parametrow w razie gdyby np konstruktor miał dwa, mozna bez niej wtedy wystarczy wpisac int.class w getDeclaredConstructor() 
		try {
			obj = up.getDeclaredConstructor(paramtype).newInstance(100); // wystarczy zmienic ta linijke do zadania, trzeba dodać getDeclaredConstructor(parametr).newInstance(liczba) gdy nie podamy liczby w newinstance to program się wywali, a samo Class.newInstance() zbiera domyslny konstruktor a nie z argumentamki
		} catch(NoSuchMethodException e) {
			println("No such constructor exists: " + e);
			System.exit(1); 
		} catch(InvocationTargetException e) {
			println("Cannot invoke: " + e);
			System.exit(1);
		} catch(InstantiationException e) {
			println("Cannot instantiate: " + e);
			System.exit(1);
		} catch(IllegalAccessException e) {
			println("Cannot access: " + e);
			System.exit(1);
		} 
		printInfo(obj.getClass());
	}
}