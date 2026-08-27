import static myutils.Skrocenie_Print.*;
/*
Exercise 22: (2) Write a switch statement for the enum in the previous example. For
each case, output a description of that particular currency.
*/
enum Kasa2 {
YEN, ZLOTY, DOLLAR, EURO, YUAN, DIRHAM }

public class Zad5_22 {
	Kasa2 opis;
public Zad5_22(Kasa2 opis) {this.opis = opis;}
public void rozpiska(){
print(opis + " - ");
switch(opis) {
	case YEN: println("waluta Japonii.");
	break;
	case ZLOTY: println("waluta Polski.");
	break;
	case DOLLAR: println("waluta USA.");
	break;
	case EURO: println("waluta Unii Europejskiej.");
	break;
	case YUAN: println("czyli waluta Chin.");
	break;
	case DIRHAM: println("czyli waluta ZEA.");
	default: println("nieznana waluta.");
}}
	public static void main(String[] args){
for(Kasa2 k: Kasa2.values()) {
	new Zad5_22(k).rozpiska();
}}}