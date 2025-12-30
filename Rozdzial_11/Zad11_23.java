import java.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 23: (4) Starting with Statistics.java, create a program that runs the test
repeatedly and looks to see if any one number tends to appear more than the others in the
results. */

public class Zad11_23 {
	public static void main(String[] args) {
		Random rand = new Random();
		Map<Integer,Integer> winners = new HashMap<>();
		for(int k = 0; k < 400; k++) {
			Map<Integer,Integer> m = new HashMap<>();
			for(int i = 0; i < 10000; i++) {
				// Produce a number between 0 and 20:
				int r = rand.nextInt(20);
				Integer freq = m.get(r);
				m.put(r, freq == null ? 1 : freq + 1);
			}
			int max = Collections.max(m.values());
			//wybiera jedna z liczb ktora w mapie m byla najwieksza i wrzuca ja do nowej mapy winners, i tak w kolko 400 razy az wyhjdzie mapa w ktorej bedzie wypisane ktora z liczb wychodzila najczesciej no chyba ze jest kilka z ta sama wartoscia to wtedy doda je wszasyskie
			for (Map.Entry<Integer,Integer> e : m.entrySet()) {
				if (e.getValue() == max) {
					winners.put(e.getKey(), winners.getOrDefault(e.getKey(), 0 ) + 1);
				}
			}	
		}
	
		
		println(new TreeMap<>(winners));
	}
}