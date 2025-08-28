import static myutils.Skrocenie_Print.print;
class iksde{
float f;}
public class zad3_3 {
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