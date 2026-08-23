import myutils.*;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import java.io.*;
/*
Exercise 40: (4) Modify DisplayProperties.java so that it uses SWTConsole.
*/
public class Zad22_40 implements SWTApplication {
    //SWTConsole przekazuje tutaj kontener(Shell),
    //wewnątrz którego aplikacja ma utworzyć swój interfejs
    @Override
    public void createContents(Composite parent) {
        //FillLayout powoduje, że jedyny komponent w kontenerze
        //wypełnia całe dostępne miejsce
            parent.setLayout(new FillLayout());
            //pole tekstowe może zawijać linie oraz posiada pionowy scrollbar
            Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
            //StringWriter pozwala zapisać wynik do tekstu w pamięci,
            //zamiast wypisywać go bezpośrednio do konsoli
            StringWriter props = new StringWriter();
            //System.getProperties() pobiera właściwości środowiska Java/systemu
            //PrintWriter przekierowuje wynik metody list() do StringWriter
            System.getProperties().list(new PrintWriter(props));
            //Wyświetlenie zebranych właściwości systemowych w komponencie SWT Text
            text.setText(props.toString());
        }
    public static void main(String [] args) {
        //SWTConsole zajmuje się powtarzalnym kodem SWT
        SWTConsole.run(new Zad22_40(), 800, 600);
    }
}

/*
SWT (Standard Widget Toolkit) to biblioteka do tworzenia graficznych aplikacji
desktopowych w javie. Jest alternatywą dla Swinga i w większym stopniu korzysta
z natywnych elementów systemu operacyjnego, dzięki czemu aplikacje mogą wyglądać
i zachowywać się bardziej jak typowe programy dla danego systemu.

SWT nie jest częścią standardowego JDK, dlatego do uruchomienia zadania
wymagana jest dodatkowa biblioteka swt.jar. Instrukcja dodania biblioteki jest w README.md projektu.

W tym zadaniu SWTConsole usuwa powtarzalny kod potrzebny w każdej aplikacji SWT,
taki jak tworzenie Display i Shell, uruchamianie pętli zdarzeń oraz zwalnianie zasobów.

SWTApplication definiuje wspólny kontrakt dla aplikacji uruchamianych przez SWTConsole:
każda z nich musi zaimplementować createContents(Composite parent), gdzie tworzy własny
interfejs użytkownika.

Najważniejsze do zapamiętania:
wydzielenie powtarzalnego kodu do SWTConsole pozwala klasom aplikacji skupiać się tylko
na tym, co jest charakterystyczne dla ich GUI.
*/