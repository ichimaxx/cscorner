import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (3) Create a base class with two methods. In the first method, call the
second method. Inherit a class and override the second method. Create an object of the
derived class, upcast it to the base type, and call the first method. Explain what happens.
*/
class Methods {
	void method1() { 
		method2();
	}
	void method2() {
		println("method2");
	}
}

public class Zad8_10 extends Methods {
	@Override
	void method2() {
		println("method2 inherited");
	}
	public static void main(String[] args) {
		Methods meth = new Zad8_10();
		meth.method1();
	}
}