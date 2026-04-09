import static myutils.Skrocenie_Print.*;


/*
Exercise 20: (1) Create an interface with two methods, and a class that implements that
interface and adds another method. In another class, create a generic method with an
argument type that is bounded by the interface, and show that the methods in the interface
are callable inside this generic method. In main( ), pass an instance of the implementing
class to the generic method.
*/

interface ziuu {
    void f();
    void g();
}
class M2 implements ziuu {
    public void f() {
        println("metF");
    }
    public void g() {
        println("metG");
    }
    public void h(){
        println("metH");
    }
}
class Zad15_20 {
    public <T extends ziuu> void get(T x) {
        x.g();
        x.f();
    } // bierze f() i g() z danego obiektu, dzieki bound (T extends ziuu) kompilator podczas sprawdzania typów wie że T ma metody z interfejsu ziuu bez tej informacji wyszedłby błąd, ze nie można znaleźć metod.
    public static void main (String[] args) {
        Zad15_20 k = new Zad15_20();
        M2 z = new M2(); // nowy obiekt M2
        k.get(z); // wywolanie metod
    }

}

//W generykach działa erasure: po kompilacji parametr typu jest wymazywany. Dla zwykłego T odpowiada to Object, a dla T extends ziuu - typowi ziuu.