import static myutils.Skrocenie_Print.*;

enum Kasa {
YEN, ZLOTY, DOLLAR, EURO, YUAN, DIRHAM }

public class Zad5_21 {
	public static void main(String[] args){
		for (Kasa k : Kasa.values())
println(k + ", ordinal " + k.ordinal()); 
}}