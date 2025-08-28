/**klasowy komentasz
*tu jest poczontek zadania 
*@version moja piersza
*@author Pawciu Lajsoki
*@see zad2_10
*/
public class Zad2_14
{
	private String message;
	/**
     * Konstruktor domyślny klasy Zad2_14. (jest to tu po to zeby sie javadoc nie przypierdalal ze tego nie ma)
     */
    public Zad2_14() {
		message = "Siema w Zad2_14!";
        // Kod inicjalizujący – przypisanie wartości początkowych do pól
    }
	/**komentasz wariablowy
	*tu taki komentasz variable co to za wariacje np teras jest publik statik void main conie
	*@param args czyli tablica dokumentow ktora jest przekazana do programju
	*/
	public static void main(String[] args){
	/**komentasz metodowy
	*
	*a tu jes komentasz metodowy to taki co to jest zaz metoda, pewnie metoda na gloda
	*/
	if (args.length >=3){
	System.out.println(args[0]);
	System.out.println(args[1]);
	System.out.println(args[2]);
	}
	else	
	{
	System.out.println("nie podano kurwa argumentuw, jak chcesz zeby cosik pokzalo napisz po javac Zad2_14 TRZY argumenty np JEBAC DISA KURWE");
	}
}
}
