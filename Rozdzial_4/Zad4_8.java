import static myutils.Skrocenie_Print.print;
/*
Exercise 8: (2) Create a switch statement that prints a message for each case, and put
the switch inside a for loop that tries each case. Put a break after each case and test it,
then remove the breaks and see what happens.
*/
public class Zad4_8 {
	public static void main (String[] args) {
		for (int i = 0; i < 6; i++){
			int c = 'a' + i;
			switch(c){
				case 'a': print("case1"); //break;
				case 'b': print("case2"); //break;
				case 'c': print("case3"); //break;
				case 'd': print("case4"); //break;
				case 'e': print("case5"); //break;
				default: print("default"); //break;
			}
		}
	}
}