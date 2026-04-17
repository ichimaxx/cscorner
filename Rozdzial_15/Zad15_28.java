import java.util.*;
import Rozdzial_15.typeinfo.pets.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 28: (4) Create a generic class Generic1<T> with a single method that takes an
argument of type T. Create a second generic class Generic2<T> with a single method that
returns an argument of type T. Write a generic method with a contravariant argument of the first
generic class that calls its method. Write a second generic method with a covariant argument of
the second generic class that calls its method. Test using the typeinfo.pets library.
 */
// klasa generyczna bierze argument typu T
class Generic1<T> {
   void readExact1(T first) {
        println(first);
    }
    // mnetoda generyczna z argumentem kontrawariantnym czyli w praktyce oznacza Generic1<? super T> oznacza generic1 jakiegos nadtypu T, dzięki temu można typowo bezpiecznie przekazać obiekt typu T
   static <T> void callWithWildcard(Generic1<? super T> cov, T typ) {
         cov.readExact1(typ);
    }
}
// klasa generyczna zwraca obiekt typu T
class Generic2<T> {
    T d; // pole przechowujace obiekt typu T
    public Generic2 (T f) {
        d = f;
    }

    T readExact2() {
        return d;
    }
    // metoda z argumenem kowariantnym, Generic2<? extends T> oznacza Generic2 jakiegos podtypu T
    static <T> T callWithoutWildcard(Generic2<? extends T> covs) {
        return covs.readExact2();
    }
}


public class Zad15_28 {

    public static void main (String[] args) {
        /*
        CONTRAVARIANCE ? super T
        Generic1<Individual> jest szersze niz Pet wiec mozna bezpiecznie przekazac pet do Generic1<? super Pet>
         */
        Generic1.callWithWildcard(new Generic1<Individual>(), new Pet());
        /*
        COVARIANCE ? extends T
        stworzono producenta typu Generic2<Pet>
        w srodku siedzi Mutt który jest podtypem Pet, metoda zwraca wynik jako typ szerszy czyli Individual
        */
        Individual i = Generic2.callWithoutWildcard(new Generic2<Pet>(new Mutt()));
    }
}

/* podsumowując:
? super T oznacza, to jest cos co przyjmie T albo jego podtyp, w ksiazce napisane jest o tym jako write/pass into i do takiego generyka mozna wtedy bezpieczenie wlozyc obiekt typu T
? extends T oznacza ze to jest cos co zwraca jakis typ będący T albo jego podtypem. Książka podsumowuje to jako read/return from czyli z takiego generyka można bezpiecznie odczytać wynik jako T

? super T  == insert
? extends T == get
 */