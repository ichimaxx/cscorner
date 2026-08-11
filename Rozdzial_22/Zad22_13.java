import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import myutils.*;
/*
Exercise 13: (3) Modify TextFields.java so that the characters in t2 retain the original
case that they were typed in, instead of automatically being forced to uppercase.
*/
public class Zad22_13 extends JFrame {
    private JButton
            b1 = new JButton("Get Text"),
            b2 = new JButton("Set Text");
    private JTextField
            //t1 tekst zamieniany na wielkie litery
            //t2 tekst zachowujący oryginalną wielkość liter
            //t3 informacje o zdarzeniach
            t1 = new JTextField(30),
            t2 = new JTextField(30),
            t3 = new JTextField(30);
    private String s = "";
    //osobny dokument odpowiedzialny za zamianę tekstu na uppercase
    private UpperCaseDocument ucd = new UpperCaseDocument();
    public Zad22_13() {
        //t1 korzysta z UpperCaseDocument
        t1.setDocument(ucd);
        //DocumentListener reaguje na każdą zmianę zawartości dokumentu
        ucd.addDocumentListener(new T1());
        b1.addActionListener(new B1());
        b2.addActionListener(new B2());
        //ActionListener JTextField (uruchamia się po naciśnięciu enter)
        t1.addActionListener(new T1A());
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(t1);
        add(t2);
        add(t3);
    }
    class T1 implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {}
        //wywoływane po dodaniu tekstu do dokumentu
        public void insertUpdate(DocumentEvent e) {
            //t2 pobiera tekst z bufora, który zachowuje oryginalną wielkość wpisanych liter
            t2.setText(String.valueOf(ucd.sb));
            //t3 pokazuje tekst znajdujący się w t1
            t3.setText("Text: "+ t1.getText());
        }
        //wywoływane po usunięciu tekstu
        public void removeUpdate(DocumentEvent e) {
            //usuwa z oryginalnego bufora dokładnie ten sam fragment,
            //który został usunięty z dokumentu t1
            ucd.sb.delete(e.getOffset(), e.getOffset() + e.getLength());
            //aktualizacja t2 po usunięciu znaków
            t2.setText(String.valueOf(ucd.sb));
        }
    }
    class T1A implements ActionListener {
        private int count = 0;
        //wywoływane po naciśnięciu Enter w t1
        public void actionPerformed(ActionEvent e) {
            t3.setText("t1 Action Event " + count++);
        }
    }
    class B1 implements ActionListener {
        //jeśli nic nie zaznaczono, zapisuje cały tekst z t1
        //w przeciwnym razie tylko zaznaczony fragment
        public void actionPerformed(ActionEvent e) {
            if(t1.getSelectedText() == null)
                s = t1.getText();
            else
                s = t1.getSelectedText();
            t1.setEditable(true);
        }
    }
    class B2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //tymczasowe wyłączenie zmiany tekstu na uppercase
            ucd.setUpperCase(false);
            t1.setText("Inserted by Button 2: " + s);
            ucd.setUpperCase(true);
            t1.setEditable(false);
        }
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_13(), 375, 200);
    }
}
class UpperCaseDocument extends PlainDocument {
    private boolean upperCase = true;
    //przechowuje tekst dokładnie w takiej wielkości liter,
    //w jakiej został wpisany w t1
    StringBuilder sb = new StringBuilder();
    public void setUpperCase(boolean flag) {
        upperCase = flag;
    }
    public void
    insertString(int offset, String str, AttributeSet attSet)
            throws BadLocationException {
        //zapis oryginalnego tekstu na odpowiedniej pozycji przed zmianą go na wielkie litery
        sb.insert(offset,str);
        //tekst przeznaczony dla t1 zostaje zmieniony na uppercase
        if(upperCase)
            str = str.toUpperCase();
        //wstawienie przetworzonego tekstu do właściwego dokumentu
        super.insertString(offset, str, attSet);
    }
}

/*
Program modyfikuje przykład TextFields tak, aby t1 nadal
zamieniało wpisywany tekst na wielkie litery, natomiast t2
zachowywało oryginalną wielkość liter.

UpperCaseDocument przechowuje oryginalny tekst w StringBuilder,
zanim zostanie on zamieniony przez toUpperCase().

DocumentListener aktualizuje t2 po dodaniu lub usunięciu znaków, dzięki czemu
oba pola zawierają ten sam tekst, ale z inną wielkością liter.
*/