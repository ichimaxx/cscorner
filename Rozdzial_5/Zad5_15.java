import static myutils.Skrocenie_Print.print;
/*
Exercise 15: (1) Create a class with a String that is initialized using instance
initialization.
*/
class Zurek1 {
	String skladnik;
	Zurek1(String skladnik) {
		this.skladnik = skladnik;
print("zur(" + skladnik + ")");}}

class Zurekzdodatkiem{

	Zurek1 zurek1;
	Zurek1 zurek2;
String opis;
{
	opis = "Zurki z dodatkami";
zurek1 = new Zurek1("z jajkiem");
zurek2 = new Zurek1("z kielbasa");
}}
public class Zad5_15 {
	public static void main(String[] args){
			Zurekzdodatkiem z = new Zurekzdodatkiem();
		print(z.opis);
		print (z.zurek1.skladnik);
print (z.zurek2.skladnik);}}
		
