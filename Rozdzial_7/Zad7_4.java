import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (2) Prove that the base-class constructors are (a) always called and (b) called
before derived-class constructors.
*/
class Art1 {
	Art1() {
		println("Rozdzial_7.Art constructor");
	} 
} 
class Drawing1 extends Art1 {
	//usunalem konstruktor w klasie Rozdzial_7.Drawing zeby pokazac ze tak czy inaczej base constructor jest called
} 
public class Zad7_4 extends Drawing1 {
	//dodany konstruktor w klasie Rozdzial_7.Zad7_4 udowadnia ze najpierw priorytetem w wlaczaniu i tak jest klasa Rozdzial_7.Art z której jest deriving dla klasy Rozdzial_7.Zad7_4
	public Zad7_4() {
		println("Rozdzial_7.Zad7_4 constructor");
	}
	public static void main(String[] args) { 
		Zad7_4 x = new Zad7_4(); 
	} 
}