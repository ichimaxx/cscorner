import static myutils.Skrocenie_Print.print;
/*
Exercise 13: (1) Verify the statements in the previous paragraph.
*/
class Cup {
	Cup(int marker) {
print("Rozdzial_5.Cup(" + marker + ")");}
void f(int marker) {
print("f(" + marker+ ")");}}
class Cups { 
static Cup cup1;
static Cup cup2;
static {
	cup1 = new Cup(1);
	cup2 = new Cup(2);
}
Cups() {
	print("Rozdzial_5.Cups()");
}}
public class Zad5_13 {
	public static void main(String[] args){
	print("Wewnatrz main()");
	//Rozdzial_5.Cups.cup1.f(99); // JAK TO JEST ODKOMENTOWANE TO INICJALIZUEJ TEZ PRZY OKAZJI POLA CUP1 I CUP2
	}
//static Rozdzial_5.Cups cups1 = new Rozdzial_5.Cups();
//static Rozdzial_5.Cups cups2 = new Rozdzial_5.Cups(); //WYKOMENTOWANIE OBU RZECZY NIE INICJALIZUJE WGL PÓL STATIC CUP1 I CUP2
}