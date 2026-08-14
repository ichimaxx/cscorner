import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 19: (3) Modify Menus.java to use radio buttons instead of check boxes on the
menus.
*/
public class Zad22_19 extends JFrame {
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    private JTextField t = new JTextField("No flavor", 30);
    //pierwszy pasek menu z dwóch w programie
    private JMenuBar mb1 = new JMenuBar();
    //wyszczególnione menu i submenu
    private JMenu
            f = new JMenu("File"),
            m = new JMenu("Flavors"),
            s = new JMenu("Safety");
    // grupowanie radiobutton, tak aby tylko jeden z nich mógł byc zaznaczony w danym momencie
    private ButtonGroup group = new ButtonGroup();
    private JRadioButtonMenuItem[] safety = {
            new JRadioButtonMenuItem("Guard"),
            new JRadioButtonMenuItem("Hide")
    };

    private JMenuItem[] file = { new JMenuItem("Open") };
    // A second menu bar to swap to:
    private JMenuBar mb2 = new JMenuBar();
    private JMenu fooBar = new JMenu("fooBar");
    //elementy drugiego menu
    //Foo i Bar mają od razu przypisane mnemoniki
    private JMenuItem[] other = {
            // Adding a menu shortcut (mnemonic) is very
            // simple, but only JMenuItems can have them
            // in their constructors:
            new JMenuItem("Foo", KeyEvent.VK_F),
            new JMenuItem("Bar", KeyEvent.VK_A),
            // No shortcut:
            new JMenuItem("Baz"),
    };
    private JButton b = new JButton("Swap Menus");
    //listener zmieniający aktualny pasek menu
    class BL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JMenuBar m = getJMenuBar();
            setJMenuBar(m == mb1 ? mb2 : mb1);
            validate(); // Refresh the frame
        }
    }
    //listener obsługujący element Open
    class ML implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //pobiera JMenuItem, który wywołał zdarzenie
            JMenuItem target = (JMenuItem)e.getSource();
            //pobranie komendy przypisanej do elementu menu
            String actionCommand = target.getActionCommand();
            if(actionCommand.equals("Open")) {
                //pobranie aktualnego tekstu z pola
                String s = t.getText();
                boolean chosen = false;
                //sprawdzenie, czy tekst odpowiada jednemu z dostępnych smaków
                for(String flavor : flavors)
                    if(s.equals(flavor))
                        chosen = true;
                if(!chosen)
                    t.setText("Choose a flavor first!");
                else
                    t.setText("Opening " + s + ". Mmm, mm!");
            }
        }
    }
    //listener dla smaków(flavors)
    class FL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JMenuItem target = (JMenuItem)e.getSource();
            t.setText(target.getText());
        }
    }
    // Alternatively, you can create a different
    // class for each different MenuItem. Then you
    // don’t have to figure out which one it is:
    class FooL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t.setText("Foo selected");
        }
    }
    class BarL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t.setText("Bar selected");
        }
    }
    class BazL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t.setText("Baz selected");
        }
    }
    //ItemListener reagujący na zmianę stanu radio buttons
    class CMIL implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            JRadioButtonMenuItem target =
                    (JRadioButtonMenuItem)e.getSource();
            String actionCommand = target.getActionCommand();
            if(actionCommand.equals("Guard"))
                t.setText("Guard the Ice Cream! " +
                        "Guarding is true!\n" +
                        "Hide the Ice Cream! " +
                        "Is it hidden? No!");
            else if(actionCommand.equals("Hide"))
                t.setText("Hide the Ice Cream! " +
                        "Is it hidden? Yes!\n" +
                        "Guard the Ice Cream! " +
                        "Guarding is false!");
        }
    }
    public Zad22_19() {
        ML ml = new ML();
        CMIL cmil = new CMIL();
        safety[0].setActionCommand("Guard");
        safety[0].setMnemonic(KeyEvent.VK_G);
        safety[0].addItemListener(cmil);
        safety[1].setActionCommand("Hide");
        safety[1].setMnemonic(KeyEvent.VK_H);
        safety[1].addItemListener(cmil);
        other[0].addActionListener(new FooL());
        other[1].addActionListener(new BarL());
        other[2].addActionListener(new BazL());
        FL fl = new FL();
        int n = 0;
        for(String flavor : flavors) {
            JMenuItem mi = new JMenuItem(flavor);
            mi.addActionListener(fl);
            m.add(mi);
            // Add separators at intervals:
            if((n++ + 1) % 3 == 0)
                m.addSeparator();
        }
        //dodanie radio buttons do jednej grupy oraz do submenu Safety
        for(JRadioButtonMenuItem sfty : safety) {
            group.add(sfty);
            s.add(sfty);
        }
        //mnemonik dla menu Safety
        s.setMnemonic(KeyEvent.VK_A);
        f.add(s);
        f.setMnemonic(KeyEvent.VK_F);
        for(int i = 0; i < file.length; i++) {
            file[i].addActionListener(ml);
            f.add(file[i]);
        }
        //dodanie file i flavors do pierwszego paska menu
        mb1.add(f);
        mb1.add(m);
        //ustawienie pierwszego paska menuj jako aktywnego
        setJMenuBar(mb1);
        t.setEditable(false);
        add(t, BorderLayout.CENTER);
        // Set up the system for swapping menus:
        b.addActionListener(new BL());
        b.setMnemonic(KeyEvent.VK_S);
        add(b, BorderLayout.NORTH);
        //dodane foo, bar i baz do drugiego menuj
        for(JMenuItem oth : other)
            fooBar.add(oth);
        fooBar.setMnemonic(KeyEvent.VK_B);
        //dodanie fooBar do drugiego paska menu
        mb2.add(fooBar);
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_19(), 300, 200);
    }
}

/*
Program modyfikuje przykład Menus.java, zastępując JCheckBoxMenuItem
elementami JRadioButtonMenuItem.

Radio buttony Guard i Hide są dodane do ButtonGroup, dzięki czemu w danym momencie,
może byc zaznaczona tylko jedna z tych opcji.
 */