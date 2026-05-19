import java.util.Arrays;
import static myutils.Skrocenie_Print.println;
/*
Exercise 24: (3) Show that the class from Exercise 19 can be searched.
*/

//Zad16_19 zostało przepisane na potrzeby tego zadania, aby już nie edytować starych zadań
public class Zad16_24 implements Comparable<Zad16_24> { // implementacja comparable(nowe) wymagane do sortowania, nie było tego oryginalnie w Zad16_19
    private static int k;
    private final int z;
    private static boolean status = false;
    public Zad16_24(int go) {
        if(!status) {
            k = go;
            status = true;
        }
        z = k++;
    }
    @Override //nadpisanie compareTo z interfejsu Comparable, wymagany Override przy implementacji interfejsu
    public int compareTo(Zad16_24 rv) { // sortuje obiekty wg pola z
        return (z > rv.z ? -1 : (z == rv.z ? 0 : 1));
    } // sortuje malejąco

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zad16_24) { // sprawdza, czy obj będzie instanceof Zad16_24
            Zad16_24 test = (Zad16_24)obj; // obj jest drugim porównywalnym obiektem, rzutujemy go na Zad16_24, żeby dostać się do jego pola z
            return z == test.z; // sprawdza, czy pole z w obu obiektach ma tę samą wartość
        }
        return false; // jeśli obj nie jest typu Zad16_24, obiekty nie są równe
    }

    public String toString() {
        return "VALUE " + z;
    }

    public static void main(String[] args) {
        int size = 5;
        //int wartosc = 690;
        int wartosc2 = 650;
        //Zad16_19[] tab = new Zad16_19[size]; // tworzenie tablicy Zad16_19, uda się, jeśli zadanie jest w tym samym folderze(Rozdzial_16)
        Zad16_24[] tab1 = new Zad16_24[size];
        for (int i = 0; i < size; i++)
            tab1[i] = new Zad16_24(wartosc2);
        //for (int i = 0; i < size; i++)
        // tab[i] = new Zad16_19(wartosc); // tworzenie tablicy Zad16_19, uda się, jeśli zadanie jest w tym samym folderze(Rozdzial_16)
        println("array z[]: " + Arrays.toString(tab1));
        // println("array tab[]: " + Arrays.toString(tab)); // print tablicy z Zad16_19, uda się, jeśli zadanie jest w tym samym folderze(Rozdzial_16)
        Arrays.sort(tab1);
        //Arrays.sort(tab); // sortowanie tablicy Zad16_19, daje exception in thread "main" java.lang.ClassCastException.
        //Powodem jest wyjątku jest brak interfejsu Comparable<Zad16_19> w Zad16_19
        println("Sorted array z[]: " + Arrays.toString(tab1));
        int index = Arrays.binarySearch(tab1, tab1[3]);
        println("\n1");
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + tab1[index]);
        } else {
            println("Nie można znaleźć elementu, tab1[index] - index jest ujemny: tab1[" + index + "]");
        }
    }
}
