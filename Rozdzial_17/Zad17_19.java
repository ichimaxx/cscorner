import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 19: (1) Repeat Exercise 13 using a SimpleHashMap.
*/
public class Zad17_19<K,V> extends AbstractMap<K,V> {
    //Rozmiar tablicy bucketów,
    //liczba pierwsza 997
    static final int SIZE = 997;

    //Tablica bucketów,
    //każdy bucket to LinkedList przechowująca pary MapEntry<K,V>.
    //Tablica nie trzyma bezpośrednio jednej party, tylko listę par,
    //bo kilka kluczy może trafić do tego samego indeksu - tak zwana collision z książki.
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value) {
        //jeżeli klucz był już w mapie, oldValue przechowuje poprzednią wartość dla tego klucza
        V oldValue = null;

        //oblicza indeks bucketa
        //key.hashCode() daje liczbę hashująca klucz,
        //Math.abs() robi z niej liczbe dodatnią,
        //a % SIZE dopasowuje ją do zakresu indeksów tablicy 0-996 (reszta z dzielenia)
        int index = Math.abs(key.hashCode()) % SIZE;

        //jeżeli pod tym indeksem nie ma jeszcze listy, tworzy nowy bucket jako LinkedList
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();

        //pobieranie bucket, listę par key=value znajdującą się pod tym indeksem
        LinkedList<MapEntry<K,V>> bucket = buckets[index];

        //tworzy nową parę key=value, którą się dodaje lub podmienia
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);

        //flaga, która mówi, że znaleziono już taki klucz w buckecie
        boolean found = false;

        //listIterator dla konkretnego bucket, aby można było po niej przechodzić
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();

        //obsługa kolizji(collision)
        while(it.hasNext()) {
            MapEntry<K,V> iPair = it.next();

            //sprawdza czy klucz z istniejącej pary jest taki sam jak klucz dodawany.
            //hashCode() tylko wskazał bucket, a equals sprawdza dokładnie klucz.
            if(iPair.getKey().equals(key)) {

                // jeżeli klucz już istnieje zapamiętuje stałą wartość
                oldValue = iPair.getValue();

                //podmiana starej pary na nową z tą samą nazwą klucza, ale z nową wartością
                it.set(pair); // Replace old with new

                //flaga na true, bo klucz został znaleziony
                found = true;
                break;
            }
        }

        //jeżeli nie znaleziono klucza w bucket, dodaje nową parę key=value na koniec listy
        if(!found)
            buckets[index].add(pair);

        //zwraca starą wartość, jeżeli klucz już istniał, albo null, jeżeli dodano nowy klucz
        return oldValue;
    }

    public V get(Object key) {
        //oblicza indeks bucketa na podstawie hashCode() szukanego klucza
        int index = Math.abs(key.hashCode()) % SIZE;

        //jeżeli bucket pod tym indeksem nie istnieje, to znaczy, że nie ma tam żadnych par więc klucza nie znaleziono
        if(buckets[index] == null) return null;

        //przechodzi po parach w buckecie, trzeba sprawdzić każdą parę, bo mogła wystąpić kolizja(collision)
        for(MapEntry<K,V> iPair : buckets[index])

            //jeżeli klucz z pary jest równy szukanemu, zwraca przypisaną do niego wartość
            if(iPair.getKey().equals(key))
                return iPair.getValue();

        //jeżeli nie znaleziono klucza, zwraca null
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
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>(new myutils.TextFile("Zad17_13.java", "\\W+"));
        Iterator<String> itt = words.iterator();
        Zad17_19<String,Integer> map = new Zad17_19<String,Integer>();
        while(itt.hasNext()) {
            String word = itt.next();
            Integer count = map.get(word);
            if (count == null) {

                // jeżeli nie ma takiego słowa w mapie, value ma być 1
                map.put(word, 1);
            } else {

                // jeżeli jest już słowo w mapie, zwiększa jego licznik o 1
                map.put(word, count + 1);
            }
        }
        println(map);
    }
}
