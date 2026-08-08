import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.regex.*;
import myutils.*;
/*
Exercise 9: (5) Starting with ShowAddListeners.java, create a program with the full
functionality of typeinfo.ShowMethods.java.
*/
public class Zad22_9 extends JFrame {
    private JTextField name = new JTextField(25);
    private JTextArea results = new JTextArea(40, 65);
    private JTextField search = new JTextField(15);
    private static Pattern p = Pattern.compile("\\w+\\.");
    class NameL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nm = name.getText().trim();
            String find = search.getText().trim();
            if(nm.length() == 0) {
                results.setText("No match");
                return;
            }
            Class<?> c;
            int lines = 0;
            try {
                results.setText("");
                c = Class.forName(nm);
                Method[] methods = c.getMethods();
                Constructor[] ctors = c.getConstructors();
                if(find.length() == 0) {
                    for(Method method : methods)
                        //wyniki są dodawane do pola oraz tworzona jest następna linia
                        results.append(p.matcher(method.toString()).replaceAll("")+ "\n");
                    for(Constructor ctor : ctors)
                        results.append(p.matcher(ctor.toString()).replaceAll("")+ "\n");
                    lines = methods.length + ctors.length;
                } else {
                    for(Method method : methods)
                        if(method.toString().indexOf(find) != -1) {
                            results.append(p.matcher(method.toString()).replaceAll("") + "\n");
                            lines++;

                        }
                    for(Constructor ctor : ctors)
                        if(ctor.toString().indexOf(find) != -1) {
                            results.append(p.matcher(ctor.toString()).replaceAll("")+ "\n");
                            lines++;
                        }
                }
                results.append("Lines: " + lines);
            } catch(ClassNotFoundException ex) {
                results.setText("No such class: " + ex);
            }
        }
    }
    public Zad22_9() {
        NameL nameListener = new NameL();
        //oba pola korzystają z tego samego ActionListener
        //naciśnięcie Enter uruchamia wyszukiwanie
        name.addActionListener(nameListener);
        search.addActionListener(nameListener);
        //panel używa domyślnego FlowLayout,
        //który układa elementy kolejno od lewej do prawej
        JPanel top = new JPanel();
        //pierwszy rząd - nazwa klasy
        //np . [ java.lang.String ]
        top.add(new JLabel("Class name (press Enter):"));
        top.add(name);
        //drugi rząd, opcjonalny filtr wyszukiwania
        //który, zawęża listę metod i konstruktorów
        //np. [ char ]
        top.add(new JLabel("Search (press Enter):"));
        top.add(search);

        add(BorderLayout.NORTH, top);
        add(new JScrollPane(results));
        // Initial data and test:
        name.setText("");
        nameListener.actionPerformed(
                new ActionEvent("", 0 ,""));
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_9(), 900, 400);
    }
}

/*
Program jest graficzną wersją ShowMethods.java

User wpisuje pełną nazwę klasy, a program z pomocą refleksji
pobiera jej publiczne metody i konstruktory.

Pole Search działa jako opcjonalny filtr:
jeżeli jest puste, wyświetlane są wszystkie metody i konstruktory,
a jeżeli zawiera tekst, program pokazuje tylko te elementy,
których opis zawiera podane słowo.

Pattern usuwa kwalifikatory z nazw,
a licznik Lines pokazuje liczbę wyświetlonych wyników.
 */