import java.util.*;

/*
Exercise 24: (4) Add Null Objects to RegisteredFactories.java. 
*/


interface Null {}
class Part3 {
	public String toString() {
		return getClass().getSimpleName();
	}
	static List<Factory3<? extends Part3>> partFactories3 = new ArrayList<Factory3<? extends Part3>>();
	static {
		// Collections.addAll() gives an "unchecked generic
		// array creation ... for varargs parameter" warning.
		partFactories3.add(new FuelFilter3.Factory());
		partFactories3.add(new AirFilter3.Factory());
		partFactories3.add(new CabinAirFilter3.Factory());
		partFactories3.add(new OilFilter3.Factory());
		partFactories3.add(new FanBelt3.Factory());
		partFactories3.add(new PowerSteeringBelt3.Factory());
		partFactories3.add(new GeneratorBelt3.Factory());
		partFactories3.add(new NullPart.Factory()); // dodany obiekt null do listy
	}
	private static Random rand = new Random();
	public static Part3 createRandom() {
		int n = rand.nextInt(partFactories3.size());
		return partFactories3.get(n).create();
	}
}
class NullPart extends Part3 implements Null{
	public String toString() { return "NullPart"; } // to co zwraca NULL 
	public static final NullPart NULL = new NullPart(); // obiekt NULL tutaj sie tworzy, to tak zwany SINGLETON, czyli jeden na cały program
	public static class Factory implements Factory3<NullPart> {
		public NullPart create() { return NULL; } // create zwraca obiekt NULL	
	}

}
class Filter3 extends Part3 {}
class FuelFilter3 extends Filter3 {
	public static class Factory implements Factory3<FuelFilter3> {
		public FuelFilter3 create() { return new FuelFilter3(); }
	}
}
class AirFilter3 extends Filter3 {
	public static class Factory implements Factory3<AirFilter3> {
		public AirFilter3 create() { return new AirFilter3(); }
	}
}
class CabinAirFilter3 extends Filter3 {
	public static class Factory implements Factory3<CabinAirFilter3> {
		public CabinAirFilter3 create() { return new CabinAirFilter3(); }
	}
}
class OilFilter3 extends Filter3 {
	public static class Factory implements Factory3<OilFilter3> {
		public OilFilter3 create() { return new OilFilter3(); }
	}
}
class Belt3 extends Part3 {}
class FanBelt3 extends Belt3 {
	public static class Factory implements Factory3<FanBelt3> {
		public FanBelt3 create() { return new FanBelt3(); }
	}
}
class GeneratorBelt3 extends Belt3 {
	public static class Factory implements Factory3<GeneratorBelt3> {
		public GeneratorBelt3 create() {
			return new GeneratorBelt3();
		}
	}
}
class PowerSteeringBelt3 extends Belt3 {
	public static class Factory implements Factory3<PowerSteeringBelt3> {
		public PowerSteeringBelt3 create() {
			return new PowerSteeringBelt3();
		}
	}
}
public class Zad14_24 {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++)
			System.out.println(Part3.createRandom());
	}
}
 

/*
Obiekt null przydaje się czasami gdy potrzebujemy duzo miejsc wypełnić nullami, obiekt null można używać bez if(x == null) ale można sprawdzic za pomoca if(x instanceof Null)
program nie wywali sie tak łatwo na NullPointerException
można go podstawiać tak jak zwykłego null, można uzyć gdy np null ma być obsługiwanym stanem w jakichś bazach danych itp
*/