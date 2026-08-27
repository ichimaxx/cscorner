import static myutils.Skrocenie_Print.print;
/*
Exercise 14: (3) Write a method that takes two String arguments and uses all the
boolean comparisons to compare the two Strings and print the results. For the == and !=,
also perform the equals( ) test. In main( ), call your method with some different String
objects.
*/
public class Zad3_14 {
	public static void p(String a, boolean b){
		print(a + ": " + b);
	}
	public static void compare(String leftvalue, String rightvalue){
		print("leftvalue: " + leftvalue + " rightvalue: " + rightvalue);
	//!	p("leftvalue > rightvalue" + leftvalue > rightvalue);
	//!	p("leftvalue < rightvalue" + leftvalue < rightvalue);
	//!	p("leftvalue >= rightvalue" + leftvalue >= rightvalue);
	//!	p("leftvalue <= rightvalue" + leftvalue <= rightvalue);
	// xdd
		p("leftvalue == rightvalue", leftvalue == rightvalue);
		p("leftvalue != rightvalue", leftvalue != rightvalue);
		p("leftvalue.equals(rightvalue)", leftvalue.equals(rightvalue));
	}
	public static void main(String[] args){
		compare("Hello", "Hello");
		String s = new String("Hello");
		compare ("Hello", s);
		compare ("Hello", "Goodbye");
}}