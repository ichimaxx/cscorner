import static myutils.Skrocenie_Print.print;
import java.util.Random;
/*
Exercise 7: (3) Write a program that simulates coin-flipping.
*/
public class Zad3_7 {

public static void main (String[] args){
	 Zad3_7 moneta = new Zad3_7();
	 String orzel = "Orzeł!";
	 String reszka = "Reszka!";
	 Random rand = new Random();
	int los = rand.nextInt(2);
	String wynik = (los == 0) ? orzel : reszka;
	System.out.print("Uwaga, rzut!: ");
	print(wynik);
}}
