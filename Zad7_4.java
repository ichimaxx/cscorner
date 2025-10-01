import static myutils.Skrocenie_Print.*;

class Art { 
	Art() { 
		println("Art constructor"); 
	} 
} 
class Drawing extends Art { 
	//usunalem konstruktor w klasie Drawing zeby pokazac ze tak czy inaczej base constructor jest called
} 
public class Zad7_4 extends Drawing {  
	//dodany konstruktor w klasie Zad7_4 udowadnia ze najpierw priorytetem w wlaczaniu i tak jest klasa Art z której jest deriving dla klasy Zad7_4
	public Zad7_4() {
		println("Zad7_4 constructor");
	}
	public static void main(String[] args) { 
		Zad7_4 x = new Zad7_4(); 
	} 
}