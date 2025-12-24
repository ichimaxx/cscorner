import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 22: (5) Modify the previous exercise so that it uses a class containing a String
and a count field to store each different word, and a Set of these objects to maintain the list
of words.*/


//program generalnie zlicza poszxczegolne String z programu Zad11_20.java, liczy sobie ile razy pojawilo sie slowo a pozniej przerzuca to w ArrayList ktory jest posortowany metoda sort()


public class Zad11_22 {
	static class Zliczak {
		// kazdy obiekt w zliczaku reprezentuje jedno slowo i informacje ile razy sie pojawil w pliku
		private String slowo; // to jest jakby klucz z mapy
		private Integer liczba;	// to jest ilosc  wystapien czyli wartosc w mapie
		//te dwie metody po prostu sciagaja slowa do sortowania i wypisania
		public String getSlowo() {
			return slowo;
		}
		public Integer getLiczba() {
			return liczba;
		}
		//zwieksza licznik slow jak slowo juz jest w kolekcji
		public void dodawanie() { 
			liczba++; 
		}
		// na start od razu kazde nowe slowo dostanie licznik 1
		Zliczak(String slowo) { 
			this.slowo = slowo.toLowerCase();
			this.liczba = 1;
		}
		// przez to hashset sprawdza czy to ten sam element, chcemy aby ten sam element oznaczal to samo slowo wiec porownojemy TYLKO pole slowo
		@Override
		public boolean equals(Object k) {
			if (this == k) return true;
			if (!(k instanceof Zliczak)) return false;
			Zliczak inne = (Zliczak) k;
			return slowo.equals(inne.slowo);
		}
		@Override
		public int hashCode() {
			return slowo.hashCode();
		}
		// tostring zmienia gibberish (adres obiektu) na stringa
		public String toString() {
			return slowo + "=" + liczba;
		}
	}
	
	public static void main(String[] args) {
		Set<Zliczak> setson = new HashSet<>();
		for(String w : new TextFile("Zad11_20.java", "\\W+")) {
			if(w.isEmpty()) continue;
			// tworzymy probke dla aktualnego slowa
			Zliczak probka = new Zliczak(w);
			Zliczak znaleziony = null;
			// poszukiwania czy obiekt juz istnieje w secie
			for(Zliczak z : setson) {
				if(z.equals(probka)) {
					znaleziony = z;
					break;
				}
			}
			// jak znalezlismy to zwiekszamy licznik jak nie to dodajemy nowy obiekt do seta
			if(znaleziony != null) znaleziony.dodawanie();
			else setson.add(probka);
		}
		// wypis set taki bez kolejnosci  bo hashset tego nie robi ;///
		System.out.println("SET NIEPOSORTOWANy: " + setson);
		ArrayList<Zliczak> ok = new ArrayList<>(setson);
		ok.sort(Comparator.comparing(Zliczak::getSlowo, String.CASE_INSENSITIVE_ORDER));
		println("");
		// posorotowane z set alfabetytcznie
		System.out.println("ARRAYLIST POSORTOWANE SLOWA Z Setu W KOLEJNOSCI ALFABETYCZNEJ: " + ok);
		println("");
		// rozpisany wynik linijka po linijce
		println("Wynik alfabetycznie sciagniety z ArrayList: ");
		for(Zliczak z : ok) {
			println(z.getSlowo() + " = " + z.getLiczba());
		}
		
	} 
}