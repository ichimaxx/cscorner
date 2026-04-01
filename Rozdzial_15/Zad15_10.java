/*
Exercise 10: (1) Modify the previous exercise so that one of f( )’s arguments is nonparameterized.
*/

public class Zad15_10 {
    public <T, U> void f(T x, U y, String z) { // ostatni argument jest nieparametryzowany (String)
        System.out.println(x.getClass().getName() + " " + y.getClass().getName() + " " + z);
    }
    public static void main(String[] args) {
        Zad15_10 gm = new Zad15_10();
        gm.f("", 5, "LAST ARG IS STRING"); /
        gm.f(1, 1.0, "LAST ARG IS STRING");
        gm.f(1.0, 4, "LAST ARG IS STRING");
        gm.f(1.0F, 4., "LAST ARG IS STRING");
        gm.f('c', 1.5F, "LAST ARG IS STRING");
        gm.f(gm,  gm, "LAST ARG IS STRING"); // trzeci argument jest nieparametryzowany i ma konkretny typ wiec musi być stringiem
    }
}
