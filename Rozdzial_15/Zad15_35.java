import Rozdzial_15.coffee.*;
import java.util.*;
/*
Exercise 35: (1) Modify CheckedList.java so that it uses the Coffee classes defined in
this chapter.
*/
public class Zad15_35 {
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyAmericanos) {
        probablyAmericanos.add(new Breve());
    }
    public static void main(String[] args) {
        List<Americano> americanos1 = new ArrayList<Americano>();
        oldStyleMethod(americanos1); // Quietly accepts a Breve
        List<Americano> americanos2 = Collections.checkedList(
                new ArrayList<Americano>(), Americano.class);
        try {
            oldStyleMethod(americanos2); // Throws an exception
        } catch(Exception e) {
            System.out.println(e);
        }
        // Derived types work fine:
        List<Coffee> coffees = Collections.checkedList(
                new ArrayList<Coffee>(), Coffee.class);
        coffees.add(new Americano());
        coffees.add(new Breve());
    }
}

// zamiana kodu z używania Pets.class na Coffee.class