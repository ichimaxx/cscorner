import static myutils.Skrocenie_Print.*;
/*
Exercise 20: (1) Create a main( ) that uses varargs instead of the ordinary main( )
syntax. Print all the elements in the resulting args array. Test it with various numbers of
command-line arguments.
*/
class Zad5_20{
	public static void main(String... args){
		for(String s: args)
			print(" " + s);
println();
println();
print("args.length =" + args.length);
}}