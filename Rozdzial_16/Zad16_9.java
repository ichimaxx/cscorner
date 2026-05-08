import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 9: (3) Create the classes necessary for the Peel<Banana> example and show
that the compiler doesn’t accept it. Fix the problem using an ArrayList.
*/
class Peel<T> {
    public String peel(T n) {
        return "Peeling " + n;
    }
}
class Banana {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Banana" +
            " " + id; }
}

public class Zad16_9 {
    public static void main(String[] args) {
        //Peel<Banana>[] peels = new Peel<Banana> [10]; // Illegal, kompilator nie zaakceptuje tablicy typu parametryzowanego, ze względu na erasure zgubi typ, a tablice muszą znać swój typ w runtime
        ArrayList<Peel<Banana>> peels = new ArrayList<Peel<Banana>>(); // rozwiązanie problemu z pomocą ArrayList
        peels.add(new Peel<Banana>()); // dodany obiekt Peel<Banana> do listy
        Peel<Banana> kk = peels.get(0); // wyciągnięty obiekt Peel<Banana> z listy
        println(kk.peel(new Banana())); // na obiekcie wywołano metodę peel z obiektem Banana()
    }
}
