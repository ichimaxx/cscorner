import java.util.*;

/*
Exercise 11: (2) Create a class that contains an Integer that is initialized to a value
between o and 100 using java.util.Random. Implement Comparable using this Integer
field. Fill a PriorityQueue with objects of your class, and extract the values using poll( ) to
show that it produces the expected order.
*/
public class Zad17_11 {
    private static Random rand = new Random();
    static class Klassa implements Comparable<Klassa> {
        //każda Klassa to pojedynczy element w queue
        private Integer x = rand.nextInt(100);
        public int compareTo(Klassa arg) {
            if(x.compareTo(arg.x) > 0)
                // jeżeli nowa liczba x jest większe od istniejącej zwraca ją później w kolejce
                return +1;
            else if(x.compareTo(arg.x) == 0)
                // jeżeli liczby x są takie same, to zwraca 0 (nie zmienia miejsca w kolejce)
                return 0;
            return -1;
        }
        public String toString() {
            return Integer.toString(x);
        }
    }
    public static void main(String[] args){
        PriorityQueue<Klassa> zz = new PriorityQueue<>();
        for (int i = 0; i < 25; i++) {
            zz.add(new Klassa());
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(zz.poll());
        }
    }
}

