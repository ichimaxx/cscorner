
/*
Exercise 5: (2) Remove the type parameter on the Node class and modify the rest of the
        code in LinkedStack.java (Zad15_5) to show that an inner class has access to the generic type
        parameters of its outer class.
*/

public class Zad15_5<T> {
    private class Node { // kiedy klasa zewnetrzna ma podany typ generyczny to klasa wewnetrzna nie musi juz go podawac drugi raz bo ma dostęp do parametrów zewnętrznej
        T item; // uzyty parametr generyczny (generic) z klasy zewnętrznej
        Node next;
        Node() { item = null; next = null; }
        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
        boolean end() { return item == null && next == null; }
    }
    private Node top = new Node(); // End sentinel
    public void push(T item) {
        top = new Node(item, top);
    }
    public T pop() {
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }
    public static void main(String[] args) {
        Zad15_5<String> lss = new Zad15_5<String>();
        for(String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
        while((s = lss.pop()) != null)
            System.out.println(s);
    }
}