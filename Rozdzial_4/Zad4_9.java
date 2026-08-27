import static myutils.Skrocenie_Print.print;
/*
Exercise 9: (4) A Fibonacci sequence is the sequence of numbers 1, 1, 2, 3, 5, 8, 13, 21,
34, and so on, where each number (from the third on) is the sum of the previous two. Create
a method that takes an integer as an argument and displays that many Fibonacci numbers
starting from the beginning, e.g., If you run java Fibonacci 5 (where Fibonacci is the
name of the class) the output will be: 1, 1, 2, 3, 5.
*/
public class Zad4_9 {
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
	
		
		