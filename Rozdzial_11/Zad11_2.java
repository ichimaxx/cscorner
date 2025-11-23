import java.util.*;
import static myutils.Skrocenie_Print.*;

public class Zad11_2 {
	public static void main(String[] args) {
	Set<Integer> c = new HashSet<Integer>(); // do metody Set moge uzyc HashSet a nie ArrayList...
		for(int i = 0; i < 10; i++)
			c.add(i); // Autoboxing
		for(Integer i : c)
			System.out.print(i + ", ");
 
	}
}