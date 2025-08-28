import static myutils.Skrocenie_Print.print;

public class zad3_4
{
public static void main(String[] args) {
	if (args.length < 2) {
		print("Za malo argumentuw, pierwszy to dystans drujgi czas, mozesz tylko pdoac dwa");
		
	}
	 if(args.length > 2) { 
      System.err.println( 
        "za durzo"); 
      System.exit(1); 
    } 

	float dystans = Float.parseFloat(args[0]);
	float czas = Float.parseFloat(args[1]);
	System.out.print("prentkosc =");
	System.out.print(dystans / czas);
	print(" km/h");
}}