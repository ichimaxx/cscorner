import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import myutils.*;
/*
Exercise 24: (7) Remember the "sketching box" toy with two knobs, one that controls
the vertical movement of the drawing point, and one that controls the horizontal movement?
Create a variation of this toy, using SineWave.java to get you started. Instead of knobs, use
sliders. Add a button that will erase the entire sketch.
*/
//panel przechowywujący i rysujący szkic
class SineDraw3 extends JPanel {
    //lista przechowująca kolejne pozycje punktu rysującego
    public ArrayList<Point> punkty = new ArrayList<>();
    //aktualna pozycja punktu w osi X i Y
    public int nowyX = 0;
    public int nowyY = 0;
    public SineDraw3() {
        //punkt początkowy rysowania
        punkty.add(new Point(0,0));
    }
    //rysowanie wszystkich zapamiętanych odcinków
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //kolejne punkty z listy są łączone liniami
        for(int i = 1; i < punkty.size(); i++) {
            Point poprzedni = punkty.get(i - 1);
            Point aktualny = punkty.get(i);
            g.drawLine(aktualny.x, aktualny.y,poprzedni.x,poprzedni.y);
        }
    }
}
public class Zad22_24 extends JFrame {
    //panel z rysowaniem XY
    private SineDraw3 sines = new SineDraw3();
    private JButton clear = new JButton("Clear");
    //slider kontrolujący rysowanie po osi X
    private JSlider rysowanieX = new JSlider(0, 1000, 0);
    //slider kontrolujący rysowanie po osi Y
    private JSlider rysowanieY = new JSlider(0, 1000, 0);
    public Zad22_24() {
        //panel z rysowaniem
        add(sines);
        //przycisk czyszczenia
        add(clear, BorderLayout.WEST);
        //slider rysowania po osi X i Y
        add(rysowanieX, BorderLayout.SOUTH);
        add(rysowanieY, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //zmiana sliderów pozwala rysować wzdłuż osi X i Y
        rysowanieY.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.nowyY = rysowanieY.getValue();
                sines.punkty.add(new Point(sines.nowyX, rysowanieY.getValue()));
                sines.repaint();
            }
        });
        //usuwa wszysstkie zapamiętane punkty, czyści cały szkic
        clear.addActionListener(e -> {
            sines.punkty.clear();
            sines.repaint();
        });
        //zmiana slidera X zmienia pozycję punktu w osi poziomej
        //i zapisuje nowy punkt historii rysowania
        rysowanieX.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sines.nowyX = rysowanieX.getValue();
                sines.punkty.add(new Point(rysowanieX.getValue(), sines.nowyY));
                sines.repaint();
            }
        });
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_24(), 1600, 1400);
    }
}

/*
Program symuluje zabawkę typu Etch A Sketch za pomocą dwóch sliderów.
Jeden slider steruje pozycją punktu w osi X, a drugi w osi Y.

Każda nowa pozycja jest zapisywana jako Point w ArrayList.

paintComponent() przechodzi po zapisanych punktach i łączy kolejne
punkty liniami, dzięki czemu powstaje cały szkic.

Do wyczyszczenia szkicu używany jest przycisk clear.
*/