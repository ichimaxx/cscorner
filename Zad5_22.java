import static myutils.Skrocenie_Print.*;

enum Kasa {
YEN, ZLOTY, DOLLAR, EURO, YUAN, DIRHAM }

public class Zad5_22 {
Kasa opis;
public Zad5_22(Kasa opis) {this.opis = opis;}
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
for(Kasa k: Kasa.values()) {
	new Zad5_22(k).rozpiska();
}}}