import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 30: (3) Write a program that shows the use of HTML text on all the items from
the previous paragraph.
*/
public class Zad22_30 extends JFrame {
    //JButton z tekstem sformatowanym za pomocą HTML
    private JButton b = new JButton(
            "<html><b><font size=+2>" +
                    "<center>Hello!<br><i>Press me now!");
    //element menu z tekstem HTML
    private JMenuItem menu = new JMenuItem("<html><i><font size=+4>Kapow MENU ITEM!");
    //panel z zakładkami
    private JTabbedPane  tp = new JTabbedPane();
    //pasek menu i jego główna pozycja
    static JMenuBar menuBar = new JMenuBar();
    //JRadioButton i JCheckBox z tekstem HTML
    static JMenu jm = new JMenu("File");
    private JRadioButton jrb = new JRadioButton("<html><i><font size=+4>Kapow RADIO BUTTON!");
    private JCheckBox jcb = new JCheckBox("<html><i><font size=+4>Kapow CHECKBOX!");
    //panel przechowujący JTabbedPane
    JPanel jf = new JPanel();
    public Zad22_30() {
        //JmenuItem dodano do menu, a następnie menu do paska menu
        jm.add(menu);
        menuBar.add(jm);
        //listener do JButton, JLabel używa HTML
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(new JLabel("<html>" +
                        "<i><font size=+4>Kapow REGURABUTTON!"));
                // ponowne rozmieszczanie komponentów po dodaniu JLabel
                validate();
            }
        });
        //listener do JRadioButton, takie samo użycie co JButton
        jrb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(new JLabel(
                        "<html><i><font size=+4>Kapow RADIO BUTTON CLICK!"));
                // Force a re-layout to include the new label:
                validate();
            }
        });
        //zawartości poszczególnych zakładek(JTabbedPane)
        JPanel page1 = new JPanel();
        page1.add(new JLabel("<html><i><font size=+4>Kapow INSIDE TAB1!"));
        JPanel page2 = new JPanel();
        page2.add(new JLabel("<html><i><font size=+4>Kapow INSIDE TAB2!"));
        JPanel page3 = new JPanel();
        page3.add(new JLabel("<html><i><font size=+4>Kapow INSIDE TAB3!"));
        //nazwy zakładek
        tp.addTab("<html><i><font size=+4>Kapow TAB1!", page1);
        tp.addTab("<html><i><font size=+4>Kapow TAB2!", page2);
        tp.addTab("<html><i><font size=+4>Kapow TAB3!", page3);
        jf.add(tp);
        //tooltipy wyświetlają HTML po najechaniu myszką na JButton lub JRadioButton
        b.setToolTipText("<html><i><font size=+4>Kapow TOOLTIP!");
        jrb.setToolTipText("<html><i><font size=+4>Kapow TOOLTIP!");
        jcb.setToolTipText("<html><i><font size=+4>Kapow TOOLTIP!");
        //listener do CheckBox zasada działania taka jak JButton i JRadioButton
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(new JLabel("<html><i><font size=+4>Kapow CHECKBOX CLICK!"));
                // Force a re-layout to include the new label:
                validate();
            }
        });
        setLayout(new FlowLayout());
        add(b);
        add(jrb);
        add(jcb);
        add(jf);
        add(menuBar);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_30(), 600, 600);
    }
}

/*
Program pokazuje możliwość użycia kodu HTML w komponentach Swing.

HTML został użyty w JButton, JMenuItem, JTabbedPane, JRadioButton,
JCheckBox i JToolTip.

Znacznik <html> informuje Swing, że tekst komponentu powinien być zinterpretowany
jako HTML.

Dzięki temu, można zmieniać rozmiar i styl tekstu, tworzyć nowe linie oraz formatować
napisy bez ręcznego rysowania.

W Swingu wystarczy, że tekst zaczyna się od <html> nie trzeba kończyć tekstu </html>.
 */