// Testing class Class.
import static net.mindview.util.Print.*;
import java.lang.reflect.*; // potrzebny zeby uzyc nowej metody newInstance(), ta z ksiazki jest przestarzala

/*Exercise 1: (1) In ToyTest.java, comment out Toy’s default constructor and explain
what happens. */

interface HasBatteries {}

interface Waterproof {}

interface Shoots {}

class Toy {
	// Comment out the following default constructor
	// to see NoSuchMethodError from (*1*)
	//Toy() {}
	Toy(int i) {}
}
class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
	FancyToy() { 
		super(1); 
	}
}
public class Zad14_1 {
	static void printInfo(Class cc) {
		print("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
		print("Simple name: " + cc.getSimpleName());
		print("Canonical name : " + cc.getCanonicalName());
	}
	public static void main(String[] args) {
		Class c = null;
		try {
			c = Class.forName("FancyToy"); // ze wzgledu na zbieranie wszystkich zadan w jednym folderze zmienilem lokacje pliku i usunalem package warto podkreslic ze jesli np szukalibysmy metod w package trzeba by bylo lokacje tego package zawrzec np tak jak w ksiazce: "typeinfo.toys.FancyToy"
		} catch(ClassNotFoundException e) {
			print("Can’t find FancyToy");
			System.exit(1);
		}
		printInfo(c);
		for(Class face : c.getInterfaces())
			printInfo(face);
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			// Requires default constructor:
			obj = up.getDeclaredConstructor().newInstance(); // metoda z ksiazki jest przestarzala(deprecated), teraz uzywa sie do tego getDeclaredConstructor(), to dziala identycznie na nowej javie, tamta wersja dziala tylko dla publicznego kosntruktora bez argumentów, slabo obsługiwała wyjątki i miala problemy z dostępem
		} catch(NoSuchMethodException e) {
			print("No such constructor exists: " + e);
			System.exit(1); // po zakomentowaniu default konstruktora lapie wyjątek NoSuchMethodException, bo nie ma takiego konstruktora, poniewaz obj = up.getDeclaredConstructor().newInstance(); szuka default(bezargumentowego) constructor z klasy którą próbujemy stworzyć, w tym przypadku to Toy (superclass FancyToy)
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