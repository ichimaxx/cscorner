/**
 * SIEMA TO ZADANIE SLUŻY ABY ODPALIC POWITANKO
 * @author pewka
 * @version jakstam nastepna
 */
public class zad2_2 {

    /**
	 * TU JEST PIERWSZY PARAMETER
     */
    private String c = "Witaj,";

    /**
     * TU JEST DRUGI PARAMETER
     */
    private String a = "świecie!";

    /**
     * TO INFORMACJA JAKA TO JES METODA GŁÓWNA PROGRAMU
     */
    public zad2_2() {
        System.out.println(c + a);
    }

    /**
     * TO URUCHAMIA APLIKACJE TWORZĄC NOWĄ INSTANCJE KLASY ZAD2_2
	 *@param	 args TO SĄ ARGUMENTY WIERSZA POLECEN <p> TAK ZWANE ALE TEGO NIE TRZYY TU</p>
     */
    public static void main(String[] args) {
        new zad2_2();
    }
}