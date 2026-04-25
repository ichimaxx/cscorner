import static myutils.Skrocenie_Print.*;
/*
Exercise 38: (4) Create a simple Decorator system by starting with basic coffee, then
providing decorators of steamed milk, foam, chocolate, caramel and whipped cream.
*/

class Coffees {
    private String name;
    public void set(String val) { name = val; }
    public String get() { return name; }
}
class Decorator extends Coffees { // tak zwany Decorator
    protected Coffees basic;
    public Decorator(Coffees basic) { this.basic = basic; }
    public void set(String val) { basic.set(val); }
    public String get() { return basic.get(); }
}
class SteamedMilk extends Decorator {
    private final String steammilk;
    public SteamedMilk(Coffees basic) {
        super(basic);
        steammilk = "Steamed Milk";
    }
    public String get() { return basic.get() + " " + steammilk; } // przy klasach pochodnych od dekoratora trzeba dodać pole basic.get() aby pobierało wartości zdefiniowane wcześniej do bieżącej klasy, inaczej zwróci tylko String z danej klasy a reszte "zapomni"
}
class Foam extends Decorator {
    private final String foam;
    public Foam(Coffees basic) {
        super(basic);
        foam = "Foam";
    }
    public String get() { return basic.get() + " " + foam; }
}
class Chocolate extends Decorator {
    private final String choco;
    public Chocolate(Coffees basic) {
        super(basic);
        choco = "Chocolate";
    }
    public String get() { return basic.get() + " " + choco; }
}
class Caramel extends Decorator {
    private final String caramels;
    public Caramel(Coffees basic) {
        super(basic);
        caramels = "Caramel";
    }
    public String get() { return basic.get() + " " + caramels; }
}
class WhippedCream extends Decorator {
    private final String whip;
    public WhippedCream(Coffees basic) {
        super(basic);
        whip = "Whipped Cream";
    }
    public String get() { return basic.get() + " " + whip; }
}
public class Zad15_38 {
    public static void main(String[] args) {
        Coffees t2 = new SteamedMilk(new WhippedCream(new Chocolate(new Foam(new Caramel(new Coffees()))))); // łańcuch dekoratorów owija bazową klase kolejnymi dodatkami
        t2.set("KAWA");
        println(t2.get());
    }
}
/*
OUTPUT: KAWA Caramel Foam Chocolate Whipped Cream Steamed Milk
Decorator działa przez warstwowe owijanie klasy bazowej.
Każdy "dodatek" nadpisuje get() i dopisuje swój "dodatek" do łańcucha.
Dzięki temu obiekt końcowy może byc traktowany jako Coffees ale zawiera też dodatkowe cechy dodane przez dekoratory.
*/