import static myutils.Skrocenie_Print.*;
import java.util.Random;
/*
Exercise 8: (4) Following the form of the example Lunch.java, create a class called
ConnectionManager that manages a fixed array of Connection objects. The client
programmer must not be able to explicitly create Connection objects, but can only get them
via a static method in ConnectionManager. When the ConnectionManager runs out of
objects, it returns a null reference. Test the classes in main( ).
*/
 class ConnectionManager{
	private static final Random rand = new Random();
	private static int next;
	private static final Connection[] ip = new Connection[7]; // tylko tyle polaczen ustawiamy sb
	static class Connection{
		private final int ipnumber;
		
		private Connection(int ipnumber){
			this.ipnumber = ipnumber;
		}
		@Override
		public String toString(){
			return "12.34.234." + ipnumber;
		}
	}
	
	static {
		for (int i = 0; i < ip.length; i++) {
			ip[i] = new Connection(rand.nextInt(256));
		}
	}

public static Connection getConnection() {
		return (next < ip.length) ? ip[next++] : null;} //ten konstruktor daje kolejne polaczenie albo null gdey juz sie skoncza z tablicy
}
	
public class Zad6_8 { 
	public static void main(String[] args){
		ConnectionManager.Connection c;
	while ((c = ConnectionManager.getConnection()) != null) { 
			println("Got: " + c);}
			
			println("\nNo more connections.");
	}
}
  