import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*;
import java.util.*;

/*
Exercise 20: (5) Look up the interface for java.lang.Class in the JDK documentation
from http://java.sun.com. Write a program that takes the name of a class as a command-line
argument, then uses the Class methods to dump all the information available for that class.
Test your program with a standard library class and a class you create. 
*/

public class Zad14_20 {
	public static void main (String[] args) {
		Class<?> c = null;
		if (args.length != 1) {
			println("JAK UZYC ZADANIA: java Zad14_20 classname");
			System.exit(1);
		}
		String h = args[0]; // pierwszy argument w command-line ma byc nazwa klasy ktora chcemy sprawdzic
		try {
			c = Class.forName(h); // laduje klase po nazwie podanej w argumencie
		} catch(ClassNotFoundException e) {
			print("Can't find: " + h);
			System.exit(1);
		} 
		// na podstawie klasy wywolanej przez Class.forName(h) wypisujemy informacje o tym typie
		println("Name of class: " + c.getName());
		println("getSimpleName: " + c.getSimpleName());
		println("getCanonicalName: " + c.getCanonicalName());
		println("isInterface: " + c.isInterface());
		println("isEnum: " + c.isEnum());
		println("isArray: " + c.isArray());
		println("isPrimitive: " + c.isPrimitive());
		println("isAnnotation(): " + c.isAnnotation());
		println("getSuperclass: " + c.getSuperclass());
		println("getInterfaces: " + Arrays.toString(c.getInterfaces()));
		println("getFields: " + Arrays.toString(c.getFields()));
		println("getDeclaredFields: " + Arrays.toString(c.getDeclaredFields()));
		println("getMethods: " + Arrays.toString(c.getMethods()));
		println("getDeclaredMethods: " + Arrays.toString(c.getDeclaredMethods()));
		println("getConstructors: " + Arrays.toString(c.getConstructors()));
		println("getDeclaredConstructors: " + Arrays.toString(c.getDeclaredConstructors()));
		println("getPackage: " + c.getPackage());
		println("getClassLoader: " + c.getClassLoader());
		println("getTypeParameters: " + Arrays.toString(c.getTypeParameters()));
		println("getAnnotations: " + Arrays.toString(c.getAnnotations()));
	}
}