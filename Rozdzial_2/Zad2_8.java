/*
Exercise 8: (3) Write a program that demonstrates that, no matter how many objects
you create of a particular class, there is only one instance of a particular static field in that
class.
*/

public class Zad2_8
{
static int i = 47;
public static void main (String[]args) {
	Zad2_8 heh = new Zad2_8();
	Zad2_8 lol = new Zad2_8();
	System.out.println(lol.i + " == " + heh.i);
	System.out.println("po inkrementacji jednego z ");
	lol.i++;
	System.out.println(lol.i + " == " + heh.i);
	System.out.println("oba w ch sa rowne, xdd");
	}
	}