import Rozdzial_15.typeinfo.pets.*;
import java.lang.reflect.*;
import java.util.*;

/*
Exercise 40: (3) Add a speak( ) method to all the pets in typeinfo.pets. Modify
Apply.java to call the speak( ) method for a heterogeneous collection of Pet.
*/

class Applys {
    public static <T, S extends Iterable<? extends T>>
    void applys(S seq, Method f, Object... args) {
        try {
            for(T t: seq)
                f.invoke(t, args);
        } catch(Exception e) {
            // Failures are programmer errors
            throw new RuntimeException(e);
        }
    }
}
public class Zad15_40 {
    public static void main(String[] args) throws Exception {
        List<Pet> petos = Pets.arrayList(10); // heterogenic collection
        Applys.applys(petos, Pet.class.getMethod("speak"));
    }
}

/*
do pets w package Rozdzial_15.typeinfo.pets; dodano metode speak() i wywołano ją tutaj za pomocą Apply
jest to opcja zastępcza wykorzystująca reflection przy tak zwanym latent typing(duck typing), czyli styl pisania tak, aby nie trzeba było implementować wspólnego interfejsu z metodami.
W javie zadanie jest utrudnione, ponieważ java nie wspiera latent typing, więc trzeba użyć obejścia, np refleksji.
Dla przykladzie w pythonie nie jest ważne czy x implementuje interfejs y, po prostu próbuje wywołać wybraną metodę x
*/