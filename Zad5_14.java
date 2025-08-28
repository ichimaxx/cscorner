import static myutils.Skrocenie_Print.print;

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
