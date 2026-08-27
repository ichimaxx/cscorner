import static myutils.Skrocenie_Print.*;
/*
Exercise 12: (3) Add a proper hierarchy of dispose( ) methods to all the classes in
Exercise 9.
*/
class Root2 {
	Root2() {
		println("root");
	}
	void dispose() {
		println("Rozdzial_7.Root dispose()");
		c3.dispose();
		c2.dispose();
		c1.dispose();
	}
	Component1_1 c1 = new Component1_1();
	Component2_1 c2 = new Component2_1();
	Component3_1 c3 = new Component3_1();
}
class Component1_1 {
	Component1_1(){
		println("komponent1");
	}
	void dispose() {
		println("komponent1 dispose()");
	}
}

class Component2_1 {
	Component2_1(){
		println("komponent2");
	}
	void dispose() {
		println("komponent2 dispose()");
	}		
}
class Component3_1 {
	Component3_1(){
		println("komponent3");
	}
	void dispose() {
		println("komponent3 dispose()");
	}
}


public class Zad7_12 extends Root2 {
	Component1_1 sc1 = new Component1_1();
	Component2_1 sc2 = new Component2_1();
	Component3_1 sc3 = new Component3_1();
	Zad7_12(){
		println("Rozdzial_7.Zad7_12");
	}
	
	public void dispose() {
		println("Rozdzial_7.Zad7_12 dispose()");
		sc3.dispose();
		sc2.dispose();
		sc1.dispose();
		super.dispose();
	}
	
	public static void main(String[] args){
		Zad7_12 f = new Zad7_12();
		try {
		}
		finally {
			f.dispose();
		}
	}
}