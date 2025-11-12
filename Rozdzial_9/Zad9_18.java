import static myutils.Skrocenie_Print.*;

interface Cycle {
	void riding();
}
interface CycleFactory {
	Cycle getFactory();
}
class Unicycle implements Cycle {
	Unicycle() {} // Package access
	public void riding() {
		println("Unicycle speed is 10km/h");
	}
}
class UnicycleFactory implements CycleFactory {
	public Cycle getFactory() {
		return new Unicycle();
	}
}
class Bicycle implements Cycle {
	Bicycle() {} // Package access
	public void riding() {
		println("Bicycle speed is 50km/h");
	}
}
class BicycleFactory implements CycleFactory {
	public Cycle getFactory() {
		return new Bicycle();
	}
}
class Tricycle implements Cycle {
	Tricycle() {} // Package access
	public void riding() {
		println("Tricycle speed is 15km/h");
	}
}
class TricycleFactory implements CycleFactory {
	public Cycle getFactory() {
		return new Tricycle();
	}
}

public class Zad9_18 {
	public static void serviceConsumer(CycleFactory fact) {
		Cycle s = fact.getFactory(); // obiekt tworzony przez fabryke
		s.riding();
	}
	public static void main(String[] args) {
		serviceConsumer(new UnicycleFactory());
		// Implementations are completely interchangeable:
		serviceConsumer(new BicycleFactory());
		serviceConsumer(new TricycleFactory());	
	}
}