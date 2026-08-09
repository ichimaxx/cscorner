import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import myutils.*;
/*
Exercise 12: (4) Monitor a new type of event in TrackEvent.java by adding the new
event-handling code. You’ll need to discover on your own the type of event that you want to
monitor.
*/
public class Zad22_12 extends JFrame {
    //mapa łączy zdarzenia z JTextField, w którym zostanie wyświetlona informacja o tym zdarzeniu
    private HashMap<String,JTextField> h =
            new HashMap<String,JTextField>();
    //tablica nazw wszystkich monitorowanych zdarzeń
    private String[] event = {
            "focusGained", "focusLost", "keyPressed",
            "keyReleased", "keyTyped", "mouseClicked",
            "mouseEntered", "mouseExited", "mousePressed",
            "mouseReleased", "mouseDragged", "mouseMoved",
            "actionPerformed"

    };
    //dwa przyciski korzystające z własnej klasy MyButton
    private MyButton
            b1 = new MyButton(Color.BLUE, "test1"),
            b2 = new MyButton(Color.RED, "test2");
    class MyButton extends JButton {
        //wyszukuje w mapie pole odpowiadające danemu zdarzeniu
        //i wpisuje do niego informacje o zdarzeniu
        void report(String field, String msg) {
            h.get(field).setText(msg);
        }
        //monitoruje otrzymanie i utratę focusu
        FocusListener fl = new FocusListener() {
            public void focusGained(FocusEvent e) {
                report("focusGained", e.paramString());
            }
            public void focusLost(FocusEvent e) {
                report("focusLost", e.paramString());
            }
        };
        //monitoruje zdarzenia klawiatury
        KeyListener kl = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                report("keyPressed", e.paramString());
            }
            public void keyReleased(KeyEvent e) {
                report("keyReleased", e.paramString());
            }
            public void keyTyped(KeyEvent e) {
                report("keyTyped", e.paramString());
            }
        };
        //monitoruje kliknięcia oraz wejście i wyjście kursora myszy z obszaru przycisku
        MouseListener ml = new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                report("mouseClicked", e.paramString());
            }
            public void mouseEntered(MouseEvent e) {
                report("mouseEntered", e.paramString());
            }
            public void mouseExited(MouseEvent e) {
                report("mouseExited", e.paramString());
            }
            public void mousePressed(MouseEvent e) {
                report("mousePressed", e.paramString());
            }
            public void mouseReleased(MouseEvent e) {
                report("mouseReleased", e.paramString());
            }
        };
        //monitoruje ruch i przeciąganie myszy
        MouseMotionListener mml = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                report("mouseDragged", e.paramString());
            }
            public void mouseMoved(MouseEvent e) {
                report("mouseMoved", e.paramString());
            }
        };
        //dodano ActionListener który śledzi naciśnięcie przycisku jako ActionEvent,
        //czyli jego uruchomienie
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                report("actionPerformed", e.paramString());
            }
        };
        public MyButton(Color color, String label) {
            super(label);
            setBackground(color);
            //podpięcie listenerów do przycisku
            addFocusListener(fl);
            addKeyListener(kl);
            addMouseListener(ml);
            addMouseMotionListener(mml);
            addActionListener(al);
        }
    }
    public Zad22_12() {
        //dla każdego zdarzenia tworzony jest jeden wiersz
        //nazwa zdarzenia + pole pokazujące szczegóły
        setLayout(new GridLayout(event.length + 1, 2));
        for(String evt : event) {
            JTextField t = new JTextField();
            //wyniki mogą być tylko odczytywane
            t.setEditable(false);
            //nazwa zdarzenia po lewej, wyniki po prawej
            add(new JLabel(evt, JLabel.RIGHT));
            add(t);
            h.put(evt, t);
        }
        add(b1);
        add(b2);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_12(), 700, 500);
    }
}

/*
Program monitoruje różne zdarzenia występujące na przyciskach MyButton.

Każde zdarzenie posiada swoje JTextField, a metoda report() wyszukuje odpowiednie
pole w hashMap i wyświetla w nim informacje otrzymywane z paramString() obiektu zdarzenia.

Do programu TrackEvent(Zad22_12) dodano dodatkowy ActionListener.
Monitoruje ActionEvent, czyli wykonanie głównej akcji JButton po jego aktywowaniu.
*/