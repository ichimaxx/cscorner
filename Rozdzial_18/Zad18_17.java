import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 17: (4) Using TextFile and a Map<Character, Integer>, create a program
that counts the occurrence of all the different characters in a file. (So if there are 12
occurrences of the letter 'a' in the file, the Integer associated with the Character
containing 'a' in the Map contains '12').
*/
public class Zad18_17 {
    public static void main(String[] args) {
        String words = TextFile.read("Zad18_17.java");
        Map<Character, Integer> ok = new TreeMap<>();
        for(int i = 0; i < words.length() ; i++) {
            //pobranie kolejnego znaku z tekstu pliku
            char g = words.charAt(i);
            //jeżeli mapa zawiera już character, to dodaje value + 1
            if (ok.containsKey(g)) {
                int k = ok.get(g);
                ok.put(g, k + 1);
            } else {
                ok.put(g, 1);
            }
        }
        //print mapy, która wyróżnia każdy poszczególny element, np. niewidoczny jak \n lub spacja
        //użyto do tego entrySet()
        for(Map.Entry<Character, Integer> entry : ok.entrySet()) {
            char c = entry.getKey();
            if(c == '\n')
                println("\\n = " + entry.getValue());
            else if(c == '\r')
                println("\\r = " + entry.getValue());
            else if(c == '\t')
                println("\\t = " + entry.getValue());
            else if(c == ' ')
                println("spacja = " + entry.getValue());
            else
                println(c + " = " + entry.getValue());
        }
    }
}
