import static myutils.Skrocenie_Print.*;
/*
Exercise 21: (1) Create an enum of the least-valuable six types of paper currency. Loop
through the values( ) and print each value and its ordinal( ).
*/
enum Kasa {
YEN, ZLOTY, DOLLAR, EURO, YUAN, DIRHAM }

public class Zad5_21 {
	public static void main(String[] args){
		for (Kasa k : Kasa.values())
println(k + ", ordinal " + k.ordinal()); 
}}