/**
 * Klasa zad2 służy do wyświetlania powitania w konsoli.
 * <p>
 * Tworzy obiekt, który łączy dwa napisy i wypisuje je na standardowym wyjściu.
 * </p>
 *
 * @author pewka
 * @version jakstam nastepna
 */
public class zad2_2 {

    /**
     * Zmienna przechowująca pierwszą część powitania.
     */
    private String c = "Witaj,";

    /**
     * Zmienna przechowująca drugą część powitania.
     */
    private String a = "świecie!";

    /**
     * Konstruktor klasy zad2.
     * <p>
     * Tworzy instancję klasy i wypisuje powitanie na standardowym wyjściu.
     * </p>
     */
    public zad2_2() {
        System.out.println(c + a);
    }

    /**
     * Metoda główna programu.
     * <p>
     * Uruchamia aplikację, tworząc nową instancję klasy zad2.
     * </p>
     *
     * @param args argumenty wiersza poleceń (niewykorzystane)
     */
    public static void main(String[] args) {
        new zad2_2();
    }
}