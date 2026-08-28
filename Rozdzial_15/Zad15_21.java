import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.lang.reflect.*;

/*
Exercise 21: (4) Modify ClassTypeCapture.java by adding a
Map<String,Class<?>>, a method addType(String typename, Class<?> kind), and
a method createNew(String typename). createNew( ) will either produce a new
instance of the class associated with its argument string, or produce an error message.
 */

class Building_2 {
    public String toString() {
        return "Empire State Building";
    }
}
class House_2 extends Building_2 {
    public String toString() {
        return "Empire State House";
    }
}
public class Zad15_21<T> {
    Class<T> kind;
    Map<String, Class<?>> boss = new TreeMap<String,Class<?>>(); // tworzenie mapy
    public Zad15_21(Class<T> kind) {
        this.kind = kind;
    }

    public void addTyper(String typename, Class<?> kind) { // metoda addType dodająca do mapy naszego String i wybraną klasę
        boss.put(typename, kind);
    }
    public boolean f(Object arg) {
        return kind.isInstance(arg); // sprawdza czy arg jest instancją typu przechowywanego w kind (instanceof)
    }
    public void createNew (String typename) throws Exception {
        if(boss.get(typename) == null) // sprawdza czy w mapie jest typename który chcemy stworzyć
            println("NIE MA TAKIEGO TYPENAME W MAPIE"); // jesli nie
        else
        println(boss.get(typename).getDeclaredConstructor().newInstance()); // jeśli w mapie istnieje podany typename, to pobiera skojarzony z nim obiekt Class<?> i tworzy nową instancję tej klasy, używmay reflection ponieważ mapa przechowuje obiekty Class<?> i na ich podstawie tworzymy nowe instancje, po erasure nie można robić new T() wiec trzeba to kompensować przez type tag, czyli jawnie przekazywać obiekt dla danego typu
        }
    public static void main(String[] args) throws Exception {
        Zad15_21<Building_2> ctt1 = new Zad15_21<Building_2>(Building_2.class);
        System.out.println(ctt1.f(new Building_2()));
        System.out.println(ctt1.f(new House_2()));
        Zad15_21<House_2> ctt2 = new Zad15_21<House_2>(House_2.class);
        System.out.println(ctt2.f(new Building_2()));
        System.out.println(ctt2.f(new House_2()));
        ctt1.addTyper("House", House_2.class);
        ctt1.addTyper("Building", Building_2.class);
        ctt1.createNew("House");
        ctt1.createNew("Building");
        ctt1.createNew("INNE");
    }
}
// w zadaniu tworzona jest mapa do której dodana jest nazwa typu jako klucz(key) oraz odpowiadająca jej klasa jako wartość(value), następnie po nazwie typu z mapy próbujemy pobrać z mapy odpowiedni obiekt Class<?>, jeśli taki klucz mamy w mapie, to tworzymy nową instancję tej klasy na podstawie obiektu pobranego z mapy pod tym kluczem (boss.get(typename) klasę skojarzoną z tym kluczem), jeśli nie, wyrzuca komunikat o błędzie
