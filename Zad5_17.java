import static myutils.Skrocenie_Print.print;

		
class Beep {
		Beep(String msg) { print("Konstruktor Beep: " + msg); }
		}
		
public class Zad5_17 {
	public static void main(String[] args){
		Beep[] arr = new Beep[6];   // tu NIC się nie wydrukuje
		print("arr.length = " + arr.length);
		print("arr[0] = " + arr[0]); // pokaże null

	
}}