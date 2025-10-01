import static myutils.Skrocenie_Print.*;
class A  {
	A() {
		println("A()");
	}
}
class B {
	B() {
		println("B()");
	}
}
public class Zad7_5 extends A {
	B x = new B();
	
	public Zad7_5() {
		println("Zad7_5()");
	}

	public static void main(String[] args){
	new Zad7_5();
	}
}