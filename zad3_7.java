import static myutils.Skrocenie_Print.print;
import java.util.Random;

public class zad3_7 { 

public static void main (String[] args){
	 zad3_7 moneta = new zad3_7();
	 String orzel = "Orzeł!";
	 String reszka = "Reszka!";
	 Random rand = new Random();
	int los = rand.nextInt(2);
	String wynik = (los == 0) ? orzel : reszka;
	System.out.print("Uwaga rzucam monetom: ");
	print(wynik);
}}
