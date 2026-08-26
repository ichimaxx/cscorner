/*
Exercise 6: (2) Write a program that includes and calls the storage( ) method defined
as a code fragment in this chapter.

*/
public class Zad2_6 {
	String s = "ojapieeokssaokesoakgkao";
	int dupa(String s){
	return s.length()*2;} // wyrzuca ilosc znakow w stringu ojapie.... pomnozony przez dwa

	void print(){
	System.out.println("ilosc znakow w stringu s razy dwa = " + dupa(s));}
	public static void main (String[]args) {
		Zad2_6 dane = new Zad2_6();
		dane.print();
	}
}