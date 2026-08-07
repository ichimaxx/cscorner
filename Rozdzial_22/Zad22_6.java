import java.util.regex.*;
import javax.swing.*;
import java.awt.*;
import myutils.*;
/*
Exercise 6: (7) Turn strings/TestRegularExpression.java into an interactive Swing
program that allows you to put an input string in one JTextArea and a regular expression in
a JTextField. The results should be displayed in a second JTextArea.
*/
public class Zad22_6 extends JFrame {
    //pole tekstowe do wyszukiwania regex
    private final JTextArea szukanyTekst = new JTextArea(20, 40);
    //pole na wyniki
    private final JTextArea outputs = new JTextArea(20, 40);
    //pole gdzie wpisywany jest regex
    private final JTextField regex = new JTextField(20);
    private final JButton button = new JButton("Szukaj");
    public Zad22_6() {
        button.addActionListener(e -> {
            String regexx = regex.getText();
            boolean flag = false;
            String input = szukanyTekst.getText();
            outputs.setText("");
            //matcher sprawdza kolejne fragmenty tekstu pasujące do wzorca regex
               Pattern p = Pattern.compile(regexx);
               Matcher m = p.matcher(input);
               while(m.find()) {
                   //wynik
                   outputs.append("Match \"" + m.group() + "\" at positions " +
                           m.start() + "-" + (m.end() - 1) + "\n");
                   flag = true;
               }
               if(!flag) {
                   outputs.append("NIE ZNALEZIONO");
               }
            });
        setLayout(new FlowLayout());
        add(new JLabel("Regex:"));
        add(regex);
        add(button);

        add(new JLabel("Tekst wejściowy:"));
        add(new JScrollPane(szukanyTekst));
        add(new JLabel("Output:"));
        add(new JScrollPane(outputs));
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_6(), 1500, 1400);
    }
}

/*
Program tworzy interaktywną aplikację do wyszukiwania dopasowań za pomocą regex(wyrażenia regularne).

Tekst jest wpisywany do JTextArea, a regex do JTextField. Po naciśnięciu przycisku "Szukaj"
tworzony jest obiekt Pattern i matcher, który sprawdza tekst za pomocą metody find().

Każde znalezione dopasowanie razem z pozycją jest wyświetlane w drugim JTextArea(outputs).
Gdy brak dopasowań, wyświetla komunikat "NIE ZNALEZIONO".
*/