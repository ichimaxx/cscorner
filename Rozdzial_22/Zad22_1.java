import javax.swing.*;
/*
Exercise 1: (1) Modify HelloSwing.java to prove to yourself that the application will
not close without the call to setDefaultCloseOperation( ).
*/
public class Zad22_1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello Swing");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}

/*
Zadanie wymaga, aby sprawdzono, że przy wykomentowaniu:

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

po zamknięciu stworzonego okna, program się nie zakończy samoistnie.
*/