import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*;
/*
Exercise 22: (3) Modify SimpleDynamicProxy.java so that it measures method-call
times.
*/


class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}
	public Object
	invoke(Object proxy, Method method, Object[] args)
	throws Throwable {
		long start = System.nanoTime(); // pomiar czasowy metody proxy start
		System.out.println("**** proxy: " + proxy.getClass() +
		", method: " + method + ", args: " + args);
		if(args != null)
			for(Object arg : args)
				System.out.println(" " + arg);
		Object result = method.invoke(proxied, args); // zrobilismy zmienna lokalna
		long end = System.nanoTime(); // pomiar czasowy metody proxy koniec
		println("wykonano metode " + method + " w czasie: " + (end - start) + " nanosekund");
		return result; // zwracamy result dopiero po tym jak zmierzylismy czas
	}
}
class Zad14_22 {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		// Insert a proxy and call again:
		Interface proxy = (Interface)Proxy.newProxyInstance(
			Interface.class.getClassLoader(),
			new Class[]{ Interface.class },
			new DynamicProxyHandler(real));
		consumer(proxy);
	}
} 