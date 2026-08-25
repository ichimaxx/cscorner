import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import java.util.*;
/*
Exercise 41: (4) Modify Display Environment.java so that it does nor use
SWTConsole.
*/
public class Zad22_41 {
    public static void main(String[] args) {
        //display zarządza połączeniem między SWT a systemem operacyjnym
        Display display = new Display();
        //shell to główne okno aplikacji SWT
        Shell shell = new Shell(display);
        // ustawienie tekstu widocznego na pasku tytułu okna
        shell.setText("Display Properties");
        //FillLayout sprawia, że komponent Text wypełni całe dostępne miejsce
        shell.setLayout(new FillLayout());
        //pole tekstowe z zawijaniem linii i pionowym paskiem przewijania
        Text text = new Text(shell, SWT.WRAP | SWT.V_SCROLL);
        // system.getenv() zwraca zmienne środowiskowe systemu jako map
        //każda para klucz-wartość jest dodawana do pola Text
        for(Map.Entry entry: System.getenv().entrySet()) {
            text.append(entry.getKey() + ": " +
                    entry.getValue() + "\n");
        }
        shell.setSize(800, 600);
        //główne okno
        shell.open();
        //główna pętla zdarzeń SWT
        //dopóki istnieje, program obsługuje zdarzenia użytkownika
        while(!shell.isDisposed())
            if(!display.readAndDispatch())
                //jeżeli nie ma zdarzeń, wątek spi
                display.sleep();
        display.dispose();
    }
}

/*
Zadanie pokazuje, jaki kod SWTConsole ukrywał w poprzednim ćwiczeniu(Zad22_40).
Po usunięciu SWTConsole aplikacja musi samodzielnie tworzyć Display i Shell,
uruchomić pętle zdarzeń oraz na końcu zwolnić zasoby przez display.dispose().

Display odpowiada za połączenie SWT z systemem operacyjnym, Shell jest głównym oknem aplikacji.
W SWT pętla obsługi zdarzeń jest jawna:
readAndDispatch() obsługuje oczekujące zdarzenia, a display.sleep() usypia wątek,
gdy nie ma nic do wykonania.

Podsumowując SWTConsole jest klasą pomocniczą, która zbiera powtarzalny kod potrzebny
do uruchomienia typowej aplikacji SWT.
*/