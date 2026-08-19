import myutils.SwingConsole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Exercise 29: (3) In the JDK documentation for javax.swing, look up the
JColorChooser. Write a program with a button that brings up the color chooser as a dialog.
*/
public class Zad22_29 extends JFrame {
    private JButton
            open = new JButton("Open Color DIALOG");
    public Zad22_29() {
        open.addActionListener(new OpenL());
        add(open);
    }
    class OpenL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JColorChooser c = new JColorChooser();
            //otwiera okno z wyborem koloru jako dialog
            c.showDialog(Zad22_29.this, "OKNO", Color.WHITE);
        }


    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_29(), 800, 600);
    }
}

/*
Program tworzy przycisk otwierający JColorChooser jako okno dialogowe.
Po kliknięciu przycisku wywoływana jest metoda showDialog(),
która wyświetla gotowy wybór koloru z początkowo ustawionym kolorem białym.

JColorChooser pozwala wybrać kolor w osobnym oknie dialogowym.
 */