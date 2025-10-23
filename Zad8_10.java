import static myutils.Skrocenie_Print.*;

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