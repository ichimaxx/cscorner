import static myutils.Skrocenie_Print.print;

public class Zad5_16 {
	public static void main(String[] args){
		String[] a = new String[6];
	
		for (int i = 0; i < a.length; i++)
		a[i] = "element" + " " + i;
		for (int i = 0; i < a.length; i++)
		print ("a[" + i + "] = " + a[i]);
}}