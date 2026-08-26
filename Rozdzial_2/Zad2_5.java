/*
Exercise 5: (1) Modify the previous exercise so that the values of the data in DataOnly
are assigned to and printed in main( ).
*/
public class Zad2_5{
int i;
double d;
boolean b;

	public static void main (String[]args) {
		Zad2_5 dane = new Zad2_5();
		dane.i = 47;
		System.out.println("Dana i=" + dane.i);
		dane.d = 1.1;
		System.out.println("Dana d=" + dane.d);
		dane.b = false;
		System.out.println("Dana b=" + dane.b);
	}
}
	