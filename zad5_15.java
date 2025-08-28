import static myutils.Skrocenie_Print.print;

class Zurek {
	String skladnik;
	Zurek(String skladnik) {
		this.skladnik = skladnik;
print("zur(" + skladnik + ")");}}

class Zurekzdodatkiem{

Zurek zurek1;
Zurek zurek2;
String opis;
{
	opis = "Zurki z dodatkami";
zurek1 = new Zurek("z jajkiem");
zurek2 = new Zurek("z kielbasa");
}}
public class Zad5_15 {
	public static void main(String[] args){
			Zurekzdodatkiem z = new Zurekzdodatkiem();
		print(z.opis);
		print (z.zurek1.skladnik);
print (z.zurek2.skladnik);}}
		
