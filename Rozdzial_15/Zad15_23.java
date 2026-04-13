import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (1) Modify FactoryConstraint.java so that create( ) takes an
argument.
 */

interface FactoryI<T> {
    T create(T a);
}
class Foo2<T> {
    private T x;
    public <F extends FactoryI<T>> Foo2(F factory, T z) { // dodano argument 'z' do konstruktora Foo2
        x = factory.create(z);
    }
    public String toString() {
        return String.valueOf(x);
    }

}
class IntegerFactory implements FactoryI<Integer> {
    public Integer create(Integer g) {// tutaj argument typu zamienia sie w Integer
        return g;
    }
}
class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create(Widget f) {// tutaj argument typu zamienia sie w Widget tak jak klasa
            return new Widget();
        }
    }
}
public class Zad15_23 {
    public static void main(String[] args) {
        Foo2<Integer> f = new Foo2<Integer>(new IntegerFactory(), 5); // dodano
        Foo2<Widget> g = new Foo2<Widget>(new Widget.Factory(), new Widget());
        println(f);
        println(g);
    }
}