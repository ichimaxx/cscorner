import java.util.*;
import static myutils.Skrocenie_Print.print;

public class Zad5_9 {
private int value;   // pole przechowujące wartość licznika

    /**
     * Konstruktor bezargumentowy.
     * Ustawia licznik na 0, ale robi to przez wywołanie
     * drugiego konstruktora z parametrem.
     */
public Zad5_9() {
	this(3);                // musi być pierwszą instrukcją!
	print("Wywołano konstruktor bezargumentowy");
    }

    /**
     * Konstruktor z jednym argumentem.
     * Przyjmuje wartość startową licznika.
     */
    public Zad5_9(int startValue) {
        this.value = startValue;
        print("Wywołano konstruktor z parametrem: " + startValue);
    }

    /*‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑*/

    // Prosty test działania:
    public static void main(String[] args) {
        Zad5_9 c1 = new Zad5_9();     // uruchomi oba konstruktory łańcuchowo
        Zad5_9 c2 = new Zad5_9(15);   // uruchomi tylko drugi
}}