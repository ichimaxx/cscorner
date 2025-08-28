import static myutils.Skrocenie_Print.print;

		
class Beep {
	String msg;
		Beep(String msg) { 
		this.msg = msg;
		print("Konstruktor Beep: " + msg); }
		}
		
public class Zad5_18 {
	public static void main(String[] args){
		Beep[] arr = new Beep[6];   // tu NIC się nie wydrukuje, ale tworzytsz tablice 6 elementów :)
		print("arr.length = " + arr.length);
		print("arr[0] = " + arr[0]); // pokaże null bo jeszcze nic tu nima do tego momenciku
		for (int i = 0; i < arr.length; i++){
		arr[i] = new Beep("obiekt " + i);}
		print("po pętli arr[0].msg = " + arr[0].msg); 
}}