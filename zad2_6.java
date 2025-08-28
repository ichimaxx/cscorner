public class zad2_6
{
String s = "ojapierdoleokssaokesoakgkao";
int dupa(String s){
	return s.length()*2;} // wyrzuca ilosc znakow w stringu ojapier.... pomnozony przez dwa

	void print(){
	System.out.println("ilosc znakow w stringu s razy dwa = " + dupa(s));}
	public static void main (String[]args) {
	zad2_6 dane = new zad2_6();
	dane.print();
	}
	}