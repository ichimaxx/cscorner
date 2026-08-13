import myutils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
Exercise 17: (5) Create an application using SwingConsole. In the JDK documentation
from http://java.sun.com, find the JPasswordField and add this to the program. If the
user types in the correct password, use JOptionPane to provide a success message to the
user.
*/
public class Zad22_17 extends JFrame {
    //przycisk z podpiętym listenerem sprawdzającym poprawność hasła
    private JButton b = new JButton("Check Password");
    //pole do wpisywania hasła
    private JPasswordField pp = new JPasswordField(15);
    public Zad22_17() {
        b.addActionListener(e-> {
            //poprawne hasło
            String password = "Start";
            //getPassword() pobiera wpisane hasło jako tablicę char[]
            //new String() zamienia ją na String aby można było porównać ją przez equals()
            if(password.equals(new String(pp.getPassword()))) {
                //jeżeli hasło jest poprawne, wyświetla ten komunikat
                JOptionPane.showMessageDialog(this,
                        "CORRECT PASSWORD!!!!", "Hey!",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                //jeżeli hasło niepoprawne, to ten
                JOptionPane.showMessageDialog(this,
                        "WRONG PASSWORD!!!!", "Hey!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        setLayout(new FlowLayout());
        add(pp);
        add(b);
    }
    public static void main (String[] args) {
        SwingConsole.run(new Zad22_17(), 250, 375);
    }
}

/*
Program tworzy JPasswordField i przycisk do sprawdzania hasła.

Po naciśnięciu przycisku getPassowrd() pobiera wpisane hasło.
JOptionPane wyświetla komunikat w zależności czy wpisane hasło było porawne, czy nie.
*/
