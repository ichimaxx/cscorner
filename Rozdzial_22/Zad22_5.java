import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 5: (4) Create an application using the SwingConsole class. Include one text
field and three buttons. When you press each button, make different text appear in the text
field.
*/

public class Zad22_5 extends JFrame {
    //pole tekstowe, w którym pojawi się tekst po nacisnięciu przycisku
    private JTextField txt = new JTextField(20);
    //trzy przyciski w oknie
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2"),
            b3 = new JButton("Button 3");

    public Zad22_5() {
        //FLowLayout układa komponenty w kolejności, w której są dodawane przez add()
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        //pole tekstowe zostanie dodane po przyciskach,
        //FlowLayout układa komponenty od lewej do prawej
        add(txt);
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
        SwingConsole.run(new Zad22_5(), 1500, 1400);
    }
}

/*
Program zawiera jedno pole JTextField oraz 3 przyciski JButton.

ActionListener pozwala reagować na kliknięcia przycisków.
Jest on interfejsem funkcyjnym (posiada dokładnie jedną metodę abstrakcyjną),
dlatego jego metodę actionPerformed() można pisać za pomocą lambdy.
*/