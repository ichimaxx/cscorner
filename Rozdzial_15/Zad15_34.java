import static myutils.Skrocenie_Print.*;

/*
Exercise 34: (4) Create a self-bounded generic type that contains an abstract method
that takes an argument of the generic type parameter and produces a return value of the
generic type parameter. In a non-abstract method of the class, call the abstract method
and return its result. Inherit from the self-bounded type and test the resulting class.
*/
abstract class ResultingClass<T extends ResultingClass<T>> { //self-bounded generic type, powoduje ze parametr T jest ograniczont do typów dziedziczacych po ResultingClass<T>
    T cos;
    abstract T set(T arg); // abstract method bierze T i zwraca T

    T get(){
        return set(cos); // metoda wywoluje set i zwraca jego wynik
    }
    public String toString() {
        return getClass().getName(); // pomoc do wypisywania klasy
    }
}
public class Zad15_34 extends ResultingClass<Zad15_34>{ //CRG(Curiously recurring generic) polega na tym ze klasa pochodna dziedziczy po typie generycznym i przekazuje sama siebie jako parametr typu
    @Override // override abstract method
    Zad15_34 set(Zad15_34 arg) {
        cos = arg; // zapisuje obiekt typu Zad15_34
        return arg; // zwraca Zad15_34
    }
    public static void main(String[] args){
        Zad15_34 g = new Zad15_34(); // obiekt klasy pochodnej (Zad15_34)
        println(g.set(new Zad15_34())); // test metody abstract zaimplementowanej w klasie pochodnej(Zad15_34)
        println(g.get()); // test zwyklej metody z klasy bazowej (ResultingClass)
    }
}

/*
wnioski:
erasure psuje overloading i interfejsy z różnymi parametrami typów
 */