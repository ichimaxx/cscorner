import java.util.prefs.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
import java.nio.file.*;
/*
Exercise 33: (2) Write a program that displays the current value of a directory called
"base directory" and prompts you for a new value. Use the Preferences API to store the value.
*/
public class Zad18_33 {
    public static void main(String[] args) throws Exception {
        //aktualny katalog programu
        String cwd = Paths.get("").toAbsolutePath().toString();
        //miejsce w systemie, gdzie preferences zapisuje ustawienia dla tej klasy
        Preferences prefs = Preferences
                .userNodeForPackage(Zad18_33.class);
        String key = "base directory";
        //aktualna wartość "base directory", jeżeli jej nie ma domyślnie używa cwd
        String cbd = prefs.get(key, cwd);
        println("Current Base Directory: " + cbd);
        //Scanner czyta dane wpisane przez użytkownika z System.in
        Scanner scanner = new Scanner(System.in);
        println("Wpisz nowy base directory: ");
        //wpisanie nowej ścieżki
        String nbd = scanner.nextLine();
        prefs.put(key, nbd);
        prefs.flush();
        //zwrot zaktualizowanej ścieżki
        println("base directory: " + prefs.get(key, cwd));
    }
}
/*
Program używa Preferences API do zapamiętania wartości "base directory".
Przy pierwszym uruchomieniu, jeżeli wartość nie istnieje, używa aktualnego katalogu roboczego jako domyślnej wartości.

Następnie użytkownik wpisuje nową ścieżkę, która zostaje zapisana w Preferences.
Przy kolejnym uruchomieniu program odczyta ostatnio zapisaną wartość.
*/