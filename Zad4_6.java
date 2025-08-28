import static myutils.Skrocenie_Print.print;

public class Zad4_6{
	
	static boolean test(int testval, int begin, int end) {
	boolean result = false; // zakladanie ze jest tru
		if (testval >= begin && testval <= end)
			result = true;
		return result;
	}
	static boolean test2(int testval, int begin, int end) {
		boolean result2 = false; // zakladanie ze jest od startu tru
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
	