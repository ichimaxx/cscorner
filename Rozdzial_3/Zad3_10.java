import static myutils.Skrocenie_Print.print;
/*
Exercise 10: (3) Write a program with two constant values, one with alternating binary
ones and zeroes, with a zero in the least-significant digit, and the second, also alternating,
with a one in the least-significant digit (hint: It’s easiest to use hexadecimal constants for
this). Take these two values and combine them in all possible ways using the bitwise
operators, and display the results using Integer.toBinaryString( ).
*/

public class Zad3_10 {
	static int l1 = 0xaaaaaa;
	static int  l2 = 0x555555;
public static void main (String[] args){
	
	print("l1 =" +  Integer.toBinaryString(l1));
	print("l2 =" +  Integer.toBinaryString(l2));
	print("l1& =" + Integer.toBinaryString(l1 & l1));
	print("l1| =" + Integer.toBinaryString(l1 | l1));
	print("l1~ =" + Integer.toBinaryString(~l1));
	print("l1^ =" + Integer.toBinaryString(l1 ^ l1));
	print("l2& =" + Integer.toBinaryString(l2 & l2));
	print("l2| =" + Integer.toBinaryString(l2 | l2));
	print("l2~ =" + Integer.toBinaryString(~l2));
	print("l2^ =" + Integer.toBinaryString(l2 ^ l2));
}}