import static myutils.Skrocenie_Print.*;
/*
Exercise 19: (2) Write a method that takes a vararg String array. Verify that you can
pass either a comma-separated list of Strings or a String[] into this method.
*/
class Zad5_19{
	public static void main(String... args){
		for(String s: args)
			print(" " + s);
println();}}