import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import myutils.*;
import java.util.Timer;

import static myutils.Skrocenie_Print.println;

/*
Exercise 26: (5) Modify the previous exercise so that multiple sine wave panels are
created within the application. The number of sine wave panels should be controlled by
command-line parameters.
*/
class SineDraw5 extends JPanel {
    //stała określająca liczbę punktów używanych do rysowania
    public static final int SCALEFACTOR = 1000;
    // liczba cykli sinusoidy
    public int cycles;
    //liczba punktów używanych do narysowania sinusoidy
    public int points;
    //tablica przechowująca obliczone wartości funkcji sin()
    public double[] sines;
    //aktualne przesunięcie sinusoidy względem X
    public int offSetX = 1;
    //określa szybkość przesuwania sinusoidy
    public int predkosc = 1;
    //tablica przechowująca wartości Y przeliczone na współrzędne panelu
    public int[] pts;
    //konstruktor z timerem
    public SineDraw5() {
        //uruchamia TimerTask po 100ms i powtarza go co 100ms
        Timer timer = new Timer();
        timer.schedule(task, 100, 100);
        //początkowa wartość cykli sinusoidy
        setCycles(5); }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        //modulo powoduje zawijanie przesunięcia po dojściu do szerokości panelu
        int przesuniecie = offSetX % maxWidth;
        //odległość pozioma pomiędzy kolejnymi punktami sinusoidy
        double hstep = (double) maxWidth / (double) points;
        //wysokość panelu
        int maxHeight = getHeight();
        //kobia Graphics2D pozwala przesuwać sinusoidę
        //bez przesuwania obramowania panelu
        Graphics2D g2 = (Graphics2D) g.create();
        //utworzenie tablicy współrzędnych Y
        pts = new int[points];
        //liczenie wartości sinusa na współrzędne pikselowe panelu
        for (int i = 0; i < points; i++)
            pts[i] =
                    (int) (sines[i] * maxHeight / 2 * .95 + maxHeight / 2);
        g2.setColor(Color.RED);
        //przesunięcie całej sinusoidy w lewo (-x)
        g2.translate(-przesuniecie, 0);
        for (int i = 1; i < points; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            //pierwsza kopia sinusoidy
            g2.drawLine(x1, y1, x2, y2);
            //druga identyczna przesunięta o szerokość panelu
            //zapobiega powstawaniu pustego miejsca podczas przewijania
            g2.drawLine(x1 + maxWidth, y1, x2 + maxWidth, y2);
        }
        //zwolnienie kopii Graphics2D
        g2.dispose();
    }
    //zadanie wykonywane cyklicznie przez java.util.Timer
    TimerTask task = new TimerTask() {
        public void run() {
            //zwiększa przesunięcie o aktualną wartość prędkości
            offSetX += predkosc;
            //rysuje sinusoidę w nowej pozycji
            repaint();
        }
    };
    public void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
        sines = new double[points];
        for(int i = 0; i < points; i++) {
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);
        }
        repaint();
    }
}
public class Zad22_26 extends JFrame {
    //tablica przechowująca wszystkie utworzone panele sinusoid
    private SineDraw5[] sines;
    //panel z opisem i sliderem predkosci
    private JPanel speedPanel = new JPanel();
    private JLabel speedLabel = new JLabel("Prędkość:");
    //jeden slider steruje prędkością wszystkich sinusoid
    private JSlider adjustSpeed = new JSlider(1, 1000, 1);
    //ilePaneli określa liczbę sinusoid z linii poleceń
    public Zad22_26(int ilePaneli) {
        //panel układający wszystkie sinusoidy pionowo jedną pod drugą
        JPanel sinePanel = new JPanel(new GridLayout(ilePaneli, 1));
        //dodanie opisu i slidera do dolnego panelu
        speedPanel.add(speedLabel);
        speedPanel.add(adjustSpeed);
        //utworzenie tablicy o rozmiarze zależnym od argumentu programu
        sines = new SineDraw5[ilePaneli];
        //utworzenie wymaganej liczby paneli sinusoid
        for(int i = 0; i < ilePaneli; i++) {
            sines[i] = new SineDraw5();
            //obramowanie, aby rozróżnić panele
            sines[i].setBorder(BorderFactory.createLineBorder(Color.black));
            sinePanel.add(sines[i]);
        }
        adjustSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                for(SineDraw5 s : sines) {
                    s.predkosc = adjustSpeed.getValue();
                }
            }
        });
        //panel sinusoid
        add(sinePanel, BorderLayout.CENTER);
        //panel ze sliderem
        add(speedPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_26(Integer.parseInt(args[0])), 1500, 1000);
    }
}

/*
Program rozwija poprzednie ćwiczenie (Zad22_25), tworząc wiele
niezależnych paneli z animowanymi sinusoidami.

Liczba paneli jest pobierana z argumentu commandline i na jej
podstawie tworzona jest tablica SineDraw5[] oraz odpowiednia liczba obiektów SineDraw5.

GridLayout układa wszystkie sinusoidy pionowo jedna pod drugą.
Każdy panel posiada własny Timer i własną animowaną sinusoidę.

Jest jeden wspólny JSlider, który zmienia prędkość wszystkich sinusoid.

*/