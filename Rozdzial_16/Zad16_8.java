import java.util.Arrays;

import static myutils.Skrocenie_Print.*;

/*
Exercise 8: (1) Demonstrate the assertions in the previous paragraph.
*/
class BerylliumSphere4 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_8<T> {
    public Zad16_8(int size) {
        T[] array; // OK
        //! array = new T[size]; // nie można tworzyć tablicy generyków ze względu na erasure
        array =(T[])new Object[size]; // kompiluje ale da unchecked warning, bo tablica naprawdę jest Object[] więc nie sprawdza dynamicznie typu T
    }
    public static void main (String[] args) {
        String[] ls = new String[3];
        Object[] la = new Object[4]; // do tablicy Object[] można wprowadzić różne obiekty
        String[] object = ls; // referencja typu String[], kompilator pozwoli tylko na String
        Object[] objects2 = ls; // ta tablica jest kowariantna, można dodać do tej tablicy Integer, String, obiekt, ale nie można dać prymitywów. Jednak runtime nadal pilnuje, że to prawdziwa tablica String[] i w przypadku dodania Integer lub innego typu niż String, program zatrzyma się na tej linii w runtime, wyrzucając wyjątek
        ls[0] =  "lxl";
        //objects2[1] = 3; // w runtime rzuci ArrayStoreException, bo 3 jest Integer a runtime pilnuje że tablica jest String[]
        //object[1] = 4; // nie pozwoli nawet skompilować
        la[0] = 5;
        la[1] = "String";
        la[2] = new BerylliumSphere4();
        println(Arrays.deepToString(la));
        println(Arrays.deepToString(ls));
    }
}
