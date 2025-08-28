import java.util.*;
import static myutils.Skrocenie_Print.print;

class Cup {
	Cup(int marker) {
print("Cup(" + marker + ")");}
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
	print("Cups()");
}}
public class Zad5_13 {
	public static void main(String[] args){
	print("Wewnatrz main()");
	//Cups.cup1.f(99); // JAK TO JEST ODKOMENTOWANE TO INICJALIZUEJ TEZ PRZY OKAZJI POLA CUP1 I CUP2
	}
//static Cups cups1 = new Cups();
//static Cups cups2 = new Cups(); //WYKOMENTOWANIE OBU RZECZY NIE INICJALIZUJE WGL PÓL STATIC CUP1 I CUP2
}