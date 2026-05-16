import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 19: (2) Create a class with an int field that’s initialized from a constructor
argument. Create two arrays of these objects, using identical initialization values for each
array, and show that Arrays.equals( ) says that they are unequal. Add an equals( )
method to your class to fix the problem.

*/
public class Zad16_19 {
    private int k;
    public Zad16_19(int k) {
        this.k = k;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zad16_19) { // sprawdza, czy obj będzie instanceof Zad16_19
            Zad16_19 test = (Zad16_19)obj; // obj jest drugim porównywalnym obiektem, rzutujemy go na Zad16_19, żeby dostać się do jego pola k
            return k == test.k; // sprawdza, czy pole k w obu obiektach ma tę samą wartość
        }
        return false; // ta metoda naprawia problem i sprawia, że obie tablice są equals.
        //Metoda Arrays.equals(o, z) sprawdza wartości w środku tablicy. Jeśli elementy są obiektami(przykład new Zad16_19()) używa ich metody equals(). Ale jeśli klasa nie ma własnej metody equals(), używana jest domyślna Object.equals(), która porównuje czy to jest ten sam obiekt w pamięci.
        //Dlatego bez dodatkowej metody equals() porównanie wychodzi false, bo używana jest domyślna metoda do obiektów.
    }
    public String toString() { return "VALUE " + k; }
    public static void main (String[] args){
        int size = 5;
        int wartosc = 690;
        Zad16_19[] z = new Zad16_19[size];
        Zad16_19[] o = new Zad16_19[size];
        for (int i = 0; i < size; i++)
            z[i] = new Zad16_19(wartosc);
        println("array z[]: " + Arrays.toString(z));
        for (int i = 0; i < size; i++)
            o[i] = new Zad16_19(wartosc);
        println("array o[]: " + Arrays.toString(o));
        println("Czy array o[] i array z[] są equal? : " + Arrays.equals(o, z));
    }
}
