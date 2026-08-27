import static myutils.Skrocenie_Print.print;
/*
Exercise 7: (1) Modify Exercise 1 so that the program exits by using the break keyword
at value 99. Try using return instead.
*/
public class Zad4_7 {
	public static void main (String[] args) {
		for (int f = 1; f <101; f++){
			if(f == 100) break;
			print(f + " ");}
	}
}
