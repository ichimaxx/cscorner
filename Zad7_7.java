import static myutils.Skrocenie_Print.*;
class A {
	protected String c;
		A(String c) {
		this.c = "J";
		print(c);
	}
}
class B {
	protected String d;
		B(String d) {
		this.d = "D";
		print(d);
	}
}
public class Zad7_7 extends A {
	private B x = new B("D");
		public Zad7_7() {
			super("J");
		}

		public static void main(String[] args){
			new Zad7_7();
		}
}