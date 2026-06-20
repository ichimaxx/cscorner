import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 41: (3) Modify the class in the previous exercise so that it will work with
HashSets and as a key in HashMaps.
*/
public class Zad17_41 implements Comparable<Zad17_41>, Comparator<Zad17_41> {
    // przechowywane są dwa Stringi,
    // jeden - do naturalnego sortowania przez Comparable
    // dwa do alternatywnego sortowania przez Comparator
    String jeden;
    String dwa;
    public Zad17_41(String a, String b) {
        this.jeden = a;
        this.dwa = b;
    }
    //comparable definiuje sposób sortowania obiektów tej klasy
    //w tym zadaniu sortowanie ma zależeć tylko od pierwszego String(jeden)
    @Override
    public int compareTo(Zad17_41 o) {
        return jeden.compareTo(o.jeden);
    }
    //do użycia HashMap i HashSet w zadaniu potrzeba zaimplementować metody hashCode i equals,
    //hashCode potrzebne do hashowania mapy/setu
    //equals do porównywania wartości tak, aby nie dodawało drugiej takiej samej wartości do setu
    public int hashCode() {
        return Objects.hash(jeden, dwa);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Zad17_41))
            return false;

        Zad17_41 other = (Zad17_41) o;

        return Objects.equals(jeden, other.jeden) &&
                Objects.equals(dwa, other.dwa);
    }

    // comparator definiuje inny sposób sortowania, ignoruje pole "jeden" i porównuje obiekty tylko po polu "dwa"
    @Override
    public int compare(Zad17_41 o1, Zad17_41 o2) {
        return o1.dwa.compareTo(o2.dwa);
    }
    public String toString() {
        return "[" + jeden + ", " + dwa + "]";
    }
    public static void main (String[] args) {
        //generator, tworzy string o długości 5 znaków
        RandomGenerator.String gen = new RandomGenerator.String(5);
        List<Zad17_41> kk = new ArrayList<Zad17_41>();
        Comparator<Zad17_41> gz = new Zad17_41("KK", "da");

        //ARRAYLIST TEST
        println("\nARRAYLIST TEST\n");
        for(int i = 0; i <150 ; i++)
            kk.add(new Zad17_41(gen.next(), gen.next()));
        println("ArrayList kk: \n" + kk);
        //sortowanie listy przez comparator (według drugiego stringa, czyli pola "dwa")
        Collections.sort(kk, gz);
        println("\nArrayList kk SORTED przez Comparator: \n" + kk);
        //szukany obiekt ma dowolne pierwsze pole, bo comparator patrzy tylko na "dwa"
        Zad17_41 szukanieBinary = new Zad17_41("test", kk.get(10).dwa);
        //binarySearch musi używać tego samego comparatora którym wcześniej posortowano listę
        int index = Collections.binarySearch(kk, szukanieBinary, gz);
        println("\nBinary Search w kk przez Comparator: " + index);
        println("Znaleziony element: " + kk.get(index));
        //sortowanie naturalne, przez comparable, według pierwszego stringa ("jeden")
        Collections.sort(kk);
        println("\nArrayList kk SORTED przez Comparable: \n" + kk);

        //ARRAY TEST
        println("\nARRAY TEST\n");
        Zad17_41[] ka = new Zad17_41[15];
        for(int i =0; i < ka.length; i++)
            ka[i] = new Zad17_41(gen.next(), gen.next());
        println("TABLICA ka: \n" + Arrays.toString(ka));
        //sortowanie przez Comparator, czyli wg drugiego Stringa
        Arrays.sort(ka, gz);
        println("\nTABLICA ka SORTED przez Comparator: \n" + Arrays.toString(ka));
        //szukany element według pola "dwa"
        //pierwszy String jest dowolny, bo Comparator go ignoruje
        Zad17_41 szukanieBinary2 = new Zad17_41("test", ka[10].dwa);
        //tablica posortowana przez gz, więc binarySearch też musi dostać gz
        int indexx = Arrays.binarySearch(ka, szukanieBinary2, gz);
        println("\nBinary Search w ka przez Comparator: " + indexx);
        println("Znaleziony element: " + ka[indexx]);
        // sortowanie naturalne wg pierwszego String ("jeden")
        Arrays.sort(ka);
        println("\nTABLICA ka SORTED przez Comparable: \n" + Arrays.toString(ka));

        //SET TEST
        println("\nHASHSET TEST\n");
        Set<Zad17_41> kkz = new HashSet<Zad17_41>();
        for(int i = 0; i <150 ; i++)
            kkz.add(new Zad17_41(gen.next(), gen.next()));
        println("HashSet kkz: \n" + kkz);
        println("kkz size(): " + kkz.size());
        println("\nset.add(new Zad17_41(\"A\", \"B\"));\n");
        kkz.add(new Zad17_41("A", "B"));
        println("kkz size(): " + kkz.size());
        println("\nset.add(new Zad17_41(\"A\", \"B\"));\n");
        kkz.add(new Zad17_41("A", "B"));
        println("kkz size(): " + kkz.size());
        println("\nset.add(new Zad17_41(\"A\", \"B\"));\n");
        kkz.add(new Zad17_41("A", "B"));
        println("kkz size(): " + kkz.size());
        println("\nHashSet kkz zmodyfikowane: \n" + kkz);

        //HASHMAP TEST
        println("\nHASHMAP TEST\n");
        HashMap<Zad17_41, Integer> ooo = new HashMap<>();
        for(int i = 0; i <150 ; i++)
            ooo.put(new Zad17_41(gen.next(), gen.next()), i);
        println("HashMap ooo: \n" + ooo);
        //klucz1 i klucz2 to dwa różne obiekty ale mają te same pola,
        //HashMap powinna potraktować je jako ten sam klucz
        //pierwszy put() doda nowy wpis, a drugi put() nadpisze wartość pod tym samym kluczem
        Zad17_41 klucz = new Zad17_41("A", "B");
        Zad17_41 klucz2 = new Zad17_41("A", "B");
        println("\nooo.size():" + ooo.size());
        println("\nooo.put(klucz, ooo.size() + 1);");
        println("\nooo.put(klucz2, ooo.size() + 1);");
        ooo.put(klucz, ooo.size() + 1);
        ooo.put(klucz2, ooo.size());
        println("\nooo.get(klucz): " + ooo.get(klucz));
        println("\nooo.get(klucz2): " + ooo.get(klucz2));

    }
}
