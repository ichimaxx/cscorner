import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 12: (3) Create and populate a List<Integer>. Create a second List<Integer>
of the same size as the first, and use ListIterators to read elements from the first List and
insert them into the second in reverse order. (You may want to explore a number of different
ways to solve this problem.)
*/
public class Zad11_12 {
	public static void main(String[] args) {
		List<Integer> koks = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		List<Integer> koks2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		ListIterator<Integer> it = koks.listIterator(); 
		println("Tablica koks1 : " + koks);
		println("Tablica koks2 : " + koks2 + "\n" );
		println("Tablica koks1 Z ROZPISANYMI ID : ");
		while(it.hasNext()){
			print(it.next() + ", ID:" + it.nextIndex() + " // ");
		}
		println();

		println("teraz odwracamy tablice koks2 zeby miala odwrotnie wartosci \n");
		int index = 0;
		while(it.hasPrevious()){
			koks2.set(index, it.previous()); 
			index++;
		}
		println("tablica odwrocona, ponizej print tablicy z zamienionymi wartosciami: \n");
		println("Tablica koks1 : " + koks);
		println("Tablica koks2 po uzyciu set(itprevious) : " + koks2);
	}
}

	