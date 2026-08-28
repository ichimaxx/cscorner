import static myutils.Skrocenie_Print.*;
/*
Exercise 16: (1) Modify the solution to Exercise 18 from the Interfaces chapter to use
anonymous inner classes.
*/
interface Cycle_4 {
	void riding();
}
interface CycleFactory_2 {
	Cycle_4 getFactory();
}
class Unicycle1 implements Cycle_4 {
	Unicycle1() {} // Package access
	public void riding() {
		println("Unicycle speed is 10km/h");
	}
	public static CycleFactory_2 factory = new CycleFactory_2() {
		public Cycle_4 getFactory() {
			return new Unicycle1();
		}
	};
}
class Bicycle1 implements Cycle_4 {
	Bicycle1() {} // Package access
	public void riding() {
		println("Bicycle speed is 50km/h");
	}
	public static CycleFactory_2 factory = new CycleFactory_2() {
		public Cycle_4 getFactory() {
			return new Bicycle1();
		}
	};
}

class Tricycle1 implements Cycle_4 {
	Tricycle1() {} // Package access
	public void riding() {
		println("Tricycle speed is 15km/h");
	}
	public static CycleFactory_2 factory = new CycleFactory_2() {
		public Cycle_4 getFactory() {
			return new Tricycle1();
		}
	};
}

public class Zad10_16 {
	public static void serviceConsumer(CycleFactory_2 fact) {
		Cycle_4 s = fact.getFactory(); // obiekt tworzony przez fabryke
		s.riding();
	}
	public static void main(String[] args) {
		serviceConsumer(Unicycle1.factory);
		// Implementations are completely interchangeable:
		serviceConsumer(Bicycle1.factory);
		serviceConsumer(Tricycle1.factory);	
	}
}