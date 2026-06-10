import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 23: (3) Implement the rest of the Map interface for SimpleHashMap.
*/
public class Zad17_23<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    private static int probes = 0;
    // You can’t have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets =
            new LinkedList[SIZE];
    public V put(K key, V value) {
        int probesForCollision = 0; // pole lokalne zliczające pojedyncze it.next dla każdej nowo dodanej kolizji
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()) {
            probesForCollision++;
            probes++;
            MapEntry<K,V> iPair = it.next();
            if(iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                println("COLLISION at bucket " + index + ": "
                        + iPair.getKey() + " and " + key + ", probes: " + probesForCollision);
                found = true;
                break;
            }
        }
        if(!found)
            buckets[index].add(pair);
        return oldValue;
    }
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null) return null;
        for(MapEntry<K,V> iPair : buckets[index])
            if(iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
        for(LinkedList<MapEntry<K,V>> bucket : buckets) {
            if(bucket == null) continue;
            for(MapEntry<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }
    public boolean containsKey(Object key) {
        boolean flag = false;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] != null) {
            for (MapEntry<K, V> k : buckets[index]) {
                //pętla po bucket z index
                if (k.getKey().equals(key))
                    //porównanie key z pętli do key z argumentu
                flag = true;
                //zmiana flagi na true
            }
        }
        return flag;
    }
    public boolean containsValue(Object value) {
        boolean flag = false;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for (MapEntry<K, V> k : buckets[i]) {
                    //przechodzący po wszystkich bucketach, bo value nie da się znaleźć przez hashCode klucza(index)
                    if (k.getValue().equals(value)) {
                        //porównanie value z pętli do value z argumentu
                        flag = true;
                        //zmiana flagi na true
                    }
                }
            }
        }

        return flag;
    }
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        //jeżeli pod tym indeksem nie ma listy, klucza nie ma na mapie (zwraca null)
        if (buckets[index] == null)
            return null;
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        // iterator do usuwania par
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                //zapis w pamięci starej wartości klucza
                V oldValue = iPair.getValue();
                it.remove();
                //metoda map.remove(key) powinna zwrócić starą wartość usunietego klucza
                return oldValue;
            }
        }
        return null;
    }
    public int size() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                z++;
            }
        }
        return z;
    }
    public boolean isEmpty() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                    z++;
            }
        }
        return (z == 0 ? true : false);
    }
    public void clear() {
        for (int i = 0; i < SIZE; i++)
            buckets[i] = null;
        //czyści mapę bucket po buckecie ustawiając je na null
    }
    public static void main(String[] args) {
        Zad17_23<String,String> m =
                new Zad17_23<String,String>();
        m.putAll(Countries.capitals(25));
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        println("\nGET \"LESOTHO\": " + m.get("LESOTHO"));
        println("\nPROBES: " + probes);
        println("ROZMIAR MAPY: " + m.size());
        System.out.println("\nCZY MAPA M ZAWIERA KEY \"LESOTHO\"?: " + m.containsKey("LESOTHO"));
        System.out.println("\nCZY MAPA M ZAWIERA VALUE \"Maseru\"?: " + m.containsValue("Maseru"));
        m.remove("LESOTHO");
        println("\nMAPA PO UZYCIU m.remove(\"LESOTHO\")\n");
        System.out.println(m);
        println("ROZMIAR PO m.remove(): " + m.size());
        m.clear();
        println("\nMAPA PO UZYCIU m.clear()\n");
        System.out.println(m);

        System.out.println("\nCZY MAPA M JEST PUSTA?: " + m.isEmpty());

        System.out.println("\nCZY MAPA M ZAWIERA KEY \"LESOTHO\"?: " + m.containsKey("LESOTHO"));
        System.out.println("\nCZY MAPA M ZAWIERA VALUE \"Maseru\"?: " + m.containsValue("Maseru"));
    }
}
