import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 40: (5) Create a class containing two String objects and make it Comparable
so that the comparison only cares about the first String. Fill an array and an ArrayList with
objects of your class, using the RandomGenerator generator. Demonstrate that sorting
works properly. Now make a Comparator that only cares about the second String, and
demonstrate that sorting works properly. Also perform a binary search using your
Comparator.
*/
public class Zad17_40 implements Comparable<Zad17_40>, Comparator<Zad17_40>{
    // przechowywane są dwa Stringi,
    // jeden - do naturalnego sortowania przez Comparable
    // dwa do alternatywnego sortowania przez Comparator
    String jeden;
    String dwa;
    public Zad17_40(String a, String b) {
        this.jeden = a;
        this.dwa = b;
    }
    //comparable definiuje sposób sortowania obiektów tej klasy
    //w tym zadaniu sortowanie ma zależeć tylko od pierwszego String(jeden)
    @Override
    public int compareTo(Zad17_40 o) {
        return jeden.compareTo(o.jeden);
    }
    // comparator definiuje inny sposób sortowania, ignoruje pole "jeden" i porównuje obiekty tylko po polu "dwa"
        @Override
    public int compare(Zad17_40 o1, Zad17_40 o2) {
         return o1.dwa.compareTo(o2.dwa);
    }
    public String toString() {
        return "[" + jeden + ", " + dwa + "]";
    }
    public static void main (String[] args) {
        //generator, tworzy string o długości 5 znaków
        RandomGenerator.String gen = new RandomGenerator.String(5);
        List<Zad17_40> kk = new ArrayList<Zad17_40>();
        Comparator<Zad17_40> gz = new Zad17_40("KK", "da");
        for(int i = 0; i <150 ; i++)
        kk.add(new Zad17_40(gen.next(), gen.next()));
        println("\nArrayList kk: \n" + kk);
        //sortowanie listy przez comparator (według drugiego stringa, czyli pola "dwa")
        Collections.sort(kk, gz);
        println("\nArrayList kk SORTED przez Comparator: \n" + kk);
        //szukany obiekt ma dowolne pierwsze pole, bo comparator patrzy tylko na "dwa"
        Zad17_40 szukanieBinary = new Zad17_40("test", kk.get(10).dwa);
        //binarySearch musi używać tego samego comparatora którym wcześniej posortowano listę
        int index = Collections.binarySearch(kk, szukanieBinary, gz);
        println("\nBinary Search w kk przez Comparator: " + index);
        println("Znaleziony element: " + kk.get(index));
        //sortowanie naturalne, przez comparable, według pierwszego stringa ("jeden")
        Collections.sort(kk);
        println("\nArrayList kk SORTED przez Comparable: \n" + kk);
        Zad17_40[] ka = new Zad17_40[15];
        for(int i =0; i < ka.length; i++)
            ka[i] = new Zad17_40(gen.next(), gen.next());
        println("\nTABLICA ka: \n" + Arrays.toString(ka));
        //sortowanie przez Comparator, czyli wg drugiego Stringa
        Arrays.sort(ka, gz);
        println("\nTABLICA ka SORTED przez Comparator: \n" + Arrays.toString(ka));
        //szukany element według pola "dwa"
        //pierwszy String jest dowolny, bo Comparator go ignoruje
        Zad17_40 szukanieBinary2 = new Zad17_40("test", ka[10].dwa);
        //tablica posortowana przez gz, więc binarySearch też musi dostać gz
        int indexx = Arrays.binarySearch(ka, szukanieBinary2, gz);
        println("\nBinary Search w ka przez Comparator: " + indexx);
        println("Znaleziony element: " + ka[indexx]);
        // sortowanie naturalne wg pierwszego String ("jeden")
        Arrays.sort(ka);
        println("\nTABLICA ka SORTED przez Comparable: \n" + Arrays.toString(ka));

    }
}
