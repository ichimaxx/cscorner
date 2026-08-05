import javax.swing.*;
import java.util.Random;
import java.util.concurrent.*;
import java.awt.FlowLayout;
import myutils.*;
/*
Exercise 3: (3) Modify SubmitSwingProgram.java so that it uses SwingConsole.
 */
public class Zad22_3 extends JFrame {
    //etykieta wyświetlana w oknie
    private JLabel label = new JLabel("A Label");
    //JlLabel zostaje dodany do JFrame
    public Zad22_3() {
        add(label);
    }
    public static void main (String[] args) throws Exception {
        Zad22_3 swprogram = new Zad22_3();
        //SwingConsole.run() to gotowy program schowany w cscorner/myutils,
        //który wykonuje powtarzalne czynności:
        //ustawia tytuł okna(setTitle()),
        //operacje zamknięcia(.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE))
        //rozmiar (.setSize(width, height))
        // pokazuje okno(.setVisible(true))
        SwingConsole.run(swprogram, 1500, 1200);
        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(() ->{
            swprogram.label.setText("Hey! This is Different!");
        });
    }
}

/*
Klasa dziedziczy po JFrame i zawiera etykietę JLabel
Do konfiguracji i wyświetlania okna wykorzystano gotową metodę SwingConsole.run(),
ogranicza to pisanie kodu ustawiającego rozmiar zamykanie i widoczność okna.
 */