import javax.swing.*;
import java.awt.*;
import myutils.*;
/*
Exercise 4: (1) Verify that without the setLayout( ) call in Buttoni.java, only one
button will appear in the resulting program.
*/

public class Zad22_4 extends JFrame {
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2");
    public Zad22_4() {
        //setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_4(), 200, 100);
    }
}
/*
Po zakomentowaniu setLayout(new FlowLayout()); JFrame używa domyślnego BorderLayout.

Oba przyciski są dodawane w to samo miejsce, dlatego widoczny jest tylko jeden.

setLayout(new FlowLayout()); zostało już użyte i pokazane w Zad22_2.java
*/