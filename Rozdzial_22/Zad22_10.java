import myutils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Exercise 10: (6) Create an application using SwingConsole, with a JButton and a
JTextField. Write and attach the appropriate listener so that if the button has the focus,
characters typed into it will appear in the JTextField.
*/
public class Zad22_10 extends JFrame {
    //pole tekstowe do którego trafiają znaki wpisywane, gdy przycisk ma focus
    private JTextField pole = new JTextField();
    private Przycisk
            b1 = new Przycisk(Color.ORANGE, "NACIŚNIJ ABY WYCZYŚCIC POLE PO PRAWEJ");
    class Przycisk extends JButton {
        //KeyListener nasłuchuje zdarzeń z klawiatury
        KeyListener kl = new KeyListener() {
            //keyTyped() zostaje wywołane po wpisaniu znaku
            public void keyTyped(KeyEvent e) {
                //getKeyChar() pobiera wpisany znak,
                //który zostaje dopisany do aktualnego JTextField
                pole.setText(pole.getText() + e.getKeyChar());
            }
            //implementując interfejs KeyListener,
            //wymagane jest zaimplementować resztę metod
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        public Przycisk(Color color, String label) {
            super(label);
            //ustawienie koloru przycisku
            setBackground(color);
            //podpięcie KeyListener do przycisku
            addKeyListener(kl);
        }
    }
    public Zad22_10() {
        //okno dzieli się na dwie części:
        //przycisk po lewej, JTextField po prawej
        setLayout(new GridLayout(1, 2));
        //kliknięcie przycisku czyści zawartość pola tekstowego
        b1.addActionListener(e -> {
            pole.setText("");
        });
        add(b1);
        add(pole);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_10(), 700, 500);
    }
}

/*
Program tworzy JButton oraz JTextField

Do przycisku został przypisany KeyListener. Gdy przycisk posiada focus,
wpisywane znaki są przechwytywane przez metodę keyTyped().

Metoda getKeyChar() pobiera wpisany znak, który następnie jest
dopisany do zawartości JTextField.

Kliknięcie przycisku dodatkowo czyści pole tekstowe.
*/
