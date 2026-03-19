package Rozdzial_14.typeinfo.pets;
import java.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 15: (4) Implement a new PetCreator using Registered Factories, and modify
the Pets Facade so that it uses this one instead of the other two. Ensure that the rest of the
examples that use Pets .Java still work correctly. 
*/


public class PetCreator2 extends PetCreator {
	@SuppressWarnings("unchecked")
	static List<Factory1<? extends Pet>> partPets = new ArrayList<Factory1<? extends Pet>>();	//tworzymy liste fabryk dla Pets
	static {
		// Collections.addAll() gives an "unchecked generic
		// array creation ... for varargs parameter" warning.
		partPets.add(new Mutt.Factory1());
		partPets.add(new Pug.Factory1());
		partPets.add(new EgyptianMau.Factory1());
		partPets.add(new Manx.Factory1());
		partPets.add(new Cymric.Factory1());
		partPets.add(new Rat.Factory1());
		partPets.add(new Mouse.Factory1());
		partPets.add(new Hamster.Factory1());
		partPets.add(new Gerbil.Factory1());
		// Types for random creation:
	}
	private static final List<Class<? extends Pet>> types = LiteralPetCreator.allTypes.subList(LiteralPetCreator.allTypes.indexOf(Mutt.class), LiteralPetCreator.allTypes.size());
	public List<Class<? extends Pet>> types() {
		return types;
	}	
	private static Random rand = new Random();
	@Override
	public Pet randomPet() { // tworzy random peta z listy fabryk ktora stworzylismy, sytuacja jak w zadaniui poprzednim uzylismy metody z RegisteredFactories i przerobione zostaly klasy, dodano do nich wewnetrzne klasy fabryk ktore implementuja interfejs Factory1
		int n = rand.nextInt(partPets.size());
		println("PetCreator swapped with PetCreator2");
		return partPets.get(n).create();
	}	
	public static void main(String[] args) {
		System.out.println(types);
	} 
} ///:~
