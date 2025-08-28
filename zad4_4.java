import java.util.*;
import static myutils.Skrocenie_Print.print;

public class zad4_4 {
	
	public static void main (String[] args){
				int max = 50055;
				for (int a = 1; a < max; a++){ //  wyrzuca liczbe ktora bedzie dzielona od 1 do 500
				boolean prime = true; // zakladamy ze kazda wyrzucona liczba bedzie pierwsza
					for(int b = 2; b < a; b++) // wyrzucamy liczby ktore sa uzywana do dzielenia, mozna tez to liczyc na potęgach z warunkiem b * b <=a bo bedzie liczyc tylko na pierwiastkach przez co bedzie szukal reszt przy pomnożonych liczbach a nie przy każdej
						if (a % b == 0){ //jesli w kazdym przypadku przy dzieleniu reszta z dzielenia bedzie zero chociaz raz
					prime = false; //to zmieniamy warunek na false
					break;} //i lecimy do nastepnej liczby z petli pierwszej
					if (prime) print(a + " ");
}}}
