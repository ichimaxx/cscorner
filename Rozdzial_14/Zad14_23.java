import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*;
/*
Exercise 23: (3) Inside invoke( ) in SimpleDynamicProxy.java, try to print the
proxy argument and explain what happens. 
*/


class DynamicProxyHandler_2 implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler_2(Object proxied) {
		this.proxied = proxied;
	}
	public Object
	invoke(Object proxy, Method method, Object[] args)
	throws Throwable {
		System.out.println("**** proxy: " + proxy + // zamiast proxy.getClass() uzywamy samego argumentu (obiektu proxy)
		", method: " + method + ", args: " + args);
		if(args != null)
			for(Object arg : args)
				System.out.println(" " + arg);
		return method.invoke(proxied, args);
	}
}
class Zad14_23 {
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
			new DynamicProxyHandler_2(real));
		consumer(proxy);
	}
} 

/* 
Ze wzgledu na uzycie samego argumentu proxy w invoke() zamiast proxy.getClass(), który bierze klase obiektu, dostajemy rekurencje ponieważ println(proxy) wywoluje toString() na obiekcie proxy,
a wywoływania metody na proxy sa przekierowywane ponownie do invoke() przez co invoke wywołuje samo siebie cały czas czyli StackOverflowError
W przypadku uzycia proxy.getClass() pobieramy klase obiektu, a nie wywołujemy metody interfejsu proxy w taki sposób, który wraca z powrotem do invoke()
*/