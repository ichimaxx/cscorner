import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (2) Create an array of Integer, fill it with random int values (using
autoboxing), and sort it into reverse order using a Comparator.
*/
public class Zad16_23 {
    public static void main(String[] args){
        Random rand = new Random();
        Integer[] in = new Integer[15];
        for (int i = 0; i < in.length; i++){
            in[i] = rand.nextInt(100);
            //rand.nextInt(100) zwraca int, ale tablica jest typu Integer[] więc java automatycznie zamienia int na Integer(Autoboxing)
            //używany jest Integer[], bo Comparator działa z obiektami, a nie z typami prymitywnymi.
        }
        println("Tablica in[]: " + Arrays.toString(in));
        Arrays.sort(in, Collections.reverseOrder()); // Collections.reverseOrder() zwraca Comparator, więc używamy Comparatora, tak jak wymaga zadanie.
        println("Reverse sort: " + Arrays.toString(in));
    }
}
