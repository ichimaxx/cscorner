import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.math.*;

/*Exercise 5: (5) For each of the basic conversion types in the above table, write the most
complex formatting expression possible. That is, use all the possible format specifiers
available for that conversion type. */

public class Zad13_5 {
	public static void main(String[] args) { 
		Formatter f = new Formatter(System.out);
		int g = 15;
		int z = 1566666;
		String a = "hel";
		String c = "looooo";
		char o = 'o';
		Boolean w = false;
		double r = 1.5;
		f.format("%1$s%2$-" + g + ".1s\n", a, c); // Integral (as decimal) 
		f.format("%1$(-" + g + "d\n", g); // Unicode character 
		f.format("%1$-" + g + "c\n", o); // Boolean value 
		f.format("%1$-" + g + ".2b\n", w); // String 
		f.format("%1$-" + g + ".6f\n", r); // Floating point (as decimal) 
		f.format("%1$-" + g + ".6e\n", r); // Floating point (in scientific notation) 
		f.format("%1$-" + g + "x\n", z); //  Integral (as hex) 
		f.format("%1$-" + g + ".6h\n", z); // Hash code (as hex) 
		f.format("%2$d%-" + g + "%\n", g, z); // Literal "%" 
	}
}
		
		
		
	//	%[argument_index$][flags][width][.precision]conversion
	
	
/*
zadanie mowi zeby zaladowac conversions jak najbardziej sie da formatterem

podsumowanie :


- d Integral 
	argument index 1$
	flags: -, +, spacja, 0, ",", ( 
	width '15'
	conversion 'd'
	
- s String
	argument index 1$ 2$
	flags: -
	width '15'
	conversion 's'
	precision .1
	
- c Unicode Character
	argument index 1$
	flags -
	width '15'
	conversion 'c'
	
- b Boolean value
	argument index 1$ 
	flags: -
	width '15'
	conversion 'b'
	precision .2
	
- f Floating point (as decimal)
	argument index 1$ 
	flags: -
	width '15'
	conversion 'f'
	precision .6	
	
- e Floating point (in scientific notation)
	argument index 1$ 
	flags: -
	width '15'
	conversion 'e'
	precision .6
	
- x Integral (as hex)
	argument index 1$ 
	flags: -
	width '15'
	conversion 'x'	
	
- h Hash code (as hex)
	argument index 1$ 
	flags: -
	width '15'
	conversion 'h'		
	precision .6
	
- % Literal "%" 
	argument index 1$  --- MOZNA WPISAC ALE IT DOES NOT DO ANYTHING
	flags: -
	width '15'
	conversion '%'		
	
*/