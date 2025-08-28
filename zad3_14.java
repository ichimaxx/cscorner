import java.util.*;
import static myutils.Skrocenie_Print.print;

public class zad3_14 {
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