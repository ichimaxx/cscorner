/*
Exercise 32: (1) Verify that FixedSizeStack in GenericCast.java(Zad15_32) generates
exceptions if you try to go out of its bounds. Does this mean that bounds-checking code is not
required?
*/
class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;
    public FixedSizeStack(int size) {
        storage = new Object[size];
    }
    public void push(T item) { storage[index++] = item; }
    @SuppressWarnings("unchecked")
    public T pop() { return (T)storage[--index]; }
}
public class Zad15_32 {
    public static final int SIZE = 10;
    public static void main(String[] args) {
        FixedSizeStack<String> strings =
                new FixedSizeStack<String>(SIZE);
        for(String s : "A B C D E F G H I J" .split(" ")) // jak dodamy dodatkowy znak 11 do stosu to dostaniemy wyjątek java.lang.ArrayIndexOutOfBoundsException
            strings.push(s);
        for(int i = 0; i < SIZE + 1; i++) { // dodano +1 do rozmiaru stosu : java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 10
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}

/*
program stworzy exceptions z powodu błędnego użycia stosów (przysłowiowe wyjście po za granice z treści zadania). Nie znaczy to jednak ze bounds-checking code jest niepotrzebny.
Wyjątek pojawia się dopiero po błędnej operacji, a index zostaje wcześniej zmieniony wiec obiekt może zostać w niepoprawnym stanie. Z tego powodu kontrola granic jest wymagana i w kodzie powinno się znaleźć np:
public void push(T item) {
    if(index >= storage.length)
        throw new IllegalStateException ("Stack full");
    storage[index++] = item;
}
public T pop() {
    if(index <= 0)
        throw new IllegalStateException("Stack empty");
    return (T)storage[--index];
}
 */