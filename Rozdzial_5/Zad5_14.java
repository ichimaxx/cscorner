import static myutils.Skrocenie_Print.print;
/*
Exercise 14: (1) Create a class with a static String field that is initialized at the point of
definition, and another one that is initialized by the static block. Add a static method that
prints both fields and demonstrates that they are both initialized before they are used.

*/

class Zurek {
	static String zurek = "zur"; // miesjce definicji i tu jest napisane jaki string
static String zurson; // deklaracja pola ale bez wartosci, dopiero pozniej wbloku statycznym jest zadeklarowane

static {
	zurson = "zurson"; // zadeklarowanie w bloku statycznym jaka jest nazwa zurku w polu staytycznym
print("Blok statyczny odpalony");}
}
public class Zad5_14 {
	public static void main(String[] args){
		print("W main()");
		print("wMiejscu = " + Zurek.zurek); // w miejscu definicji 
		print("wBloku = " + Zurek.zurson);
}}
