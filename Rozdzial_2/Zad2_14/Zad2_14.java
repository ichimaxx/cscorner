
/*
Exercise 14: (1) Add an HTML list of items to the documentation in the previous
exercise.
*/
/**klasowy komentarz
*tu jest początek zadania
*@version moja piersza
*@author Pawciu Lajsoki
*@see zad2_10
*/
public class Zad2_14
{
	private String message;
	/**
     * Konstruktor domyślny klasy Rozdzial_2.Zad2_14.
     */
    public Zad2_14() {
		message = "Siema w Rozdzial_2.Zad2_14!";
        // Kod inicjalizujący – przypisanie wartości początkowych do pól
    }
	/**komentarz wariablowy
	*tu taki komentarz variable co to za wariacje np teraz jest public static void main
	*@param args czyli tablica dokumentow ktora jest przekazana do programu
	*/
	public static void main(String[] args){
	/**komentarz metodowy
	*
	*a tu jest komentarz metodowy to taki co to jest za metodą
	*/
	if (args.length >=3){
	System.out.println(args[0]);
	System.out.println(args[1]);
	System.out.println(args[2]);
	}
	else	
	{
	System.out.println("nie podano argumentów, jak chcesz zeby cosik pokzalo napisz po javac Rozdzial_2.Zad2_14 TRZY argumenty ");
	}
}
}
