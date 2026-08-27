import static myutils.Skrocenie_Print.print;

/*
Exercise 3: (1) Create a class containing a float and use it to demonstrate aliasing
during method calls.
*/
class iksde{
float f;}
public class Zad3_3 {
	static void f(iksde y){
		y.f = 2.0f;
	}
	public static void main (String[] args){
		iksde x = new iksde();
		x.f  = 3.5f;
		print ("1: x.f =" + x.f);
		f(x);
		print ("2: x.f =" + x.f);
}}