
import myutils.RandomGenerator;
import java.util.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 42: (2) Modify Exercise 40 so that an alphabetic sort is used.
*/
public class Zad17_42 implements Comparable<Zad17_42>, Comparator<Zad17_42>  {
    // przechowywane są dwa Stringi,
    // jeden - do naturalnego sortowania przez Comparable
    // dwa do alternatywnego sortowania przez Comparator
    String jeden;
    String dwa;
    public Zad17_42(String a, String b) {
        this.jeden = a;
        this.dwa = b;
    }
    //comparable definiuje sposób sortowania obiektów tej klasy,
    //w tym zadaniu sortowanie przechodzi alfabetycznie, bez rozróżniania wielkości liter według pola "jeden"
    @Override
    public int compareTo(Zad17_42 o) {
        //ponieważ nie można sortować obiektów Zad17_42 bezpośrednio Comparatorem dla Stringów,
        //użyto String.CASE_INSENSITIVE_ORDER wewnątrz metody porównującej
        return String.CASE_INSENSITIVE_ORDER.compare(jeden, o.jeden);
    }
    //comparator definiuje inny sposób sortowania, ignoruje pole "jeden" i porównuje obiekty tylko po polu "dwa"
    //jednak nadal sortuje elementy alfabetycznie jak comparable
    @Override
    public int compare(Zad17_42 o1, Zad17_42 o2) {
        //ponieważ nie można sortować obiektów Zad17_42 bezpośrednio Comparatorem dla Stringów,
        //użyto String.CASE_INSENSITIVE_ORDER wewnątrz metody porównującej
        return String.CASE_INSENSITIVE_ORDER.compare(o1.dwa, o2.dwa);
    }
    public String toString() {
        return "[" + jeden + ", " + dwa + "]";
    }
    public static void main (String[] args) {
        //generator, tworzy string o długości 5 znaków
        RandomGenerator.String gen = new RandomGenerator.String(5);
        List<Zad17_42> kk = new ArrayList<Zad17_42>();
        Comparator<Zad17_42> gz = new Zad17_42("KK", "da");
        for(int i = 0; i <150 ; i++)
            kk.add(new Zad17_42(gen.next(), gen.next()));
        println("\nArrayList kk: \n" + kk);
        //sortowanie listy przez comparator (według drugiego stringa, czyli pola "dwa")
        Collections.sort(kk, gz);
        println("\nArrayList kk SORTED przez Comparator(Alfabetycznie po String2): \n" + kk);
        //szukany obiekt ma dowolne pierwsze pole, bo comparator patrzy tylko na "dwa"
        Zad17_42 szukanieBinary = new Zad17_42("test", kk.get(10).dwa);
        //binarySearch musi używać tego samego comparatora którym wcześniej posortowano listę
        int index = Collections.binarySearch(kk, szukanieBinary, gz);
        println("\nBinary Search w kk przez Comparator(Alfabetycznie po String2): " + index);
        println("Znaleziony element: " + kk.get(index));
        //sortowanie naturalne, przez comparable, według pierwszego stringa ("jeden")
        Collections.sort(kk);
        println("\nArrayList kk SORTED przez Comparable(Alfabetycznie po String1): \n" + kk);
        Zad17_42[] ka = new Zad17_42[15];
        for(int i =0; i < ka.length; i++)
            ka[i] = new Zad17_42(gen.next(), gen.next());
        println("\nTABLICA ka: \n" + Arrays.toString(ka));
        //sortowanie przez Comparator, czyli wg drugiego Stringa
        Arrays.sort(ka, gz);
        println("\nTABLICA ka SORTED przez Comparator(Alfabetycznie po String2): \n" + Arrays.toString(ka));
        //szukany element według pola "dwa"
        //pierwszy String jest dowolny, bo Comparator go ignoruje
        Zad17_42 szukanieBinary2 = new Zad17_42("test", ka[10].dwa);
        //tablica posortowana przez gz, więc binarySearch też musi dostać gz
        int indexx = Arrays.binarySearch(ka, szukanieBinary2, gz);
        println("\nBinary Search w ka przez Comparator(Alfabetycznie po String2): " + indexx);
        println("Znaleziony element: " + ka[indexx]);
        // sortowanie naturalne wg pierwszego String ("jeden")
        Arrays.sort(ka);
        println("\nTABLICA ka SORTED przez Comparable(Alfabetycznie po String1): \n" + Arrays.toString(ka));
    }
}
