import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (3) Create a class, then make an initialized array of objects of your class. Fill
a List from your array. Create a subset of your List by using subList( ), then remove this
subset from your List.
*/
class Pomiary {
    private String name;

    Pomiary(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
} // trzeba zrobic klase zewnetrzna najpierw, ktora bedize wpisywala w liste

public class Zad11_7 {
	
	static Pomiary[] ary = {
		new Pomiary ("Power"),
		new Pomiary ("Amp"),
		new Pomiary ("Watt"),
		new Pomiary ("Volt")
	}; // tworzymy tablice w srodku klasy glownej
	
	public static void main(String[] args) {
		List<Pomiary> tablic1 = new ArrayList<>(Arrays.asList(ary)); // trzeba dac tablice ArrayList, bo Arrays.asList zwraca tablice zawsze w stalym rozmiarze, mozna zmieniac elementy ale NIE MOŻNA dodawac ani usuwac z niej wiec wywala blad UnsupportedOperationException. Dlatego tą tablicę pakuje się w tablice którą można zmieniać
		println(tablic1);
		List<Pomiary> subek = tablic1.subList(1, 3); // wyciaga sublist z ideksu od 1 do 3 czyli 1-amp 2-watt, a dlatego tak jest, bo w rzeczywistosci druga liczba wpisana liczba (3) jest zakresem czyli toIndex 3 w rzeczywistosci jest brany ostatni element przed 3 czyli 2. Jakbysmy dali 4 to by wtedy wzięło zakres od Amp do Volt w subliste
		println("sublist: " + subek); // pokazuje tylko sublist
		tablic1.removeAll(subek); // wywala subliste z tablicy
		println(tablic1); // zostaje tylko power i volt
	}
}
