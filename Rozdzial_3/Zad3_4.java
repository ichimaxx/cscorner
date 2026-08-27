import static myutils.Skrocenie_Print.print;

/*
Exercise 4: (2) Write a program that calculates velocity using a constant distance and a
constant time.
*/

public class Zad3_4
{
public static void main(String[] args) {
	if (args.length < 2) {
		print("Za malo argumentów, pierwszy to dystans drugi czas, mozesz tylko podac dwa");
		
	}
	 if(args.length > 2) { 
      System.err.println( 
        "za duzo");
      System.exit(1); 
    } 

	float dystans = Float.parseFloat(args[0]);
	float czas = Float.parseFloat(args[1]);
	System.out.print("prędkosc =");
	System.out.print(dystans / czas);
	print(" km/h");
}}