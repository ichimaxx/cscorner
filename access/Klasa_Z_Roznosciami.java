package access;

public class Klasa_Z_Roznosciami{
	public int a;
	private int b;
	protected int c;
	int d;
	public void f1(){System.out.println("f1 byl public");}
	private void f2(){System.out.println("f2 byl private");}
	protected void f3(){System.out.println("f3 byl protected");}
	void f4(){System.out.println("f4 byl w paczce bez zadnego specifier");}

	public static void main(String[] args){
		Klasa_Z_Roznosciami test = new Klasa_Z_Roznosciami();
		test.a = 1;
		test.b = 2;
		test.c = 3;
		test.d = 4;
		test.f1();
		test.f2();
		test.f3();
		test.f4();
		
	System.out.printf("%d%s, %d%s, %d%s, %d%s%n",test.a, " = a", test.b, " = b", test.c, " = c", test.d, " = d");
}}