import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 12: (1) Create an initialized array of double using CountingGenerator. Print
the results.
*/
public class Zad16_12 {
    public static void main(String[] args) {
        Double[] a = Generated.array(Double.class, new myutils.CountingGenerator.Double(), 15); // tworzenie tablicy Double[]
        double[] b = ConvertTo.primitive(a); // uzycie ConvertTo które kopiuje pojedyncze wartości z tablicy Double[] do tablicy double[] w której są już prymitywy, przy kopiowaniu zachodzi autounboxing z Double na double
        println(Arrays.toString(b)); // print tablicy
    }
}

