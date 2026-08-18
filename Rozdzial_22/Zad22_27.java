import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import myutils.*;
import javax.swing.Timer;
/*
Exercise 27: (5) Modify Exercise 25 so that the javax.swing.Timer class is used to
drive the animation. Note the difference between this and java.util.Timer.
*/
class SineDraw5 extends JPanel {
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
    public SineDraw5() {
        //zamiast java.util.Timer użyto javax.swing.Timer
        ActionListener task = e -> {
            offSetX += predkosc;
            repaint();
        };
        Timer timer = new Timer(100, task);
        timer.setRepeats(true);
        timer.start();
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
public class Zad22_27 extends JFrame {
    private SineDraw5 sines = new SineDraw5();
    private JSlider adjustSpeed = new JSlider(1, 1000, 1);
    public Zad22_27() {
        add(sines);
        adjustSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.predkosc = adjustSpeed.getValue();
            }
        });
        add(BorderLayout.SOUTH, adjustSpeed);

    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_27(), 700, 400);
    }
}

/*
Program modyfikuje Zad22_25, zamieniając java.util.Timer na javax.swing.Timer.
javax.swing.Timer wywołuje cyklicznie ActionListener co 100ms.
W jego obsłudze zwiększana jest wartość offsetX, a następnie repaint()
powoduje ponowne narysowanie sinusoidy w nowej pozycji.

Różnica między java.util.Timer a javax.swing.Timer:
- java.util.Timer wykonuje TimerTask w osobnym wątku timera.
- javax.swing.Timer wywołuje ActionListener w wątku EDT
- javax.swing.Timer jest wygodniejszy do prostych animacji i aktualizacji interfejsu Swing,
ponieważ kod timera wykonuje się w tym samym wątku co obsługa GUI.
 */