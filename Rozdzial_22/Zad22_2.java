import javax.swing.*;
import java.util.Random;
import java.util.concurrent.*;
import java.awt.FlowLayout;
/*
Exercise 2: (2) Modify HelloLabel.java to show that label addition is dynamic, by
adding a random number of labels.
*/
public class Zad22_2 {
    public static void main(String[] args) throws Exception {
        //generator liczb losowych
        Random rand  = new Random();
        //losowanie liczby etykiet od 1 do 100
        int losowaLiczba = rand.nextInt(100) + 1;
        //otwiera główne okno aplikacji
        JFrame frame = new JFrame("Hello Swing");
        //FlowLayout układa komponenty kolejno od lewej do prawej.
        //bez ustawienia layoutu JFrame używa domyślnie BorderLayout,
        //przez co wszystkie etykiety byłyby dodawane w to samo miejsce
        frame.setLayout(new FlowLayout());
        for(int j=0; j<losowaLiczba; j++){
            frame.add(new JLabel("A Label" + j));
        }
        //zamknięcie okna kończy działanie całej aplikacji
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //rozmiar okna
        frame.setSize(1500, 1400);
        //znajduje się na końcu, kiedy wszystkie komponenty zostały już dodane i skonfigurowane
        //pozwala na wyświetlanie wszystkich funkcji na ekranie
        frame.setVisible(true);
    }
}

/*
Program losuje liczbę od 1 do 100, a następnie dynamicznie tworzy odpowiednią liczbę obiektów JLabel
i dodaje je do okna JFrame.

FlowLayout umożliwia wyświetlenie wielu etykiet obok siebie, ponieważ domyślny (BorderLayout)
umieściłby je w tym samym miejscu.
 */