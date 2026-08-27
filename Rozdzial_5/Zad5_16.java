import static myutils.Skrocenie_Print.print;
/*
Exercise 16: (1) Create an array of String objects and assign a String to each element.
Print the array by using a for loop.
*/
public class Zad5_16 {
	public static void main(String[] args){
		String[] a = new String[6];
	
		for (int i = 0; i < a.length; i++)
		a[i] = "element" + " " + i;
		for (int i = 0; i < a.length; i++)
		print ("a[" + i + "] = " + a[i]);
}}