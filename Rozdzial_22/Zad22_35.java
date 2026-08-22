package Rozdzial_22;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/*
Exercise 35: (6) Locate and download one or more of the free GUI builder development
environments available on the Internet, or use a commercial product if you own one.
Discover what is necessary to add BangBean to this environment and to use it.
*/
public class Zad22_35 extends JPanel implements Serializable {
    //aktualna pozycja kursora
    private int xm, ym;
    //properties JavaBeana
    private int cSize = 20; // Circle size
    private String text = "Bang!";
    private int fontSize = 48;
    private Color tColor = Color.RED;
    //listener reagujący na zdarzenia generowane przez Bean
    private ActionListener actionListener;
    //konstruktor bezargumentowy, pozwala utworzyć komponent bez podawania parametrów
    public Zad22_35() {
        addMouseListener(new ML());
        addMouseMotionListener(new MML());
    }
    //pary get/set tworzą properties JavaBean
    //GUI Builder może je wykryć i pokazać w panelu właściwości
    public int getCircleSize() { return cSize; }
    public void setCircleSize(int newSize) {
        cSize = newSize;
    }
    public String getBangText() { return text; }
    public void setBangText(String newText) {
        text = newText;
    }
    public int getFontSize() { return fontSize; }
    public void setFontSize(int newSize) {
        fontSize = newSize;
    }
    public Color getTextColor() { return tColor; }
    public void setTextColor(Color newColor) {
        tColor = newColor;
    }
    //rysowanie okręgu w aktualnej pozycji kursora
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval(xm - cSize/2, ym - cSize/2, cSize, cSize);
    }
    //Bean obsługuje pojedynczy ActionListener - unicast
    // This is a unicast listener, which is
    // the simplest form of listener management:
    public void addActionListener(ActionListener l)
            throws TooManyListenersException {
        if(actionListener != null)
            throw new TooManyListenersException();
        actionListener = l;
    }
    public void removeActionListener(ActionListener l) {
        actionListener = null;
    }
    //kliknięcie wyświetla tekst i generuje ActionEvent
    class ML extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(tColor);
            g.setFont(
                    new Font("TimesRoman", Font.BOLD, fontSize));
            int width = g.getFontMetrics().stringWidth(text);
            g.drawString(text, (getSize().width - width) /2,
                    getSize().height/2);
            g.dispose();
            // Call the listener’s method:
            if(actionListener != null)
                actionListener.actionPerformed(
                        new ActionEvent(Zad22_35.this,
                                ActionEvent.ACTION_PERFORMED, null));
        }
    }
    //ruch myszy zmienia pozycję okręgu i odświeża komponent
    class MML extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
            xm = e.getX();
            ym = e.getY();
            repaint();
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}

/*
Użyto pluginu Swing UI Designer z Intellij Idea jako GUI Buildera.

Własny komponent Swing może zostać dodany do Palette i używany
w formularzu tak jak standardowe komponenty Swing.

GUI Builder potrafi rozpoznać properties JavaBeana
na podstawie konwencji getter/setter, np. getBangText()/setBangText().

IDE i GUI Builder jedynie pomaga projektować GUI i łączyć komponenty z kodem.

Można to traktować jako wizualny podgląd jak będzie wyglądać gotowa aplikacja, lub
dany fragment interfejsu używany później w oknie programu.
*/