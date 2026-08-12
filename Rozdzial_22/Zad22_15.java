import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 15: (5) Add a check box to the application created in Exercise 5, capture the
event, and insert different text into the text field.
*/
public class Zad22_15 extends JFrame {
    //pole tekstowe, w którym pojawi się tekst po naciśnięciu przycisku
    private JTextField txt = new JTextField(20);
    //trzy przyciski w oknie
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2"),
            b3 = new JButton("Button 3");
    //checkbox do zadania
    private JCheckBox
            cb1 = new JCheckBox("Check Box 1");
    public Zad22_15() {
        //FLowLayout układa komponenty w kolejności, w której są dodawane przez add()
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        add(cb1);
        //pole tekstowe zostanie dodane po przyciskach,
        //FlowLayout układa komponenty od lewej do prawej
        add(txt);
        //ActionListener do nowego checkbox
        cb1.addActionListener(e -> {
            if(cb1.isSelected()) {
                txt.setText("Zaznaczono Checkbox 1");
            }  else {
                txt.setText("Odznaczono Checkbox 1");
            }

        });
        //pierwszy przycisk napisze w polu tekstowym: Kliknięto Button 1 itd.
        b1.addActionListener(e -> {
            txt.setText("Kliknięto Button 1");
        });

        b2.addActionListener(e -> {
            txt.setText("Kliknięto Button 2");
        });

        b3.addActionListener(e -> {
            txt.setText("Kliknięto Button 3");
        });
    }

    public static void main(String[] args) {
        //SwingConsole
        SwingConsole.run(new Zad22_15(), 1500, 1400);
    }
}

/*
Program rozszerza Exercise 5 (Zad22_5) o JCheckBox.

Do checkboxa został dodany ActionListener, który reaguje na zmianę jego stanu.
Metoda isSelected() sprawdza, czy checkbox jest zaznaczony.

W zależności od jego stanu w JTextField pojawi się inna informacja.
*/