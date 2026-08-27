import static myutils.Skrocenie_Print.print;
/*
Exercise 17: (2) Create a class with a constructor that takes a String argument. During
construction, print the argument. Create an array of object references to this class, but don’t
actually create objects to assign into the array. When you run the program, notice whether
the initialization messages from the constructor calls are printed.
*/
		
class Beep {
		Beep(String msg) { print("Konstruktor Rozdzial_5.Beep: " + msg); }
		}
		
public class Zad5_17 {
	public static void main(String[] args){
		Beep[] arr = new Beep[6];   // tu NIC się nie wydrukuje
		print("arr.length = " + arr.length);
		print("arr[0] = " + arr[0]); // pokaże null

	
}}