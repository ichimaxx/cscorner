import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (2) Create a simple class. Inside a second class, define a reference to an object
of the first class. Use lazy initialization to instantiate this object.
*/
class Klasanazew{
	class Klasawklasie{
	    {println("Klasawklasie");
}}
	Klasawklasie getKlasawklasie() {
	return new Klasawklasie();
	}}



	
public class Zad7_1{
	public static void main(String[] args) {
	Klasanazew z = new Klasanazew();
	Klasanazew.Klasawklasie o = z.getKlasawklasie();}
}