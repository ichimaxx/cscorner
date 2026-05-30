import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 13: (4) Use AssociativeArray Java to create a wordoccurrence counter,
mapping String to Integer. Using the net.mindview.util.TextFile utility in this book,
open a text file and break up the words in that file using whitespace and punctuation, and
count the occurrence of the words in that file.
*/
public class Zad17_13<K extends Comparable<K> ,V> {
    // K extends Comparable po to, aby można było użyć compareTo na K
    private Object[][] pairs;
    private int index;
    public Zad17_13(int length) {
        pairs = new Object[length][2];
    }
    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        for(int i = 0; i < index; i++) {
            K oldkey = (K) pairs[i][0]; // przechodzi przez wszystkie stare klucze z tablicy
            // pairs[i] to jedna para klucz-wartość, pairs[i][0] to klucz(key), a pairs[i][1] to wartość(value)
            if (key.compareTo(oldkey) == 0) {
                //jeżeli klucz już istnieje, aktualizuje jego wartość zamiast dodawać nową parę
                pairs[i][1] = value;
                return;
            }
        }
        if(index >= pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{ key, value };
    }
    @SuppressWarnings("unchecked")
    public V get(K key) {
        for(int i = 0; i < index; i++)
            if(key.equals(pairs[i][0]))
                return (V)pairs[i][1];
        return null; // Did not find key
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if(i < index - 1)
                result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>(new myutils.TextFile("Zad17_13.java", "\\W+"));
        Iterator<String> itt = words.iterator();
        Zad17_13<String,Integer> map = new Zad17_13<String,Integer>(words.size());
        try {
            while(itt.hasNext()) {
                String word = itt.next();
                Integer count = map.get(word);
                if (count == null) {
                    // jeżeli nie ma słowa takiego słowa w mapie, value ma być 1
                    map.put(word, 1);
                } else {
                    map.put(word, count + 1);
                    // jeżeli jest już słowo w mapie, zwiększa jego licznik o 1
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(map);
    }
}