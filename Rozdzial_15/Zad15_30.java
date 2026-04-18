import static myutils.Skrocenie_Print.*;

/*
Exercise 30: (2) Create a Holder for each of the primitive wrapper types, and show that
autoboxing and autounboxing works for the set( ) and get( ) methods of each instance.
*/
class Holder2<T> {
    private T value;
    public Holder2() {}
    public Holder2(T val) { value = val; }
    public void set(T val) { value = val; }
    public T get() { return value; }
    public boolean equals(Object obj) {
        return value.equals(obj);
    }
}

public class Zad15_30 {
    public static void main (String[] args) {
        boolean boli = false;
        Holder2<Boolean> bolh = new Holder2<Boolean>(true);
        println(bolh.get());
        bolh.set(boli);
        println("boolean after change:");
        boolean b = bolh.get();
        println(b + "\n");

        char chari = 's';
        Holder2<Character> charh = new Holder2<Character>('p');
        println(charh.get());
        charh.set(chari);
        println("char after change:");
        char c = charh.get();
        println(c + "\n");

        byte byti = 0;
        Holder2<Byte> byth = new Holder2<Byte>(byti);
        println(byth.get());
        byti = 1;
        byth.set(byti);
        println("byte after change:");
        byte bt = byth.get();
        println(bt + "\n");

        short shorti = 32767;
        Holder2<Short> shorth = new Holder2<Short>(shorti);
        println(shorth.get());
        shorti = 32761;
        shorth.set(shorti);
        println("short after change:");
        short sh = shorth.get();
        println(sh + "\n");

        int inti = 76587644;
        Holder2<Integer> inth = new Holder2<Integer>(1564652);
        println(inth.get());
        inth.set(inti);
        int it = inth.get();
        println("int after change:");
        println(it + "\n");

        long longi = 76587434644L;
        Holder2<Long> longh = new Holder2<Long>(longi);
        println(longh.get());
        longi = 765876441241L;
        longh.set(longi);
        long lg = longh.get();
        println("long after change:");
        println(lg + "\n");

        float floati = 0.5f;
        Holder2<Float> floath = new Holder2<Float>(0.6f);
        println(floath.get());
        floath.set(floati);
        float fl = floath.get();
        println("float after change:");
        println(fl + "\n");

        double doubli = 1341341431414314d;
        Holder2<Double> doublh = new Holder2<Double>(doubli);
        println(doublh.get());
        doubli = 13413414314434343433445414314d;
        doublh.set(doubli); // udowadnia autoboxing ponieważ na metodach generycznych nie można używać primitives, a tu wprowadzamy primitive
        double dl = doublh.get(); // udowadnia autounboxing ponieważ na metodach generycznych nie można używać primitives, a tu rozpakowujemy do primitive
        println("double after change:");
        println(dl + "\n");
    }
}
// cwiczenie pokazuje, źe autoboxing i autounboxing pozwala pracować używając prymitywów z generykami, mimo że nie mogą być parametrami typów generycznych. Przy set() primitives zamienia sie na wrapperem a przy get wrapper rozpakowuje się z powrotem do primitive