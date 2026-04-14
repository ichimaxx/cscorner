
/*
Exercise 25: (2) Create two interfaces and a class that implements both. Create two
generic methods, one whose argument parameter is bounded by the first interface and one
whose argument parameter is bounded by the second interface. Create an instance of the
class that implements both interfaces, and show that it can be used with both generic
methods.
*/

// two interfaces
interface SuperPowers {
    void supermoce();
}
interface SuperBoss {
    void superbos();
}
// class that implements both
class Heros implements SuperBoss, SuperPowers {
    public void superbos() {}
    public void supermoce() {}
}


class Zad15_25 {
    // dwie generyczne metody kazda jest bounded do innego interface
    static <T extends SuperPowers> void usePower(T x) {
        x.supermoce();
    }
    static <T extends SuperBoss> void useBoss(T x) {
        x.superbos();
    }
    public static void main (String[] args) {
        Heros h = new Heros(); // instance klasy
        useBoss(h); // z pomoca klasy która implementuje oba interfejsy jestesmy w stanie uruchomić obie metody generyczne, i ta która implementuje SuperPowers i SuperBoss
        usePower(h);
    }
}