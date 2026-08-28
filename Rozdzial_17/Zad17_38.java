import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 38: (3) Look up the HashMap class in the JDK documentation. Create a
HashMap, fill it with elements, and determine the load factor. Test the lookup speed with
this map, then attempt to increase the speed by making a new HashMap with a larger initial
capacity and copying the old map into the new one, then run your lookup speed test again on
the new map.
*/
public class Zad17_38 {
        static List<Test_2<Map<String,String>>> tests =
                new ArrayList<Test_2<Map<String,String>>>();
        static {
            tests.add(new Test_2<Map<String,String>>("get") {
                int test(Map<String,String> map, TestParam tp) {
                    int loops = tp.loops;
                    int span = tp.size * 2;
                    String[] keys = map.keySet().toArray(new String[0]);
                    for(int i = 0; i < loops; i++)
                        for(int j = 0; j < span; j++)
                            map.get(keys[j%keys.length]);
                    return loops * span;
                }
            });
        }
        public static void main(String[] args) {
            if(args.length > 0)
                Tester.defaultParams = TestParam.array(args);
            //domyślna hashmap ma InitialCapacity na 16 (2 DO POTĘGI 4)
            Map<String, String> oks = new HashMap<>();
            oks.putAll(Countries.capitals(55554));
            //ustawia capacity oker na 1024 (2 DO POTĘGI 10)
            //bo 195(size) / 0.75(load factor) = 260 (capacity)
            //więc domyślna mapa(oks), która ma InitialCapacity 16, jest zrehashowana do 512 (2 DO POTĘGI 9)
            Map<String, String> oker = new HashMap<>(1024);
            //capacity oker2 na 33554432 (2 DO POTĘGI 25)
            Map<String, String> oker2 = new HashMap<>(33554432);
            oker.putAll(oks);
            oker2.putAll(oks);
            Tester.run(oks, tests);
            Tester.run(oker, tests);
            Tester.run(oker2, tests);
        }
}

/*
Countries.capitals(55554) zwraca tylko 195 wpisów, bo ma ograniczoną liczbe danych.

Pierwsza mapa po wypełnieniu 195 elementami ma capacity 512 ponieważ przy capacity 256 * 0.75 = 192,
192 jest mniejsze niż 195 co powoduje zwiększenie tablicy o następną potęgę 2 do 512.

195/512 = 0.38(loadfactor - rzeczywiste zapełnienie bucketów)

Druga mapa została utworzona z większą initialCapacity 1024 i tymi samymi danymi.
Jej rzeczywiste zapełnienie bucketów wynosi 195 / 1024 = 0.19 co może oznaczać mniej kolizji i szybsze get().

Trzecia mapa ma bardzo dużą capacity 33554432 więc jej zapełnienie jest bardzo małe, nie oznacza to jednak
automatycznie najlepszej wydajności, bo zbyt duża liczba bucketów zwiększa zużycie pamięci.
-- HashMap --
 size     get
   10      21
  100       5
 1000       1
10000       1
-- HashMap --
 size     get
   10       1
  100       1
 1000       1
10000       1
-- HashMap --
 size     get
   10     214
  100      11
 1000       2
10000       2
*/