import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (1) Modify the previous exercise so that each class only has non-default
constructors.
*/

class Root {
	Component1 c1;
	Component2 c2;
	Component3 c3;
	
	Root(String d) {
		println("Rozdzial_7.Root(" + d + ")");
		c1 = new Component1(d + ":c1");
		c2 = new Component2(d + ":c2");
		c3 = new Component3(d + ":c3");
	}

}
class Component1 {
	protected String a;
	Component1(String a){
		this.a = "komponent1(";
		println("komponent 1(" + a + ")");
	}
}
class Component2 {
	protected String b;
	Component2(String b){
		this.b = "komponent2(";
		println("komponent 2(" + b + ")");
	}
}
class Component3 {
	protected String c;
	Component3(String c){
		this.c = "komponent3";
		println("komponent 3(" + c + ")");
	}
}


public class Zad7_10 extends Root {
	Component1 sc1;
	Component2 sc2;	
	Component3 sc3;
	
	public Zad7_10(String e) {
		super(e);
		println("Rozdzial_7.Zad7_10{" + e + ")");
		sc1 = new Component1(e + ":sc1");
		sc2 = new Component2(e + ":sc2");
		sc3 = new Component3(e + ":sc3");
	}
	
	public static void main(String[] args){
		new Zad7_10("x");
	}
}