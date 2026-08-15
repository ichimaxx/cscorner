import myutils.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.Serializable;

/*
Exercise 21: (5) Modify SineWave.java to turn SineDraw into a JavaBean by adding
"getter" and "setter" methods.
*/
class SineDraw extends JPanel implements Serializable {
    //stała określająca liczbę punktów używanych do rysowania
    private static final int SCALEFACTOR = 200;
    // liczba cykli sinusoidy
    private int cycles;
    //liczba punktów używanych do narysowania sinusoidy
    private int points;
    //tablica przechowywująca obliczone wartości funkcji sin()
    private double[] sines;
    //tablica przechowywująca wartości Y przeliczone na współrzędne panelu
    private int[] pts;
    //konstruktor bezargumentowy ustawia początkowo 5 cykli
    public SineDraw() { setCycles(5); }
    //metoda wywoływana przez Swing podczas rysowania panelu
    public void paintComponent(Graphics g) {
        //wyczyszczenie i przygotowanie panelu do ponownego rysowania
        super.paintComponent(g);
        //pobranie aktualnej szerokości panelu
        int maxWidth = getWidth();
        //obliczenie odstępu pomiędzy kolejnymi punktami
        double hstep = (double)maxWidth / (double)points;
        //wysokość panelu
        int maxHeight = getHeight();
        //utworzenie tablicy współrzędnych Y
        pts = new int[points];
        //przeliczenie wartości sinusa (-1 do 1)
        //na współrzędne pikselowe panelu
        for(int i = 0; i < points; i++)
            pts[i] =
                    (int)(sines[i] * maxHeight/2 * .95 + maxHeight/2);
        g.setColor(Color.RED);
        //łączenie kolejnych punktów liniami
        //tworzy widoczną sinusoidę
        for(int i = 1; i < points; i++) {
            int x1 = (int)((i - 1) * hstep);
            int x2 = (int)(i * hstep);
            int y1 = pts[i-1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }
    //setter cycles, ustawia liczbę cykli i ponownie oblicza sinusoidę
    public void setCycles(int newCycles) {
        cycles = newCycles;
        //liczba punktów zależy od liczby cykli
        points = SCALEFACTOR * cycles * 2;
        //utworzenie tablicy na wartości sinusoidy
        sines = new double[points];
        //obliczenie wartości sijn() dla kolejnych punktów
        for(int i = 0; i < points; i++) {
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);
        }
        repaint();
    }
    //getter właściwości cycles
    //zwraca aktualną liczbę cykli sinusoidy
    public int getCycles() {
        return cycles;
    }

}
public class Zad22_21 extends JFrame {
    //Bean SineDraw, który będzie wyświetlany w oknie
    private SineDraw sines = new SineDraw();
    //slider pozwalający wybrać od 1 do 30 cykli, wartość początkowa to 5
    private JSlider adjustCycles = new JSlider(1, 30, 5);
    public Zad22_21() {
        add(sines);
        //listener do zmiany wartości slidera
        adjustCycles.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.setCycles(
                        ((JSlider)e.getSource()).getValue());
            }
        });
        add(BorderLayout.SOUTH, adjustCycles);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Zad22_21(), 700, 400);
    }
}
/*
Program modyfikuje przykład SideWave.java (Zad22_21.java), tworząc z klasy SineDraw JavaBean.

Właściwość cycles posiada publiczny getter getCycles() oraz setter setCycles(),
który zmienia liczbę cykli sinusoidy, przelicza jej punkty i odświeża rysunek.

JSlider zmienia wartość cycles poprzez wywołanie setCycles().
*/