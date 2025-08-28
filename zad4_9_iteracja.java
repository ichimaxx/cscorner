import java.util.*;
import static myutils.Skrocenie_Print.print;

public class zad4_9_iteracja {
static long fib(long n) {
    long a = 1, b = 1;           // F(1), F(2)
    for (long i = 3; i <= n; i++) {
        long next = a + b;       // F(i)
        a = b; b = next;
}
return (n <= 2) ? 1 : b;}
public static void main(String[] args){
	long n = 10;
	if (args.length > 0) {
	n = Integer.parseInt(args[0]);}
	for (long i = 1; i <= n; i++)
		print(fib(i) + (i < n ? ", " : "\n"));
}}
	
		
		