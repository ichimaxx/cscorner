import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/*Exercise 28: (2) Fill a PriorityQueue (using offer( )) with Double values created
using java.util.Random, then remove the elements using poll( ) and display them. */

// ZZAMIANA queue.remove() na queue.poll() jesli kolejka byla by pusta zwrocilo by null, w sytuacji remove jesli byla by pusta wyrzuci NoSuchElementException 
public class Zad11_28 {
	public static void printQ(Queue queue) {
		while(queue.peek() != null){
			println(queue.poll() + " ");
		}
		System.out.println();
	} 
	public static void main(String[] args) {
	PriorityQueue<Double> priorityQueue = new PriorityQueue<Double>();
	Random rand = new Random(47);
	for(int i = 0; i < 10; i++)
		priorityQueue.offer(rand.nextDouble() * (i + 10));
	printQ(priorityQueue); 
	}
}