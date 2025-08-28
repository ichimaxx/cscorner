import java.util.*;
import static myutils.Skrocenie_Print.print;

public class zad4_9 {
static long fib(long c){
	if (c <= 2)
		return 1;
return fib(c-1) + fib(c-2);}
public static void main(String[] args){
	long n = 10;
	if (args.length > 0) {
	n = Integer.parseInt(args[0]);}
	for (long i = 1; i <= n; i++)
		print(fib(i) + ", ");
}}
	
		
		