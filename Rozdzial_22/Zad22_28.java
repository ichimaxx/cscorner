import myutils.SwingConsole;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/*
Exercise 28: (7) Create a dice class (just a class, without a GUI). Create five dice and
throw them repeatedly. Draw the curve showing the sum of the dots from each throw, and
show the curve evolving dynamically as you throw more and more times.
*/
//panel odpowiedzialny za przechowywanie wyników i rysowanie wykresu
class SineDraw6 extends JPanel {
    //lista przechowująca punkty X = numer rzutu, Y = suma oczek
    public ArrayList<Point> punkty = new ArrayList<>();
    //numer aktualnego rzutu
    public int nowyX = 0;
    //suma oczek z aktualnego rzutu
    public int nowyY = 0;
    public SineDraw6() {
        //punkt początkowy wykresu
        punkty.add(new Point(0,0));
    }
    //rysowanie osi, skali oraz zapamiętanych wyników
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //odległość osi od krawędzi panelu, po to, aby wykres nie zaczynał się przy krawędzi okna
        int margines = 50;
        //pozycja poziomej osiX
        int osX = getHeight() - margines;
        //rysowanie osi X i Y
        g.drawLine(margines, osX, getWidth() - 20, osX);
        g.drawLine(margines, osX, margines, 20);
        //opisy osi
        g.drawString("Liczba Rzutow", getWidth() / 2, osX + 30);
        g.drawString("Suma oczek", 5, 20);
        //skala osiY, możliwa suma pięciu kostek wynosi od 5 do 30
        for(int i = 5; i <= 30; i += 5) {
            int y = osX -i * 20;
            //opis osi dodawany co 5
            g.drawString("" + i, margines - 30, y + 5);
        }
        //skala osiX, do 100 rzutów
        for(int i = 1; i <= 100; i++) {
            int x = margines + i * 20;
            //opis osi, dodawany co 1
            g.drawString("" + i, x, osX + 20);
        }
        //kolejne wyniki są łączone liniami
        for(int i = 1; i < punkty.size(); i++) {
            Point poprzedni = punkty.get(i - 1);
            Point aktualny = punkty.get(i);
            //przeliczanie numeru rzutu i sumy oczek na współrzędne panelu
            int x = margines + aktualny.x * 20;
            int y = osX - aktualny.y * 20;
            int oY = osX - poprzedni.y * 20;
            int oX = margines + poprzedni.x * 20;
            g.drawLine(x,y,oX,oY);
        }
    }
}
//klasa z jedną kostką
class Dice6 {
    Random rand = new Random();
    private int i;
    public Dice6() {
    }
    public int getValue() {
        return i;
    }
    public void roll() {
        i = rand.nextInt(6) + 1;
    }
}
public class Zad22_28 extends JFrame {
    //tablica 5 kostek
    private Dice6[] dices;
    private int iloscKostek = 5;
    //panel z rysowaniem XY
    private SineDraw6 sines = new SineDraw6();
    //przycisk czyszczący wykres
    private JButton clear = new JButton("Clear");
    //przycisk wykonujący kolejny rzut pięcioma kostkami
    private JButton rysowanieXY = new JButton("RZUT");

    public Zad22_28() {
        dices = new Dice6[iloscKostek];
        for(int i = 0; i < iloscKostek; i++) {
            dices[i] = new Dice6();
        }
            //panel z rysowaniem
            add(sines);
            //przycisk czyszczenia
            add(clear, BorderLayout.WEST);
            add(rysowanieXY, BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //po kliknięciu RZUT wszystkie pięć kostek jest rzucanych na raz,
            //ich wartości są sumowane
            rysowanieXY.addActionListener((ActionListener) e -> {
                int suma = 0;
                for (Dice6 s : dices) {
                    s.roll();
                    suma += s.getValue();
                }
                //zapisanie numeru rzutu i jego sumy jako nowego punktu
                sines.nowyY = suma;
                sines.nowyX++;
                sines.punkty.add(new Point(sines.nowyX,sines.nowyY));
                //odświeżenie wykresu
                sines.repaint();
            });
            //usuwa wszystkie zapamiętane punkty, czyści cały szkic
            clear.addActionListener(e -> {
                sines.punkty.clear();
                sines.nowyX = 0;
                //trzeba dodać nowy punkt początkowy
                sines.punkty.add(new Point(0,0));
                sines.repaint();
            });
        }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_28(), 1600, 1400);
    }
}

/*
Program tworzy pięć obiektów Dice. Po każdym kliknięciu RZUT
wszysktie kostki są rzucane, a suma ich oczek jest zapisywana jako kolejny punkt wykresu.

Oś X przedstawia numer rzutu, a oś Y sumę oczek pięciu kostek.
Kolejne punkty są łączone liniami, dzięki czemu wykres rozwija się dynamicznie
po każdym rzucie. Przycisk Clear rozpoczyna wykres od nowa.
 */