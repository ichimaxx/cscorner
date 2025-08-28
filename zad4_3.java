import java.util.*;
import static myutils.Skrocenie_Print.print;
import java.util.concurrent.ThreadLocalRandom;

public class zad4_3 {

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
		print("losowanko...... " + para[0] + " oraz " + para[1] + "\n obie ruwne ");
}
public static void main (String[] args){
	while(1>0)
	test();
}}
