import static myutils.Skrocenie_Print.*;
/*
Exercise 9: (2) Create a class called Root that contains an instance of each of the classes
(that you also create) named Component1, Component2, and Component3. Derive a
class Stem from Root that also contains an instance of each “component.” All classes should
have default constructors that print a message about that class.
*/
class Root3 {
	Root3() {
		println("root");
	}
	Component1_2 c1 = new Component1_2();
	Component2_2 c2 = new Component2_2();
	Component3_2 c3 = new Component3_2();
}
class Component1_2 {
	Component1_2(){
		println("komponent1");
	}
}
class Component2_2 {
	Component2_2(){
		println("komponent2");
	}
}
class Component3_2 {
	Component3_2(){
		println("komponent3");
	}
}


public class Zad7_9 extends Root3 {
	Zad7_9(){
		println("Rozdzial_7.Zad7_9");
	}
	Component1_2 sc1 = new Component1_2();
	Component2_2 sc2 = new Component2_2();
	Component3_2 sc3 = new Component3_2();
	
	public static void main(String[] args){
	new Zad7_9();
	}
}