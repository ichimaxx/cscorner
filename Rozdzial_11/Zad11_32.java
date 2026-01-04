import pets.*;
import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 32: (2) Following the example of MultilterableClass, add reversed( ) and
randomized( ) methods to NonCollectionSequence.java, as well as making
NonCollectionSequence implement Iterable, and show that all the approaches work in
foreach statements. */
class InterfaceVsIterator {
	public static void display(Iterator<Pet> it) {
		while(it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ":" + p + " ");
		}
		System.out.println();
	}	
	public static void display(Collection<Pet> pets) {
		for(Pet p : pets)
			System.out.print(p.id() + ":" + p + " ");
		System.out.println();
	}
}	

public class Zad11_32 implements Iterable<Pet> {
	protected Pet[] pets = Pets.createArray(8);
	public Iterable<Pet> reversed() {
		return new Iterable<Pet>() {
			public Iterator<Pet> iterator() {
				return new Iterator<Pet>() {
					int current = pets.length - 1;
					public boolean hasNext() { return current > -1; }
					public Pet next() { return pets[current--]; }
					public void remove() { // Not implemented
						throw new UnsupportedOperationException();
					}
				};
			}
		}; 
	}
	// dodalem tylko randomized i reversed oraz przerobilem leciutko wykly iterator() zeby to robilo, no i zaimplementowalem Iterable<Pet>
	public Iterable<Pet> randomized() {
		return new Iterable<Pet>() {
			public Iterator<Pet> iterator() {
				List<Pet> shuffled = new ArrayList<Pet>(Arrays.asList(pets));
				Collections.shuffle(shuffled, new Random());
				return shuffled.iterator();
			}
		};
	}
	public Iterator<Pet> iterator() {
		return new Iterator<Pet>() {
					private int index = 0;
					public boolean hasNext() {
						return index < pets.length;
					}
					public Pet next() { return pets[index++]; }
					public void remove() { // Not implemented
						throw new UnsupportedOperationException();
					}
				};
	}
	public static void main(String[] args) {
		Zad11_32 nc = new Zad11_32();
		print("odwrocony array: "); 
		for(Pet f : nc.reversed())
			print(f + " ");
		println();
		print("randomowe wartosci array: "); 
		for(Pet f : nc.randomized())
			print(f + " ");
		println();
		print("regular array: "); 
		for(Pet f : nc)
			print(f + " ");
		println();
	}
}