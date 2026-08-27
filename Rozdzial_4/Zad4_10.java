import java.util.*;
import static myutils.Skrocenie_Print.print;
/*
Exercise 10: (5) A vampire number has an even number of digits and is formed by
multiplying a pair of numbers containing half the number of digits of the result. The digits
are taken from the original number in any order. Pairs of trailing zeroes are not allowed.
Examples include:
1260 = 21 * 60
1827 = 21 * 87
2187 = 27 * 81
Write a program that finds all the 4-digit vampire numbers. (Suggested by Dan Forhan.)
*/
public class Zad4_10 {
	static long[] fangs(long n) {
		String digits = Long.toString(n);
		int len = digits.length();
		if (len % 2 != 0) return null; // sprawdza czy jest parzysta liczba
		
		int k = len / 2; // dlugosc jednego "kla"
		long start = (long) Math.pow(10, k-1); // najmniejsza kcyfrowa liczba (10^(k-1))
		long end = (long) Math.pow(10, k) -1; // najwieksza kcyfrowa liczba (10^k -1)
		
		char[] sortedN = digits.toCharArray();
		Arrays.sort(sortedN); // posortowany zbiór liczb takiej liczby wampirzej
		
		for (long a = start; a <= end; a++) {
			if (n % a != 0) continue; // a nie dzieli tutaj n
			long b = n / a;
			if (b < start || b > end) continue; // b nie ma k cyfr
			if (a % 10 == 0 && b % 10 == 0) continue; // oba kły nie mogą konczyc sie zerem wiec znowu skip
	
			char[] fangstablica = (Long.toString(a) + b).toCharArray();
			Arrays.sort(fangstablica);
			if (Arrays.equals(sortedN, fangstablica)) // jesli cyfry z liczby wampirzej i z liczby z petli do siebie pasuja
			return new long[]{a, b}; // zwraca liczby ktr sa odpowiedzialne za wampirzed liczby
		}
	return null;
	}
	public static void main(String[] args){
		for (long i = 1; i <= 9999; i++){
			long[] kly = fangs(i);
		if (kly != null){
			print(i + " jest wapirzycą a jej mnozenie to " + kly[0] + "*" + kly[1]);
}}}}