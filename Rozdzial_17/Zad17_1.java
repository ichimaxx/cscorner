import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (1) Create a List (try both ArrayList and LinkedList) and fill it using
Countries. Sort the list and print it, then apply Collections.shuffle( ) to the list
repeatedly, printing it each time so that you can see how the shuffle( ) method randomizes
the list differently each time.
*/


public class Zad17_1 {
    public static void main(String[] args){
        ArrayList<String> k = new ArrayList<String>(Countries.names(15));
        LinkedList<String> z = new LinkedList<String>(Countries.names(15));
        Collections.sort(k);
        Collections.sort(z);
        println("\nArrayList names() sorted: \n" + k);
        println("\nLinkedList names() sorted: \n" + z);
        Collections.shuffle(k);
        println("\nArrayList names shuffled() 1: \n" + k);
        Collections.shuffle(k);
        println("\nArrayList names shuffled() 2: \n" + k);
        Collections.shuffle(k);
        println("\nArrayList names shuffled() 3: \n" + k);
        Collections.shuffle(z);
        println("\nLinkedList names shuffled() 1: \n" + z);
        Collections.shuffle(z);
        println("\nLinkedList names shuffled() 2: \n" + z);
        Collections.shuffle(z);
        println("\nLinkedList names shuffled() 3: \n" + z);
    }
}
// Każde wywołanie Collections.shuffle() losowo zmienia kolejność elementów w liscie.