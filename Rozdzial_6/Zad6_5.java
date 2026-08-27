import access.Klasa_Z_Roznosciami;
/*
Exercise 5: (2) Create a class with public, private, protected, and package-access
fields and method members. Create an object of this class and see what kind of compiler
messages you get when you try to access all the class members. Be aware that classes in the
same directory are part of the “default” package.
*/
public class Zad6_5 extends Klasa_Z_Roznosciami{
	
	public static void main(String[] args){
		Klasa_Z_Roznosciami test = new Klasa_Z_Roznosciami();
		test.a = 1;
		//! test.b = 2; - NIE ODPALI BO JEST PRIVATE W PACZCE
		//! test.c = 3; - NIE ODPALI BO JEST PROTECTED W PACZCE
		//! test.d = 4; - NIE ODPALI BO NIE JEST PUBLIC A JA JESTEM PO ZA PACZKA
		test.f1();
		//! test.f2(); - NIE ODPALI BO JEST PRIVATE W PACZCE
		//! test.f3(); - NIE ODPALI BO JEST PROTECTED W PACZCE
		//! test.f4(); - NIE ODPALI BO NIE JEST PUBLIC A JA JESTEM PO ZA PACZKA
		
//! System.out.printf("%d%s, %d%s, %d%s, %d%s%n",test.a, " = a", test.b, " = b", test.c, " = c", test.d, " = d");  - NO NIE ODPALI DLATEGO CO WCZENSIEJ
}}