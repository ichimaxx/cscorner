import static myutils.Skrocenie_Print.print;
/*
Exercise 6: (2) Modify the two test( ) methods in the previous two programs so that
they take two extra arguments, begin and end, and so that testval is tested to see if it is
within the range between (and including) begin and end.
*/
public class Zad4_6{
	
	static boolean test(int testval, int begin, int end) {
	boolean result = false; // zakladanie ze jest true
		if (testval >= begin && testval <= end)
			result = true;
		return result;
	}
	static boolean test2(int testval, int begin, int end) {
		boolean result2 = false; // zakladanie ze jest od startu true
		if (testval >= begin && testval <= end)
			result2 = true;
		return result2;
	}
		
	public static void main(String[] args) {
		print(test2(10, 5, 15));
		print(test2(5, 10, 15));
		print(test2(5, 5, 15));
		print(test(10, 5, 15));
		print(test(5, 10, 15));
		print(test(5, 5, 15));
}}
	