import javax.swing.*;
import java.awt.*;
import myutils.*;
/*
Exercise 8: (6) Almost every Swing component is derived from Component, which has
a setCursor( ) method. Look this up in the JDK documentation. Create an application and
change the cursor to one of the stock cursors in the Cursor class.
*/
public class Zad22_8 extends JFrame {
    //trzy przyciski
    private JButton
            b1 = new JButton("Zmiana kursora na rączkę"),
            b2 = new JButton("Powrót do normalności"),
            b3 = new JButton("Zmiana kursora na krzyżyk");
    public Zad22_8() {
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        //metoda setCursor() ustawia wybrany kursor dla okna
        b1.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });
        b2.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        });
        b3.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        });
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_8(), 1500, 1400);
    }
}

/*
Program tworzy trzy przyciski pozwalające zmieniać wygląd kursora.
Metoda Cursor.getPredefinedCursor() pobiera jeden z gotowych typów,
a setCursor() ustawia go dla okna JFrame.
*/