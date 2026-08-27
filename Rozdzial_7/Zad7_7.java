import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (1) Modify Exercise 5 so that A and B have constructors with arguments
instead of default constructors. Write a constructor for C and perform all initialization within
C’s constructor.
*/
class A_1 {
	protected String c;
		A_1(String c) {
		this.c = "J";
		print(c);
	}
}
class B_1 {
	protected String d;
	B_1(String d) {
		this.d = "D";
		print(d);
	}
}
public class Zad7_7 extends A_1 {
	private B_1 x = new B_1("D");
		public Zad7_7() {
			super("J");
		}

		public static void main(String[] args){
			new Zad7_7();
		}
}