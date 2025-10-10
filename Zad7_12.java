import static myutils.Skrocenie_Print.*;

class Root {
	Root() {
		println("root");
	}
	void dispose() {
		println("Root dispose()");
		c3.dispose();
		c2.dispose();
		c1.dispose();
	}
	Component1 c1 = new Component1();
	Component2 c2 = new Component2();
	Component3 c3 = new Component3();
}
class Component1 {
	Component1(){
		println("komponent1");
	}
	void dispose() {
		println("komponent1 dispose()");
	}
}

class Component2 {
	Component2(){
		println("komponent2");
	}
	void dispose() {
		println("komponent2 dispose()");
	}		
}
class Component3 {
	Component3(){
		println("komponent3");
	}
	void dispose() {
		println("komponent3 dispose()");
	}
}


public class Zad7_12 extends Root {
	Component1 sc1 = new Component1();
	Component2 sc2 = new Component2();
	Component3 sc3 = new Component3();
	Zad7_12(){
		println("Zad7_12");
	}
	
	public void dispose() {
		println("Zad7_12 dispose()");
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