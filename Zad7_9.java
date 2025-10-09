import static myutils.Skrocenie_Print.*;

class Root {
	Root() {
		println("root");
	}
	Component1 c1 = new Component1();
	Component2 c2 = new Component2();
	Component3 c3 = new Component3();
}
class Component1 {
	Component1(){
		println("komponent1");
	}
}
class Component2 {
	Component2(){
		println("komponent2");
	}
}
class Component3 {
	Component3(){
		println("komponent3");
	}
}


public class Zad7_9 extends Root {
	Zad7_9(){
		println("Zad7_9");
	}
	Component1 sc1 = new Component1();
	Component2 sc2 = new Component2();
	Component3 sc3 = new Component3();
	
	public static void main(String[] args){
	new Zad7_9();
	}
}