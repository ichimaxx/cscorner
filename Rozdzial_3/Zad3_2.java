import static myutils.Skrocenie_Print.print;
/*
Exercise 2: (1) Create a class containing a float and use it to demonstrate aliasing.
 */

class ch {
float f;
}

public class Zad3_2 {
public static void main(String[] args) {
	ch a = new ch();
	ch b = new ch();
	ch c = new ch();
	
	a.f = 30f;
	b.f = 45f;
	c.f = 60f;
	print("BEZ ZMIAN ARGUMENTÓW: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
	a = b;
	print("ZE ZMIANA A na B: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
	
	b.f = 4f;
	print("ZE ZMIANA B NA LICZBE 4: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
}
}