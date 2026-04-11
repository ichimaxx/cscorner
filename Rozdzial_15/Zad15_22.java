import static myutils.Skrocenie_Print.*;

/*
Exercise 22: (6) Use a type tag along with reflection to create a method that uses the
argument version of newInstance( ) to create an object of a class with a constructor that
has arguments.
 */

class Building { // klasa bazowa z konstruktorem String
    String z;
    public Building(String z) {
        this.z = z;
    }
    public String toString() {
        return z;
    }
}
class House extends Building {
    String k;
    public House(String k) {
        super(k); // wywolanie konstruktora klasy bazowej, do building zawsze przekazywane k
        this.k = k;
    }
    public String toString() {
        return k;
    }
}
public class Zad15_22<T> {
    Class<T> kind; // type tag, przechowuje obiekt Class<T> czyli informacje o typie T, dzieki temu można w runtime sprawdzać typ i tworzyć obiekty przez reflection
    public Zad15_22(Class<T> kind) {
        this.kind = kind;
    }
    public boolean f(Object arg) {
        return kind.isInstance(arg); // sprawdza czy arg jest instancją typu przechowywanego w kind (instanceof), to taki odpowiednik instanceof ale z uzyciem type tagu dla generyków
    }
    public void createNew (Class<T> typename, String f) throws Exception { // metoda tworzy nowy obiekt klasy przekazanej w type tagu "typename", używa reflection, znajduje konstruktor przyjmujący String, wywołuje go z argumentem f i wypisuje utworzony obiekt
        // to jest zrobione w taki sposób ponieważ w generykach nie mozna napisać po prostu new T(f), zamiast tego używamy type tagu Class<T> i reflection
            println(typename.getDeclaredConstructor(String.class).newInstance(f)); // to taki odpowiednik new T(f) którego nie da się stworzyć normalnie jako new T(f) tak jak można to zrobić w C++
        /*
        to takie:
        typename - jaki typ stworzyć
        getDeclaredConstructor(String.class) - jaki konstruktor ma znaleźć
        newInstance(f) - jaki argument przekazać
         */
    }
    public static void main(String[] args) throws Exception {
        Zad15_22<Building> ctt1 = new Zad15_22<Building>(Building.class); // obiekt przechowujący type tag dla Building
        System.out.println(ctt1.f(new Building("OKS")));
        System.out.println(ctt1.f(new House("BS")));
        Zad15_22<House> ctt2 = new Zad15_22<House>(House.class); // obiekt przechowujący type tag dla House
        System.out.println(ctt2.f(new Building("OKS")));
        System.out.println(ctt2.f(new House("BOS")));
        ctt2.createNew(House.class, "WOW"); // tworzy nowy obiekt House przez reflection, używając konstruktora House(String)
        ctt1.createNew(Building.class, "ELS"); // tworzy nowy obiekt Building przez reflection, używając konstruktora Building(String)

    }
}
