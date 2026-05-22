import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 2: (2) Produce a Map and a Set containing all the countries that begin with ‘A’.
*/
public class Zad17_2 {
    public static void main(String[] args){
        TreeMap<String,String> kk = new TreeMap<String,String>(Countries.capitals());
        TreeMap<String,String> kz = new TreeMap<String,String>();
        String regex = "^[Aa].*"; // regex sprwadzający, czy nazwa kraju zaczyna się od A albo a
        for(String key: kk.keySet()){
            if(key.matches(regex)) // jeśli key pasuje do regexa, dodajemy tą parę key-value do nowej mapy kz
                kz.put(key,kk.get(key));
        }
        println(kz);
        HashSet<String> sk = new HashSet<String>(Countries.names());
        HashSet<String> sz = new HashSet<String>();
            for(String keys: sk) {
                if(keys.matches(regex))
                    sz.add(keys);
        }
        println(sz);
    }
}
