import java.util.*;

/*
Exercise 29: (5) Create a generic method that takes as an argument a
Holder<List<?>>. Determine what methods you can and can’t call for the Holder and for
the List. Repeat for an argument of List<Holder<?>>.
*/
class Holder<T> {
    private T value;
    public Holder() {}
    public Holder(T val) { value = val; }
    public void set(T val) { value = val; }
    public T get() { return value; }
    public boolean equals(Object obj) {
        return value.equals(obj);
    }
}

public class Zad15_29 {
    static <T> void check1(Holder<List<?>> holder1) {; // holder1 ma typ Holder<List<?>> czyli Holder przechgowuje jakis obiekt typu List<?>
        List<?> list = holder1.get(); // List<?> wyjęta z holdera
        holder1.get(); // get() zwraca tu List<?> bo typ parametru holdera jest znany
        holder1.set(new ArrayList<String>()); // holder przechowuje List<?> a ArrayList<String> pasuje do List<?>
        holder1.equals(new ArrayList<>()); // equals sprawdza obiekt czyli ArrayList a nie typ wiec wildcard nie przeszkadza
        //list.add(new Object()); // add(E) wymaga znajomosci konkretnego typu elemetnu a w List<?> typ elementu jest nieznany
        list.get(0); // z List<?> można czytać ale bezpiecznie jest tylko jako Object, bo nie znamy konkretnego typu elementu
        list.remove(0); //usuwanie po indeksie, nie trzeba zwracac typu elementu wiec dziala
        list.clear(); // to samo co wyzej
        list.contains("abc"); // contains bierze Object wiec wildcard nie blokuje wywołania
        list.isEmpty(); // metoda informacyjna, nie potrzebuje typu
        //list.set(0, new Object());
}
    static <T> void check2(List<Holder<?>> holder2) { // holder2 ma typ List<Holder<?>> czyli lista wie ze przechowuje elementy typu Holder<?>
        Holder<?> hold = holder2.get(0); // Holder<?> wyjęty z List(holder2)
        holder2.add(new Holder<Integer>(45)); // Holder<Integer> pasuje do Holder<?>
        holder2.add(new Holder<String>("OK")); // jw.
        holder2.remove(0); //usuwanie po indeksie nie wymaga znajomosci typu wewnątrz Holdera
        holder2.clear(); // czyszczenie listy nie wymaga znajomosci typu
        holder2.isEmpty(); // metoda informacyjna, niepotrzebny typ
        Object y = hold.get(); // z Holder<?> można czytać informacje, ale bezpiecznie bez warnings tylko jako Object
        //hold.set(5353); // hold ma nieznany typ wiec kompilator nie może pozwolić na set
        hold.equals(new Holder<>()); // equals sprawdza obiekt czyli Holder a nie typ wiec wildcard nie przeszkadza
}
    public static void main (String[] args) {
        ArrayList<Holder<?>> z = new ArrayList<Holder<?>>();
        Holder<List<?>> g = new Holder<List<?>>();
        Holder<?> o = new Holder<>("gdd"); // lista przechowuje Holder<?> wiec możemy dodać Holder<String>
        ArrayList<String> f = new ArrayList<>(); // robimy liste Stringow
        f.add("hi"); // dodajemy do niej Stringa
        g.set(f); // i dopiero teraz dajemy do Holdera ale check1() bedzie ją widzieć już jako List<?>. Jest to zrobione po to, aby można było w check1 operować na wartościach, żeby nie było w środku nulla
        z.add(o);
        check1(g);
        check2(z);
    }
}