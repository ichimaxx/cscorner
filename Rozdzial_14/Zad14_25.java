import typeinfo.twierdza.*; // package zrobiony pod zadanie 25
import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*;

/*Exercise 25: (2) Create a class containing private, protected and package-access
methods. Write code to access these methods from outside of the class’s package. */

public class Zad14_25 {
	public static void main(String[] args) throws Exception {
		Undisputed_Tower oz = new Undisputed_Tower(); 
		String[] a = {"one", "two", "three", "four", "five", "six"}; // tablica wymieniajaca metody
		for (String g : a) {
			Method f = oz.getClass().getDeclaredMethod(g); // deklaracja metody
			f.setAccessible(true); // to jest metoda z klasy AccessibleObject pakietu java.lang.reflect która omija normalne ograniczenia dostepu, daje możliwość wywołania metodom takim jak protected,package lub private, działa to także z polami, pola można dodatkowo zamieniać, nawet przy private. Jedynie w przypadku pola final jest to utrudnione i nie da się zamienic go w tak łatwy sposób
			f.invoke(oz); // metoda klasy Method z pakietu java.lang.reflect , która uruchamia znaleziona metode na obiekcie oz w tym przypadku na obiekcie Undisputed_Tower
		}
	}
}