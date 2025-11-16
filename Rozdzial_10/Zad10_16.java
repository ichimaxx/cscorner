import static myutils.Skrocenie_Print.*;

interface Cycle {
	void riding();
}
interface CycleFactory {
	Cycle getFactory();
}
class Unicycle1 implements Cycle {
	Unicycle1() {} // Package access
	public void riding() {
		println("Unicycle speed is 10km/h");
	}
	public static CycleFactory factory = new CycleFactory() {
		public Cycle getFactory() {
			return new Unicycle1();
		}
	};
}
class Bicycle1 implements Cycle {
	Bicycle1() {} // Package access
	public void riding() {
		println("Bicycle speed is 50km/h");
	}
	public static CycleFactory factory = new CycleFactory() {
		public Cycle getFactory() {
			return new Bicycle1();
		}
	};
}

class Tricycle1 implements Cycle {
	Tricycle1() {} // Package access
	public void riding() {
		println("Tricycle speed is 15km/h");
	}
	public static CycleFactory factory = new CycleFactory() {
		public Cycle getFactory() {
			return new Tricycle1();
		}
	};
}

public class Zad10_16 {
	public static void serviceConsumer(CycleFactory fact) {
		Cycle s = fact.getFactory(); // obiekt tworzony przez fabryke
		s.riding();
	}
	public static void main(String[] args) {
		serviceConsumer(Unicycle1.factory);
		// Implementations are completely interchangeable:
		serviceConsumer(Bicycle1.factory);
		serviceConsumer(Tricycle1.factory);	
	}
}