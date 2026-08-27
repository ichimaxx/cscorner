import static myutils.Skrocenie_Print.print;
/*
Exercise 18: (1) Complete the previous exercise by creating objects to attach to the array
of references.
*/
		
class Beep2 {
	String msg;
	Beep2(String msg) {
		this.msg = msg;
		print("Konstruktor Rozdzial_5.Beep: " + msg); }
		}
		
public class Zad5_18 {
	public static void main(String[] args){
		Beep2[] arr = new Beep2[6];   // tu NIC się nie wydrukuje, ale tworzytsz tablice 6 elementów :)
		print("arr.length = " + arr.length);
		print("arr[0] = " + arr[0]); // pokaże null bo jeszcze nic tu nima do tego momenciku
		for (int i = 0; i < arr.length; i++){
		arr[i] = new Beep2("obiekt " + i);}
		print("po pętli arr[0].msg = " + arr[0].msg); 
}}