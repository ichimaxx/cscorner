import javax.swing.*;
import java.awt.*;
import java.util.*;
import myutils.*;
/*
Exercise 11: (4) Inherit a new type of button from JButton. Each time you press this
button, it should change its color to a randomly selected value. See ColorBoxes.java (later
in this chapter) for an example of how to generate a random color value.
*/
public class Zad22_11 extends JFrame {
    private static Random rand = new Random();
    //pole z kolorem
    private Color color;
    private Button
            b1 = new Button("NACIŚNIJ ABY ZMIENIĆ MÓJ KOLOR");
    class Button extends JButton {
        public Button(String label) {
            super(label);
            //ustawienie koloru przycisku
            addActionListener(e -> {
                color = new Color(rand.nextInt(0xFFFFFF));
                setBackground(color);
            });
        }
    }
    public Zad22_11() {
        add(b1);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_11(), 700, 500);
    }
}

/*
Program tworzy własny typ przycisku Button, który dziedziczy po JButton.

Przycisk posiada ActionListener, który po każdym kliknięciu
losuje nowy kolor za pomocą Random i tworzy z niego obiekt Color.

Metoda setBackground() ustawia wylosowany kolor jako tło przycisku.
*/