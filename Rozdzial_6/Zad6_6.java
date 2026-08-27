import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Create a class with protected data. Create a second class in the same file
with a method that manipulates the protected data in the first class.
*/
class Manipulacjazad6{
	protected int a = 1;
}
public class Zad6_6{
	public static void main(String[] args){
	Manipulacjazad6 x = new Manipulacjazad6();
	
x.a = 3;
println(x.a);
}}