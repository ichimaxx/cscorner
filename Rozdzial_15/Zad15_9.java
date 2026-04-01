
/*
Exercise 9: (1) Modify GenericMethods.java so that f( ) accepts three arguments, all
of which are of a different parameterized type.
*/
public class Zad15_9 {
    public <T, U, Z> void f(T x, U y, Z z) {
        System.out.println(x.getClass().getName() + " " + y.getClass().getName() + " " + z.getClass().getName());
    }
    public static void main(String[] args) {
        Zad15_9 gm = new Zad15_9();
        gm.f("", 5, 3.0);
        gm.f(1, 1.0, 'd');
        gm.f(1.0, 4, 'h');
        gm.f(1.0F, 4., "");
        gm.f('c', 1.5F, 1.2d);
        gm.f(gm, gm, gm); // f() akceptuje 3 argumenty może być też obiekt typu Zad15_9
    }
}
//W porownaniu z generyczna klasa przy generycznej metodzie nie trzeba podawac typu parametru bo komppilator potrafi go sam wywnioskowac, Nazywa sie to type argument inference