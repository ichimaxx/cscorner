import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 16: (5) Simplify List.java by passing the array to the constructor and
eliminating the dynamic addition of elements to the list.
*/
public class Zad22_16 extends JFrame {
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    //tablica zostaje przekazana bezpośrednio do konstruktora JList,
    //dzięki czemu wszystkie elementy pojawią się od razu
    private JList<String> lst = new JList<>(flavors);
    //pole wyświetlające zaznaczone elementy listy
    private JTextArea t =
            new JTextArea(flavors.length, 20);
    //listener reagujący na zaznaczanie elementów w JList
    private ListSelectionListener ll =
            e -> {
        //jeżeli zaznaczenie jest w trakcie zmiany, listener nic nie robi
                if(e.getValueIsAdjusting()) return;
                //czyszczenie poprzednich wyników
                t.setText("");
                //pobranie aktualnie zaznaczonych elementów i
                //wyświetlenie każdego z nich w osobnej linii
                for(Object item : lst.getSelectedValuesList())
                    t.append(item + "\n");
            };
    public Zad22_16() {
        t.setEditable(false);
        setLayout(new FlowLayout());
        // Create Borders for components:
        //czarna ramka góra 1px lewo 1px dół 2px prawo 2px
        Border brd = BorderFactory.createMatteBorder(
                1, 1, 2, 2, Color.BLACK);
        lst.setBorder(brd);
        t.setBorder(brd);
        add(t);
        add(lst);
        // Register event listeners
        lst.addListSelectionListener(ll);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_16(), 250, 375);
    }
}

/*
Program upraszcza przykład List.java (Zad22_16).

Tablica flavors jest przekazywana bezpośrednio do konstruktora JList,
dzięki czemu wszystkie elementy są dostępne od początku i nie trzeba
używać DefaultListModel ani dynamicznie dodawać elementów.

ListSelectionListener reaguje na zmianę zaznaczenia i wyświetla
wybrane elementy JTextArea.
*/