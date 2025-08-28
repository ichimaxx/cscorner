package access;
import access.Klasa_Z_Roznosciami;

public class Zad6_5_wsrodku{
	
	public static void main(String[] args){
		Klasa_Z_Roznosciami test = new Klasa_Z_Roznosciami();
		test.a = 1;
		//! test.b = 2; bo jest private i mozna tylko w tej samej klasie odpalic
		test.c = 3;
		test.d = 4; 
		test.f1();
		// test.f2(); same story jak tamta 
		test.f3(); 
		test.f4();
		
System.out.printf("%d%s, %d%s, %d%s%n",test.a, " = a", test.c, " = c", test.d, " = d");
}}