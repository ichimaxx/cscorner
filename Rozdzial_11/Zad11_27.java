import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/*Exercise 27: (2) Write a class called Command that contains a String and has a
method operation( ) that displays the String. Write a second class with a method that fills
a Queue with Command objects and returns it. Pass the filled Queue to a method in a
third class that consumes the objects in the Queue and calls their operation( ) methods. */
 // pierwsza klasa
class Command {
	private String zlomi = "ARO";
	public void operation() { 
		print(zlomi);
	}
}

//drugta klasa dodajesz obiekty command do queue
class Command2 {
	Command ok = new Command();
	public Queue<Command> objeczt() {
		Queue<Command> qc = new LinkedList<>();
			qc.offer(new Command());
			qc.offer(new Command());
			qc.offer(new Command());
			qc.offer(new Command());
			qc.offer(new Command());
			return qc;	
	}
}

//pqrzerzucasz wypelniony queue do  metody ktora patrzy na ten queue i idize po petli do momentu jak nie bedzie mialo nulla (bedzie pusta) i przy okazji wywoluje operation() za kazdy obiekt ktory posiada w queue
public class Zad11_27 {
	public static void printQ(Queue<Command> queue) {
		while(queue.peek() != null){
			queue.remove().operation();
			println(" ");
		}
		System.out.println();
	} 
	public static void main(String[] args) {
		Command ok = new Command();
		ok.operation();
		println("");
		Command2 ok2 = new Command2();
		Queue<Command> qu = ok2.objeczt();		
		printQ(qu);
	}
}