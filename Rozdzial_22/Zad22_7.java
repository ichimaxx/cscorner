import javax.swing.*;
import java.awt.*;
import myutils.*;

/*
Exercise 7: (5) Create an application using SwingConsole, and add all the Swing
components that have an addActionListener( ) method. (Look these up in the JDK
documentation from http://java.sun.com. Hint: Search for addActionListener( ) using
the index.) Capture their events and display an appropriate message for each inside a text
field.
*/
public class Zad22_7 extends JFrame {
    //pole do wyświetlania informacji,
    //który komponent wygenerował ActionEvent
    private JTextField output = new JTextField(30);
    //JTextField generuje ActionEvent np. po naciśnięciu Enter
    private JTextField a = new JTextField(20);
    //zwykły przycisk
    private JButton b = new JButton("Button 1");
    //pole wyboru
    private JCheckBox c = new JCheckBox("CheckBox");
    //przycisk posiadający dwa stany: włączony i wyłączony
    private JToggleButton d = new JToggleButton("ToggleButton");
    //element menu
    private JMenuItem e = new JMenuItem("E");
    //element menu działający jak JCheckBox
    private JCheckBoxMenuItem f = new JCheckBoxMenuItem("F");
    //menu które może zawierać inne elementy JMenuItem
    private JMenu g = new JMenu("Menu");
    //przycisk typu radio (taki checkbox tylko okrągły)
    private JRadioButton h = new JRadioButton("RadioButton");
    //pole tekstowe umożliwiające formatowanie wprowadzanych danych
    private JFormattedTextField i = new JFormattedTextField();
    //rozwijana lista wartości
    private JComboBox<String> j = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6"});
    //pole tekstowe przeznaczone do wpisywania hasła
    private JPasswordField k = new JPasswordField(10);
    //komponent umożliwiający wybór pliku
    private JFileChooser l = new JFileChooser();
    //element menu działający jak JRadioButton
    private JRadioButtonMenuItem m = new JRadioButtonMenuItem("M");


    public Zad22_7() {
        //FlowLayout układa komponenty kolejno w oknie
        setLayout(new FlowLayout());
        //dodawanie wszystkich testowanych komponentów do JFrame
        add(a);
        add(b);
        add(c);
        add(d);
        add(e);
        add(f);
        add(g);
        add(h);
        add(i);
        add(j);
        add(k);
        add(l);
        add(m);
        //wyświetla informacje o zdarzeniach
        add(output);
        //każdy komponent otrzymuje własny ActionListener
        a.addActionListener( e ->
                output.setText("JTextField"));
        b.addActionListener( e ->
                output.setText("JButton"));
        c.addActionListener( e ->
                output.setText("JCheckBox"));
        d.addActionListener( e ->
                output.setText("JToggleButton"));
        e.addActionListener( e ->
                output.setText("JMenuItem"));
        f.addActionListener( e ->
                output.setText("JCheckBoxMenuItem"));
        g.addActionListener(e ->
                output.setText("JMenu"));
        h.addActionListener(e ->
                output.setText("JRadioButton"));
        i.addActionListener(e ->
                output.setText("JFormattedTextField"));
        j.addActionListener(e ->
                output.setText("JComboBox"));
        k.addActionListener(e ->
                output.setText("JPasswordField"));
        l.addActionListener(e ->
                output.setText("JFileChooser"));
        m.addActionListener(e ->
                output.setText("JRadioButtonMenuItem"));
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_7(), 1500, 1400);
    }
}

/*
Program zawiera komponenty Swing posiadające metodę addActionListener()
Do każdego komponentu został przypisany ActionListener zapisany jako lambda.

Po wykonaniu odpowiedniej akcji, np. naciśnięciu przycisku
lub wciśnięciu enter w JTextField, generowany jest ActionEvent.

Listener przechwytuje zdarzenie i wyświetla nazwę komponentu w polu JTextField output.
*/