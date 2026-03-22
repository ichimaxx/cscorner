import static myutils.Skrocenie_Print.*;

/*
Exercise 21: (3) Modify SimpleProxyDemo.java so that it measures method-call
times. 
*/

interface Interface {
	void doSomething();
	void somethingElse(String arg); 
}
class RealObject implements Interface {
	public void doSomething() { 
		println("doSomething"); 
	}
	public void somethingElse(String arg) {
		println("somethingElse " + arg);
	}
}
class SimpleProxy implements Interface {
	private Interface proxied;
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	public void doSomething() {
		println("SimpleProxy doSomething");
		long start = System.nanoTime(); // pomiar czasowy metody proxy start
		proxied.doSomething();
		long end = System.nanoTime(); // pomiar czasowy metody proxy koniec
		println("Czas w jakim wywolywana została metoda doSomething() = " + (end - start) + " nanosekund");
	}
	public void somethingElse(String arg) {
		println("SimpleProxy somethingElse " + arg);
		long start = System.nanoTime(); // pomiar czasowy metody proxy start
		proxied.somethingElse(arg);
		long end = System.nanoTime(); // pomiar czasowy metody proxy koniec
		println("Czas w jakim wywolywana została metoda somethingElse() = " + (end - start) + " nanosekund");
	}
}

class Zad14_21 {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}

// dodano pomiar czasowy przed i po uzyciu metody proxy