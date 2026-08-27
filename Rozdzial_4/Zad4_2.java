import static myutils.Skrocenie_Print.print;
import java.util.concurrent.ThreadLocalRandom;
/*
Exercise 2: (2) Write a program that generates 25 random int values. For each value,
use an if-else statement to classify it as greater than, less than, or equal to a second
randomly generated value.
*/
public class Zad4_2 {

	static int[] losowanko() {
		return new int[]{
		ThreadLocalRandom.current().nextInt(1,256),
		ThreadLocalRandom.current().nextInt(1,256)
	};
	}
public static void test(){
	int[] para = losowanko();
	if (para[1] < para[0])
		print("losowanko...... " + para[0] + " oraz " + para[1]  + "\n pierwsza wartosc wieksza, czyli numer " + para[0]);
	else if (para[1] > para[0])
		print("losowanko...... " + para[0] + " oraz " + para[1] + "\n druga wartosc wieksza, czyli numer " + para[1]);
	else 
		print("losowanko...... " + para[0] + " oraz " + para[1] + "\n obie równe ");
	}
	public static void main (String[] args) {
		for (int f = 0; f <25; f++)
	test();
}}
