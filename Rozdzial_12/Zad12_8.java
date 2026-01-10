import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 8: (1) Write a class with a method that throws an exception of the type created
in Exercise 4. Try compiling it without an exception specification to see what the compiler
says. Add the appropriate exception specification. Try out your class and its exception inside
a try-catch clause. 
*/
public class Zad12_8 {
	public String h;
	public void g(String h) throws SimpleException{
		this.h = h;
		println(h);
		throw new SimpleException("xdd");
	}
	public static void main(String[] args) { 
		Zad12_8 kok = new Zad12_8();
		try {
			kok.g("LOL");
			
		} catch(SimpleException e) {
			println("nie moge wykonac kodu poniewaz kok.g() jest: " + e.getMessage());
			e.storedvoid(); 
		} catch(Exception e) {
			println("nie moge wykonac kodu poniewaz kok.g() jest: " + e.getMessage());
		}
		finally {
			println("koniec wyjatku");
		}
	}
}



/* bez exception specification:


C:\Users\ichim\Desktop\cscorner\Rozdzial_12>javac Zad12_8.java
Zad12_8.java:13: error: unreported exception SimpleException; must be caught or declared to be thrown
                throw new SimpleException("xdd");
                ^
Zad12_8.java:20: error: exception SimpleException is never thrown in body of corresponding try statement
                } catch(SimpleException e) {
                  ^
2 errors

Generalnie to cwiczenie sprawdza co sie stanie jak usuniesz throws z metody ktora moze rzucic checkedexception, inna opcja jest zrobienie w tej metodzie try/catch
niby lepiej wybierac throws jak metoda nie wie jak sensownie naprawic problem i to osoba ktora bedzie odpowiedzialna za pisanie kodu bedize musiala zdecydowac, 
a try/catch kiedy metoda bedzie potrafila zareagowac na jakies problemy typu komunikat, powtorzenie lub cos innego
Checked exception czyli extends Exception to takie wyjatki ktore sa na zewnatrz kodu np brak internetu lub zniknal jakis plik itp
unchecked exception czyli extends RuntimeException to np blad w programkowaniu, null, za duza liczba argumentow(indeksow) w array, dzielenie przez 0
*/


