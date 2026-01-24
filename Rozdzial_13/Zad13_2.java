import java.util.*;

/*Exercise 2: (1) Repair InfiniteRecursion.java. */

public class Zad13_2 {
	public String toString() {
		return " InfiniteRecursion address: " + super.toString( ) + "\n";
	}
	public static void main(String[] args) {
		List<Zad13_2> v = new ArrayList<Zad13_2>();
		for(int i = 0; i < 10; i++){
			v.add(new Zad13_2());
		}
			System.out.println(v);
	}
} 

/* super.toString() wywołuje Object.toString() (metodę z klasy bazowej. Czyli zaglebiajac się w to zwraca Stringa w stylu: getClass().getName() + "@" + Integer.toHexString(hashCode()) ),
 więc nie wchodzi znowu w Zad13_2.toString() i dizeki temu nie ma nieskonczonej rekurencji jak w przypadku wczesniejszym, czyli this, ktory woła toString() na obiekcie Zad13_2, a przez to ze woła się to w toString(), robi się pętla
 
 prościej: jak w metodzie toString() odpalasz this (który w rzeczywistosci wywoluje domyślnie toString()) to bedzie wołać tą metodę, więc bedzie się odpalać w kółko ta sama metoda public String toString() {} ...
 
 */