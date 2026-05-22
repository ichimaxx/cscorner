import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (3) Modify CountingMapData.java to fully implement the flyweight by
adding a custom EntrySet class like the one in Countries.java.
*/
class CountingMapDatas extends AbstractMap<Integer,String> {
    private static String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                    .split(" ");
    private int size;
    public CountingMapDatas(int size) {
        if(size < 0)
            this.size = 0;
        else
            this.size = size;
    }
    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }
        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }
        public Integer getKey() {
            return index;
        }
        public String getValue() {
            return
                    chars[index % chars.length] + Integer.toString(index / chars.length);
        }
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    static class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
        private int size;

        EntrySet(int size) {
            if (size < 0)
                this.size = 0;
            else
                this.size = size;
            // Rozmiar moze byc większy niż chars.length,
            // bo wartości są generowane cyklicznie: po A0 do Z0 idzie A1 do Z1 itd.
        }
        public int size() {
            return size;
        }
        private class Iter implements Iterator<Map.Entry<Integer, String>> {
            // Only one Entry object per Iterator:
            private Entry entry = new Entry(-1);
            public boolean hasNext() {
                return entry.index < size - 1;
            }
            public Map.Entry<Integer, String> next() {
                entry.index++;
                return entry;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        public Iterator<Map.Entry<Integer, String>> iterator() {
            return new Iter();
        }
    }
    public Set<Map.Entry<Integer, String>> entrySet() {
        return new EntrySet(size);
    }
    static Map<Integer, String> select(int size) {
        return new CountingMapDatas(size);
    }
    static Map<Integer,String> map = new CountingMapDatas(chars.length);
    public static Map<Integer,String> keyss() {
        // Domyślna mapa o rozmiarze chars.length
        return map;
    }
    public static Map<Integer,String> keyss(int size) {
        return select(size);
    }
    static List<Integer> liczby = new ArrayList<Integer>(map.keySet());
    public static List<Integer> sameliczby() { return liczby; }
    public static List<Integer> sameliczby(int size) {
        return new ArrayList<Integer>(select(size).keySet());
    }
}
public class Zad17_5 {
    public static void main (String[] args){
        Set<Integer> sk = new HashSet<Integer>(CountingMapDatas.sameliczby(155));
        println("\nHashSet pozycji z CountingMapDatas.sameliczby(155):\n" + sk);
        TreeMap<Integer,String> kk = new TreeMap<Integer,String>(CountingMapDatas.keyss(155));
        println("\nTreeMap pozycji z CountingMapDatas.keyss(155):\n" + kk);
    }
}
/*
CountingMapDatas zwraca teraz własny EntrySet, zamiast budować gotowy zbiór wpisów.
EntrySet używa iteratora z jednym obiektem Entry,
którego index zmienia się przy każdym wywołaniu next() - tak jak w klasie FlyweightMap.
*/