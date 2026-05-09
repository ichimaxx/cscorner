import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 11: (2) Show that autoboxing doesn’t work with arrays.
*/
public class Zad16_11 {
    public static void main(String[] args) {
        int size = 6;
        Boolean[] a2 = Generated.array(Boolean.class, new myutils.RandomGenerator.Boolean(), size);
        println("a2 = " + Arrays.toString(a2));
        boolean primitive = a2[3]; // autounboxing działa na pojedynczym elemencie z tablicy
        boolean primitives = true; // wartość prymitywna
        Boolean wrapper = primitives; // autoboxing boolean do Boolean
        //Jednak jeśli spróbujemy to zrobić z tablicą:
        boolean[] primitiveArray = {true, false, true}; // tablica prymitywów
        //Boolean[] arrayWrap = primitiveArray; // autoboxing boolean[] do Boolean[] nie działa z tablicami
        //boolean[] primitives2 = a2; // też nie zadziała, Boolean[] nie może się zmienić automatycznie w boolean[]
    }
}
