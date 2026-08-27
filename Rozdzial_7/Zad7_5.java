import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (1) Create two classes, A and B, with default constructors (empty argument
lists) that announce themselves. Inherit a new class called C from A, and create a member of
class B inside C. Do not create a constructor for C. Create an object of class C and observe
the results.
*/
class A  {
	A() {
		println("Rozdzial_7.A()");
	}
}
class B {
	B() {
		println("Rozdzial_7.B()");
	}
}
public class Zad7_5 extends A {
	B x = new B();
	
	public Zad7_5() {
		println("Rozdzial_7.Zad7_5()");
	}

	public static void main(String[] args){
	new Zad7_5();
	}
}