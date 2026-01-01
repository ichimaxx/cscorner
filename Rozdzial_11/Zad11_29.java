import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;

/*Exercise 29: (2) Create a simple class that inherits from Object and contains no
members, and show that you cannot successfully add multiple elements of that class to a
PriorityQueue. This issue will be fully explained in the Containers in Depth chapter. */

class Supciok {}

public class Zad11_29 {
	public static void printQ(Queue queue) {
		while(queue.peek() != null){
			println(queue.poll() + " ");
		}
		System.out.println();
	} 
	public static void main(String[] args) {
		Supciok ok = new Supciok();
		PriorityQueue<Supciok> qc = new PriorityQueue<>();
		qc.offer(new Supciok());
		qc.offer(new Supciok());
		qc.offer(new Supciok());
		qc.offer(new Supciok());
		qc.offer(new Supciok());
		printQ(qc);
	}
}

/* program sie uruchomi ale nie dojdzie do konca bo jest to co zadanie chcialo czyli próba dodania kilku elementów typu pusta klasa do priorityqueue czyli wywali sie przy drugim qc.offer(new Supciok());
C:\Users\ichim\Desktop\cscorner\Rozdzial_11>java Zad11_29
Exception in thread "main" java.lang.ClassCastException: class Supciok cannot be cast to class java.lang.Comparable (Supciok is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
        at java.base/java.util.PriorityQueue.siftUpComparable(PriorityQueue.java:643)
        at java.base/java.util.PriorityQueue.siftUp(PriorityQueue.java:639)
        at java.base/java.util.PriorityQueue.offer(PriorityQueue.java:330)
        at Zad11_29.main(Zad11_29.java:21) */
