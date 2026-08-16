import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import myutils.*;
import java.util.Timer;

import static myutils.Skrocenie_Print.println;

/*
Exercise 25: (8) Starting with SineWave.java, create a program (an application using
the SwingConsole class) that draws an animated sine wave that appears to scroll past the
viewing window like an oscilloscope, driving the animation with a java.util.Timer. The
speed of the animation should be controlled with a javax.swing.JSlider control.
*/
class SineDraw4 extends JPanel {
    //stała określająca liczbę punktów używanych do rysowania
    public static final int SCALEFACTOR = 1000;
    // liczba cykli sinusoidy
    public int cycles;
    //liczba punktów używanych do narysowania sinusoidy
    public int points;
    //tablica przechowywująca obliczone wartości funkcji sin()
    public double[] sines;
    //aktualne przesunięcie sinusoidy względem X
    public int offSetX = 1;
    //określa szybkość przesuwania sinusoidy
    public int predkosc = 1;
    //tablica przechowująca wartości Y przeliczone na współrzędne panelu
    public int[] pts;
    //konstruktor z timerem
    public SineDraw4() {
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
        //umożliwia przesuwanie całego układu współrzędnych
        Graphics2D g2 = (Graphics2D) g;
        //utworzenie tablicy współrzędnych Y
        pts = new int[points];
        //liczenie wartości sinusa na współrzędne pikselowe panelu
        for (int i = 0; i < points; i++)
            pts[i] =
                    (int) (sines[i] * maxHeight / 2 * .95 + maxHeight / 2);
        g.setColor(Color.RED);
        //przesunięcie całej sinusoidy w lewo (-x)
        g2.translate(-przesuniecie, 0);
        for (int i = 1; i < points; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            //pierwsza kopia sinusoidy
            g.drawLine(x1, y1, x2, y2);
            //druga identyczna przesunięta o szerokość panelu
            //zapobiega powstawaniu pustego miejsca podczas przewijania
            g.drawLine(x1 + maxWidth, y1, x2 + maxWidth, y2);
        }
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
public class Zad22_25 extends JFrame {
    private SineDraw4 sines = new SineDraw4();
    private JSlider adjustSpeed = new JSlider(1, 1000, 1);
    public Zad22_25() {
        add(sines);
        adjustSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.predkosc = adjustSpeed.getValue();
            }
        });
        add(BorderLayout.SOUTH, adjustSpeed);

    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_25(), 700, 400);
    }
}

/*
Program tworzy animowaną sinusoidę, która przesuwa się w lewo i daje
efekt ciągłego przewijania podobnego do oscyloskopu.

Timer cyklicznie zwiększa wartość offSetX o aktualną prędkość
ustawioną przez JSlider, repaint odświeża położenie sinusoidy.

Do uzyskania ciągłego przewijania rysowane są dwie identyczne kopie sinusoidy,
a operator %(modulo) zawija przesunięcie po osiągnięciu szerokości panelu.

JSlider steruje prędkością animacji.
*/