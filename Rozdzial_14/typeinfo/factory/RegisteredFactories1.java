//: typeinfo/RegisteredFactories.java
// Registering Class Factories in the base class.
package Rozdzial_14.typeinfo.factory;
import java.util.*;
import java.lang.reflect.*; // potrzebne do odpalenia getDeclaredConstructor().newInstance()
import static myutils.Skrocenie_Print.*;

/*Exercise 14: (4) A constructor is a kind of factory method. Modify
RegisteredFactories.java so that instead of using an explicit factory, the class object is
stored in the List, and newlnstance( ) is used to create each object. */

// Zadanie polega na wykluczeniu interfejsu Factory z RegisteredFactories.java, tak zeby klasy były przechowywane w liscie i uzywane bylo do tego newInstance() do tworzenia obiektów
class Part1 {
	Part1() {}
	public String toString() {
		return getClass().getSimpleName();
	}
	static List<Class<? extends Part1>> partFactories1 = new ArrayList<Class<? extends Part1>>();	// dodano ograniczenie generyczne(<Class<? extends Part1>) dla Part1 bo wtedy pilnuje zeby nie wrzucic np String.class, ale odpali bez tego
	static {
		partFactories1.add(FuelFilter1.class);
		partFactories1.add(AirFilter1.class);
		partFactories1.add(CabinAirFilter1.class);
		partFactories1.add(OilFilter1.class);
		partFactories1.add(FanBelt1.class);
		partFactories1.add(PowerSteeringBelt1.class);
		partFactories1.add(GeneratorBelt1.class);
	}
	private static Random rand = new Random();
	public static Part1 createRandom() {
		Part1 obj = null;
		int n = rand.nextInt(partFactories1.size());
		try {
			// Requires default constructor:
			obj = partFactories1.get(n).getDeclaredConstructor().newInstance();  // uzywamy nowej metody, bo newInstance() jest przestarzala
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
		return obj;
	}
}	

class Filter1 extends Part1 {
	Filter1() {} // wyrzucone wszystkie Factory, bo taki byl cel cwiczenia, dodane zostaly default constructors potrzebne do dzialania getDeclaredConstructor().newInstance();
}

class FuelFilter1 extends Filter1 {
	FuelFilter1() {}
}

class AirFilter1 extends Filter1 {
	AirFilter1() {}
}	

class CabinAirFilter1 extends Filter1 {
	CabinAirFilter1() {}
}

class OilFilter1 extends Filter1 {
	OilFilter1() {}
}	

class Belt1 extends Part1 {
	Belt1() {}
}

class FanBelt1 extends Belt1 {
	FanBelt1() {}
}

class GeneratorBelt1 extends Belt1 {
	GeneratorBelt1() {}
}	

class PowerSteeringBelt1 extends Belt1 {
	PowerSteeringBelt1() {}
}	

public class RegisteredFactories1 {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++)
			System.out.println(Part1.createRandom());
	}
} 