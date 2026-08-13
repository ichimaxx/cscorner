import myutils.*;
import javax.swing.*;
import java.awt.*;
/*
Exercise 18: (4) Modify MessageBoxes.java(Zad22_18) so that it has an individual
ActionListener for each button (instead of matching the button text).
*/
public class Zad22_18 extends JFrame {
    //przyciski wyświetlają różne rodzaje okien dialogowych JOptionPane
    private JButton
        a = new JButton("Alert"),
        b = new JButton("Yes/No"),
        c = new JButton("Color"),
        d = new JButton("Input"),
        e = new JButton("3 Vals");
    //output dla opcji z przycisków
    private JTextField txt = new JTextField(15);
    public Zad22_18() {
        //osobny ActionListener dla każdego przycisku
        a.addActionListener(event -> {
            //wyświetla komunikat błędu
            JOptionPane.showMessageDialog(null,
                "There’s a bug on you!", "Hey!",
                JOptionPane.ERROR_MESSAGE);
        });
        b.addActionListener(event -> {
            //wyświetla okno z możliwością wyboru Yes lub No
            JOptionPane.showConfirmDialog(null,
                    "or no", "choose yes",
                    JOptionPane.YES_NO_OPTION);
        });
        c.addActionListener(event -> {
            //tablica opcji wyświetlanych w oknie
            Object[] options = { "Red", "Green" };
            //showOptionDialog() zwraca indeks wybranej opcji
            int sel = JOptionPane.showOptionDialog(
                    null, "Choose a Color!", "Warning",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null,
                    options, options[0]);
            //jeżeli użytkownik nie zamknął okna bez wyboru,
            //wybrany kolor zostaje wyświetlony w JTextField
            if(sel != JOptionPane.CLOSED_OPTION)
                txt.setText("Color Selected: " + options[sel]);
        });
        //showInputDialog() pozwala użytkownikowi wpisać własny tekst
        d.addActionListener(event -> {
            String val = JOptionPane.showInputDialog(
                    "How many fingers do you see?");
            //zwraca wpisaną wartość do pola z outputem
            txt.setText(val);
        });
        e.addActionListener(event -> {
            Object[] selections = {"First", "Second", "Third"};
            //okno pozwala wybrać jedną z trzech wartości
            Object val = JOptionPane.showInputDialog(
                    null, "Choose one", "Input",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, selections, selections[0]);
            //jeżeli dokonano wyboru, wartość zwracana jest do pola output
            if(val != null)
                txt.setText(val.toString());
        });
        setLayout(new FlowLayout());
        add(a);
        add(b);
        add(c);
        add(d);
        add(e);
        add(txt);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_18(), 200, 200);
    }
}

/*
Program modyfikuje przykład MessageBoxes.java (Zad22_18).
Zamiast jednego wspólnego ActionListenera sprawdzającego tekst
naciśniętego przycisku, każdy JButton posiada własny ActionListener.

Dzięki temu każdy przycisk bezpośrednio wykonuje przypisaną mu akcję
i nie trzeba rozpoznawać przycisku za pomocą getSource() oraz getText().
*/