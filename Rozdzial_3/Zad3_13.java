import static myutils.Skrocenie_Print.print;
import java.lang.*;
/*
Exercise 13: (1) Write a method that displays char values in binary form. Demonstrate
it using several different characters.
*/
public class Zad3_13 {
	public static void main(String[] args){
		char c = Character.MAX_VALUE;
		print("maks value =" + Integer.toBinaryString(c));
		print("J = " + Integer.toBinaryString('J'));
		print("D = " + Integer.toBinaryString('D'));
		print("? = " + Integer.toBinaryString('?'));
}}