import java.util.*;
import static myutils.Skrocenie_Print.*;


public class Zad11_5 {

	public static void main(String[] args) {
		Random rand = new Random(47);
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			nums.add(i);
		}
		println("1: " + nums);
		Integer h = 2;
		nums.add(h); // Automatically resizes
		println("2: " + nums);
		println("3: " + nums.contains(h));
		nums.remove(h); // Remove by object
		Integer p = nums.get(2);
		println("4: " + p + " " + nums.indexOf(p));
		Integer cymrik = 999; // 
		nums.add(cymrik); // zeby wyswietlilo indeks tego numeru trzeba go najpierw dodac do listy, wtedy na output jest ze jest 10 w kolejnosci
		println("5: " + nums.indexOf(cymrik));
		println("6: " + nums.remove(cymrik));
		// Must be the exact object:
		println("7: " + nums.remove(p));
		println("8: " + nums);
		nums.add(3, 111); // Insert at an index
		println("9: " + nums);
		List<Integer> sub = nums.subList(1, 4);
		println("subList: " + sub);
		println("10: " + nums.containsAll(sub));
		Collections.sort(sub); // In-place sort
		println("sorted subList: " + sub);
		// Order is not important in containsAll():
		println("11: " + nums.containsAll(sub));
		Collections.shuffle(sub, rand); // Mix it up
		println("shuffled subList: " + sub);
		println("12: " + nums.containsAll(sub));
		List<Integer> copy = new ArrayList<Integer>(nums);
		sub = Arrays.asList(nums.get(1), nums.get(4));
		println("sub: " + sub);
		copy.retainAll(sub);
		println("13: " + copy);
		copy = new ArrayList<Integer>(nums); // Get a fresh copy
		copy.remove(2); // Remove by index
		println("14: " + copy);
		copy.removeAll(sub); // Only removes exact objects
		println("15: " + copy);
		copy.set(1, 111); // Replace an element
		println("16: " + copy);
		copy.addAll(2, sub); // Insert a list in the middle
		println("17: " + copy);
		println("18: " + nums.isEmpty());
		nums.clear(); // Remove all elements
		println("19: " + nums);
		println("20: " + nums.isEmpty());
		nums.addAll(new ArrayList<Integer>());
		for (int i = 0; i < 10; i++) {
			nums.add(i);
		}
		println("21: " + nums);
		Object[] o = nums.toArray();
		println("22: " + o[3]);
		Integer[] pa = nums.toArray(new Integer[0]);
		println("23: " + pa[7]);
	}
}