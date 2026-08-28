import pets.*;
import pets.Hamster;
import pets.Cymric;
import pets.Pet;
import pets.Pets;
import java.util.*;
import pets.Mouse;
import static myutils.Skrocenie_Print.*;

public class ListFeatures {
 public static void main(String[] args) {
 Random rand = new Random(47);
 List<Pet> pets = Pets.arrayList(7);
 println("1: " + pets);
 Hamster h = new Hamster();
 pets.add(h); // Automatically resizes
 println("2: " + pets);
 println("3: " + pets.contains(h));
 pets.remove(h); // Remove by object
 Pet p = pets.get(2);
 println("4: " + p + " " + pets.indexOf(p));
 Pet cymric = new Cymric();
 println("5: " + pets.indexOf(cymric));
 println("6: " + pets.remove(cymric));
 // Must be the exact object:
 println("7: " + pets.remove(p));
 println("8: " + pets);
 pets.add(3, new Mouse()); // Insert at an index
 println("9: " + pets);
 List<Pet> sub = pets.subList(1, 4);
 println("subList: " + sub);
 println("10: " + pets.containsAll(sub));
 Collections.sort(sub); // In-place sort
 println("sorted subList: " + sub);
 // Order is not important in containsAll():
 println("11: " + pets.containsAll(sub));
 Collections.shuffle(sub, rand); // Mix it up
 println("shuffled subList: " + sub);
 println("12: " + pets.containsAll(sub));
 List<Pet> copy = new ArrayList<Pet>(pets);
 sub = Arrays.asList(pets.get(1), pets.get(4));
 println("sub: " + sub);
 copy.retainAll(sub);
 println("13: " + copy);
 copy = new ArrayList<Pet>(pets); // Get a fresh copy
 copy.remove(2); // Remove by index
 println("14: " + copy);
 copy.removeAll(sub); // Only removes exact objects
 println("15: " + copy);
 copy.set(1, new Mouse()); // Replace an element
 println("16: " + copy);
 copy.addAll(2, sub); // Insert a list in the middle
 println("17: " + copy);
 println("18: " + pets.isEmpty());
 pets.clear(); // Remove all elements
 println("19: " + pets);
 println("20: " + pets.isEmpty());
 pets.addAll(Pets.arrayList(4));
 println("21: " + pets);
 Object[] o = pets.toArray();
 println("22: " + o[3]);
 Pet[] pa = pets.toArray(new Pet[0]);
 println("23: " + pa[3].id());
 }
}