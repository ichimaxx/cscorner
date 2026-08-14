import javax.swing.*;
import java.util.*;
import myutils.*;
/*
Exercise 20: (6) Create a program that breaks a text file into words. Distribute those
words as labels on menus and submenus.
*/
public class Zad22_20 extends JFrame {
    //wczytanie pliku tekstowego i podzielenie go na pojedyncze słowa
    //regex \\W+ rozdziela tekst po znakach niebędących znakami słowa
    ArrayList<String> words = new ArrayList<String>(
            new TextFile("MenuList.txt", "\\W+"));
    //tablica przechowująca główne menu
    private JMenu[] menus;
    public Zad22_20() {
        //utworzenie miejsca na trzy główne menu
        menus = new JMenu[3];
        //pierwsze trzy słowa z pliku zostają nazwami głównych menu
        for(int i = 0; i < 3; i++) {
            menus[i] = new JMenu(words.get(i));
        }
        //pozostałe słowa są rozdzielane pomiędzy trzy główne menu
        //operator % powoduje przechodzenie kolejno przez indeksy 0,1,2
        for(int i = 3; i < words.size(); i++) {
            menus[i % 3].add(new JMenu(words.get(i)));
        }
        //utworzenie paska menu
        JMenuBar mb = new JMenuBar();
        //dodanie wszystkich głównych menu do JMenuBar
        for (JMenu jm : menus)
            mb.add(jm);
        //ustawienie utworzonego paska menu w JFrame
        setJMenuBar(mb);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_20(), 200, 150);
    }
}

/*
Program wczytuje plik tekstowy MenuList.txt i dzieli jego zawartość
na pojedyncze słowa.

Pierwsze trzy słowa sa używane jako nazwy głównych JMenu,
a pozostałe słowa są rozdzielane pomiędzy nie jako submenu.

Operator % pozwala cyklicznie rozdzielać kolejne słowa
pomiędzy trzy główne menu.
*/