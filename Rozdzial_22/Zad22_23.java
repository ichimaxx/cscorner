import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import myutils.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (8) Using SineWave.java as a starting point, create a program that
displays a rotating square on the screen. One slider should control the speed of rotation, and
a second slider should control the size of the box.
*/
//panel który rysuje obracający się kwadrat
class SineDraw2 extends JPanel {
    //pozycja początkowa kwadratu
    public int x = 250;
    public int y = 250;
    //początkowy rozmiar kwadratu
    public int w = 500;
    public int h = 500;
    //kąt obrotu
    public double angle = 0;
    //prędkość obrotu
    public int speed = 0;
    //kwadrat
    private Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, w, h);
    public SineDraw2() { }
    //rysowanie kwadratu na panelu
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //graphics2D umożliwia obracanie układu współrzędnych
        Graphics2D rectangle2 = (Graphics2D) g;
        //wyznaczenie aktualnego środka kwadratu
        int srodekX = x + w / 2;
        int srodekY = y + h / 2;
        //obrót układu współrzędnych wokół środka kwadratu
        rectangle2.rotate(Math.toRadians(angle),srodekX,srodekY);
        //aktualizacja rozmiaru i położenia prostokąta, po zmianie wartości
        rectangle.setRect(x, y, w, h);
        //rysowanie obróconego kwadratu
        rectangle2.draw(rectangle);
    }
}
public class Zad22_23 extends JFrame {
    //panel z obracającym się kwadratem
    private SineDraw2 sines = new SineDraw2();
    //slider kontrolujący prędkość obrotu
    private JSlider adjustSpeed = new JSlider(0, 40, 0);
    //slider kontrolujący rozmair kwadratu
    private JSlider adjustSize = new JSlider(0, 900, sines.w);
    public Zad22_23() {
        //panel z kwadratem znajduje się w centrum okna
        add(sines);
        //slider prędkości na dole, a slider rozmiaru na górze
        add(adjustSpeed, BorderLayout.SOUTH);
        add(adjustSize, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //zmiana slidera ustawia prędkość obrotu kwadratu
        adjustSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.speed = adjustSpeed.getValue();
            }
        });
        //zmiana slidera ustawia jednakową szerokość i wysokość,
        //dzięki czemu figura cały czas pozostaje kwadratem
        adjustSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.h = adjustSize.getValue();
                sines.w = adjustSize.getValue();
            }
        });
        //osobny wątek odpowiedzialny za animację obrotu
        new Thread(() -> {
            while (true) {
                //zwiększanie kąta o aktualną wartość prędkości
                //po pełnym obrocie wartość wraca do zakresu 0-359
                sines.angle = (sines.angle + sines.speed) % 360;
                //ponowne rysowanie panelu (efekt animacji)
                repaint();
                //krótka przerwa pomiędzy kolejnymi klatkami animacji
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    println(e);
                }
            }
        }).start();
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_23(), 1600, 1400);
    }
}

/*
Program wyświetla obracający się kwadrat narysowany za pomocą Graphics2D.

Pierwsze JSlider steruje prędkością obrotu poprzez zmianę wartości speed,
drugi zmienia szerokość i wysokość kwadratu.

Osobny wątek zwiększa kąt obrotu, a repaint() powoduje ponowne narysowanie
kwadratu pod nowym kątem. Obrót jest wykonywany wokół środka kwadratu.
*/