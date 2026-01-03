//: holding/CollectionSequence.java
import pets.*;
import java.util.*;

/*Exercise 30: (5) Modify CollectionSequence.java so that it does not inherit from
AbstractCollection, but instead implements Collection. */

public class Zad11_30 implements Collection<Pet> {
	public static void display(Iterator<Pet> it) {
		while(it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ":" + p + " ");
		}
		System.out.println();
	}
	// wazna informacja, jak skipujemy metody zeby ich nie trzeba bylo dodawac jak implementujemy interfejs to trzeba dodawac albo interfejs generyczny i wtedy metody tez generyczne(implements Collection<Pet>) albo raw(implements Collection) i wtedy skipujemy metody raw
	
	// te metody mozna znalezc w dokumentacji javy w interfejsie Collection za pomoca UnsupportedOperationException je skipujemy i nie musimy ich wypisywac calych
	public boolean retainAll(Collection<?> c) { 
		throw new UnsupportedOperationException();
	} 	
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	} 	
	public boolean addAll (Collection<? extends Pet> c) { // to wszystko jest w dokumentacji interfejsu collection, wystarczy sprawdzic
		throw new UnsupportedOperationException();
	} 	
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	} 	
	public boolean remove(Object o) { 
		throw new UnsupportedOperationException();
	}
	public boolean add(Pet e) { // musi byc Pet bo generic klasa jest Collection<Pet>
		throw new UnsupportedOperationException();
	}
	public <T> T[] toArray(T[] a) { 
		throw new UnsupportedOperationException();
	}
	public Object[] toArray() {
		throw new UnsupportedOperationException();	
	}
	public boolean contains(Object o) { 
		throw new UnsupportedOperationException();
	}
	public boolean isEmpty() { 
		throw new UnsupportedOperationException();
	}
	public void clear() { 
		throw new UnsupportedOperationException();
	}
	public static void display(Collection<Pet> pets) {
		for(Pet p : pets)
		System.out.print(p.id() + ":" + p + " ");
		
		System.out.println();
	}

	private Pet[] pets = Pets.createArray(8);
	public int size() {
		return pets.length;
	}
	public Iterator<Pet> iterator() {
		return new Iterator<Pet>() {
			private int index = 0;
			public boolean hasNext() {
				return index < pets.length;
			}
			public void remove() { // Not implemented
				throw new UnsupportedOperationException();
			} 
			public Pet next() { 
				return pets[index++]; 
			}
		};
	}
	public static void main(String[] args) {
		Zad11_30 c = new Zad11_30();
		display(c);
		display(c.iterator());
	}
}

// WAŻNE: ta klasa dizala tylko do odczytu i iteracji nic za bardzo tu nie zrobisz dodatkowego np nie uzyjesz metody clear() albo innych z collection bo je po prostu skipnelismy