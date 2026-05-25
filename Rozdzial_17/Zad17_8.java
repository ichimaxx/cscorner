import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 8: (7) Create a generic, singly linked list class called SList, which, to keep
things simple, does not implement the List interface. Each Link object in the list should
contain a reference to the next element in the list, but not the previous one (LinkedList, in
contrast, is a doubly linked list, which means it maintains links in both directions). Create
your own SListIterator which, again for simplicity, does not implement ListIterator. The
only method in SList other than toString( ) should be iterator( ), which produces an
SListIterator. The only way to insert and remove elements from an SList is through
SListIterator. Write code to demonstrate SList.
*/
class Zad17_8<T> {
    private Link first; // zmienna przechowująca referencje do pierwszego element/node listy
    class Link { // każdy pojedynczy element/node listy
        T item; // wartość przechowywana w tym elemencie/node
        Link next; // referencja do następnego elementu/node
    }
    class SListIterator {
        public void dodaj(T item) {
            // w sytuacji dodawania nowego obiektu, trzeba stworzyć nowy obiekt Link,
            // ponieważ class Link reprezentuje pojedynczy element listy
            Link nowy = new Link();
            nowy.item = item; // zapisuje wartość przekazaną do metody w nowym elemencie/node
            nowy.next = null; // nowy element będzie ostatni, więc jego next wskazuje na null
            if(first == null) {
                first = nowy; // jeżeli lista jest pusta, nowy węzeł staje się pierwszym elementem/node
            } else { // jeżeli lista nie jest pusta, szuka ostatniego elementu
                Link current = first;
                while(current.next != null) {
                    // idzie po elementach aż current będzie ostatnim elementem, czyli current.next == null
                    current = current.next; // przesuwa current na kolejny element listy
                }
                current.next = nowy; // ostatni element zaczyna wskazywać na nowy, następny element
            }
        }
        public void remove() {
            if (first == null) { // jeśli lista jest pusta nic nie usunie
                return;
            }
            if(first.next == null) { // jeśli lista ma tylko jeden element,
                first = null; // zmienna first przestaje wskazywać na obiekt, lista staje się pusta
                return;
            }
            Link current = first;
            while(current.next.next != null) { // idzie do przedostatniego elementu listy
                current = current.next; // przesuwa current na kolejny element/node
            }
            current.next = null; // przedostatni element przestaje wskazywać na ostatni
            // więc ostatni zostaje usunięty z listy
        }
    }
    public SListIterator iterator() {
        return new SListIterator();
    }
    public String toString() {
        // buduje tekstową reprezentację listy
        StringBuilder sb  = new StringBuilder();
        Link current = first;

        while(current != null) { // idzie po kolei po obiektach aż natrafi na null
            sb.append(current.item).append(" "); // dodaje wartość aktualnego elementu do StringBuilder
            current = current.next; // pozwala na przechodzenie po liście podmieniając current na current.next
        }
        return sb.toString(); // zwraca gotowy tekst z elementami listy
    }
    public static void main(String[] args){
        Zad17_8<String> zz = new Zad17_8<>();
        Zad17_8<String>.SListIterator it = zz.iterator();
        it.dodaj("Hello");
        it.dodaj("Czesc");
        println(zz);
        it.remove();
        println(zz);
    }
}

/*
Iterator dodaje nowy node na koniec listy.
Przy usuwaniu przechodzi do przedostatniego elementu/node i ustawia jego next na null,
dzięki czemu ostatni node zostaje odłączony od listy.
*/