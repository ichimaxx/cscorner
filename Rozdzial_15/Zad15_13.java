import Rozdzial_14.generics.coffee.*;
import net.mindview.util.Generator;
import static myutils.Skrocenie_Print.println;
import java.util.*;

/*
Exercise 13: (4) Overload the fill( ) method so that the arguments and return types are
the specific subtypes of Collection: List, Queue and Set. This way, you don’t lose the type
of container. Can you overload to distinguish between List and LinkedList?
*/

class Fibonacci2 implements Generator<Integer> {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public static void main(String[] args) {
        Fibonacci2 gen = new Fibonacci2();
        for(int i = 0; i < 18; i++)
            System.out.print(gen.next() + " ");
    }
}

public class Zad15_13 { // OVERLOADED metoda fill dla subtypów List,LinkedList,Set i Queue
    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> Set<T> fill(Set<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> Queue<T> fill(Queue<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> LinkedList<T> fill(LinkedList<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        List<Coffee> a = new LinkedList<Coffee>(); // to jest tak zwany overload List
        List<Coffee> coffee = fill(a, new CoffeeGenerator(), 4); /* dzieki temu, ze zdefiniowaliśmy "a" wcześniej kompilator widzi argument typu List<Coffee>!! i wybierze overload, nie patrzy ze w srodku siedzi LinkedList.
        Po lewej mówimy pod jakim typem referencji trzymamy obiekt w pamieci i tą informację dostaje metoda fill, jeżeli chcielibyśmy wpisać na sucho new List<Coffee>() to nie skompiluje się ponieważ wyjdzie błąd cannot be instantiated */
        for(Coffee c : coffee)
            System.out.println(c);
        LinkedList<Integer> fnumbers = fill(new LinkedList<Integer>(), new Fibonacci2(), 12);
        for(int i : fnumbers)
            System.out.print(i + ", ");
        println("");
        Set<Integer> fnumbers2 = fill(new HashSet<Integer>(), new Fibonacci2(), 14);
        for(int i : fnumbers2)
            System.out.print(i + ", ");
        println("");
        Queue<Integer> fnumbers3 = fill(new PriorityQueue<Integer>(), new Fibonacci2(), 17);
        for(int i : fnumbers3)
            System.out.print(i + ", ");
        println("");
        List<Integer> fnumbers4 = fill(new LinkedList<Integer>(), new Fibonacci2(), 16); // w tej sytuacji zostanie wybrany overload LinkedList, bo przeciazenie wybiera sie na podstawie typu argumentu przekazywanego do metody fill, czyli new LinkedList<Integer>() a nie na podstawie typu zmiennej po lewej stronie przypisania (List<Integer> fnumbers4)
        for(int i : fnumbers4)
            System.out.print(i + ", ");
    }
}
/*
Odpowiadając na pytanie z zadania, tak da się zrobić overload dla List oraz LinkedList, ponieważ to różne typy parametrów metod.
O tym, który overload zostanie wybrany, decyduje typ argumentu widziany przez kompilator w miejscu wywołania.
*/