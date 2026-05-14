import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
import java.math.BigDecimal;
/*
Exercise 17: (5) Create and test a Generator for BigDecimal, and ensure that it works
with the Generated methods.
*/

public class Zad16_17 {
    public static class
    BigDecimals implements myutils.Generator<BigDecimal> {
        private int razy = 1;
        private BigDecimal value = BigDecimal.ZERO; // aktualna wartość generatora, domyślnie 0 (BigDecimal.ZERO)
        public BigDecimals() {
            this(1,1); // jeśli nie podano argumentów w main krok będzie 1 i wartość 1
        }
        public BigDecimals(int razy) {
            this.razy = razy; // jeżeli podano tylko krok w main to będzie zdefiniowany a wartość będzie 0
        }
        public BigDecimals(int razy, int wartosc) {
            this.razy = razy;
            this.value = new BigDecimal(wartosc); // w przypadku dwóch argumentów w main ustawia początkową wartość generatora
        }
        public BigDecimal next() {
            BigDecimal result = value;
            value = value.add(BigDecimal.valueOf(razy));// zwiększa wartość o krok podany w konstruktorze(4)
            return result; }
    }
    public static void main (String[] args) {
        int size = 9;
        BigDecimal[] a1 = Generated.array(BigDecimal.class, new BigDecimals(4, 6), size); //
        println("a1(BigDecimal) Generated.array(nowa array) = " + Arrays.toString(a1));
        BigDecimal[] a2 = new BigDecimal[15];
        a2 = Generated.array(a2, new BigDecimals(4, 6));
        println("a2(BigDecimal) Generated.array(istniejąca array) = " + Arrays.toString(a2));
    }
}
