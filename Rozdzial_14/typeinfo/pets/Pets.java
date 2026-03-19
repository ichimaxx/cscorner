//: typeinfo/pets/Pets.java
// Facade to produce a default PetCreator.
package Rozdzial_14.typeinfo.pets;
import java.util.*;

public class Pets {
  public static final PetCreator creator = new PetCreator2(); // podmienione LiteralPetCreator na PetCreator2 dla dzialania Zad14_15.java
  public static Pet randomPet() {
    return creator.randomPet();
  }
  public static Pet[] createArray(int size) {
    return creator.createArray(size);
  }
  public static ArrayList<Pet> arrayList(int size) {
    return creator.arrayList(size);
  }
} ///:~
