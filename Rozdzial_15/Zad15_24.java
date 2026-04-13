import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 24: (3) Modify Exercise 21 so that factory objects are held in the Map instead
of Class<?>.

 */
interface FactoryII<T> {
    T create();
}
class Buildings {
    public static class Factory implements FactoryII<Buildings> {
        public Buildings create() {
            return new Buildings();
        }
    }
    public String toString() {
        return "Empire State Building";
    }
}
class Houses extends Buildings {
    public static class Factory implements FactoryII<Houses> {
        public Houses create() {
            return new Houses();
        }
    }
    public String toString() {
        return "Empire State House";
    }
}
public class Zad15_24<T> {
    Class<T> kind;

    Map<String, FactoryII<? extends T>> boss = new TreeMap<>(); // tworzenie mapy z Factory z wildcard dlatego ponieważ generyki są niekowariantne czyli np Factory<Buildings> i Factory<Houses> to dla javy jest to tylko Factory<Buildings> mimo że Houses dziedziczy(extends) po Buildings
    public Zad15_24(Class<T> kind) {
        this.kind = kind;
    }

    public void addTyper(String typename, FactoryII<? extends T> oks) { // metoda addType dodająca do mapy naszego String i fabryke klasy
        boss.put(typename, oks);
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg); // sprawdza czy arg jest instancją typu przechowywanego w kind (instanceof)
    }
    public void createNew (String typename) {
        if(boss.get(typename) == null) // sprawdza czy w mapie jest typename który chcemy stworzyć
            println("NIE MA TAKIEGO TYPENAME W MAPIE"); // jesli nie
        else
            println(boss.get(typename).create()); //jeśli jest, wywoluje metode z danej fabryki wpisanej w main, dzieki temu ze zrobione jest z factories nie trzeba używac reflection
    }
    public static void main(String[] args) {
        Zad15_24<Buildings> ctt1 = new Zad15_24<>(Buildings.class);
        System.out.println(ctt1.f(new Buildings()));
        System.out.println(ctt1.f(new Houses()));
        Zad15_24<Houses> ctt2 = new Zad15_24<>(Houses.class);
        System.out.println(ctt2.f(new Buildings()));
        System.out.println(ctt2.f(new Houses()));
        ctt1.addTyper("House", new Houses.Factory()); // dodane fabryki
        ctt1.addTyper("Building", new Buildings.Factory());
        ctt1.createNew("House");
        ctt1.createNew("Building");
        ctt1.createNew("INNE");
    }
}