/**
 * SIEMA TO ZADANIE SLURZY ZEBY ODPALIC POWITANKO NO BO JAK KURWA INACZEJ HEHE
 * @author pewka
 * @version jakstam nastepna
 */
public class zad2_2 {

    /**
	 * TU JES PIERSZY PARAMETER
     */
    private String c = "Witaj,";

    /**
     * TU JEST DRUGI PARAMETER
     */
    private String a = "świecie!";

    /**
     * TO INFORMACJA JAKA TO JES METODA GUWNA PROGRAMU
     */
    public zad2_2() {
        System.out.println(c + a);
    }

    /**
     * TO URUHAMIA APLIKACJE TWORZONC NOWOM INSTANCJE KLASY ZAD2_2
	 *@param	 args TO SOM ARGUMENTY WIERSZA POLECEN <p> TAKZWAWNE ALE TEGO NIE URZYWAMY TU</p>
     */
    public static void main(String[] args) {
        new zad2_2();
    }
}