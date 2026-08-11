import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myutils.SwingConsole;
import net.mindview.util.*;
import myutils.*;
/*
Exercise 14: (2) Modify TextPane.java to use a JTextArea instead of a JTextPane.
*/
public class Zad22_14 extends JFrame {
    private JButton b = new JButton("Add Text");
    //JTextArea użyte zamiast JTextPane
    private JTextArea tp = new JTextArea();
    private static myutils.Generator sg =
            new myutils.RandomGenerator.String(7);
    public Zad22_14() {
        //po kliknięciu dodawane jest 9 losowych Stringów
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 1; i < 10; i++)
                    //JTextArea posiada append(), więc nie trzeba kopiować wcześniejszej zawartości
                    tp.append(sg.next() + "\n");
            }
        });
        //JScrollPane umożliwia przewijanie tekstu
        add(new JScrollPane(tp));
        //przycisk umieszczony na dole okna
        add(BorderLayout.SOUTH, b);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_14(), 475, 425);
    }
}

/*
Program modyfikuje TextPane.java(Zad22_14).
Zastąpiono JTextPane na JTextArea.

Po naciśnięciu przycisku generowane są losowe napisy,
które są dopisywane do JTextArea za pomocą metody append().

JTextArea znajduje się wewnątrz JScrollPane,
dzięki czemu można przewijać tekst, gdy jest go więcej, niż mieści się
w widocznym obszarze.
*/