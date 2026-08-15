import javax.swing.*;
import java.awt.*;
import myutils.*;
/*
Exercise 22: (7) Create an application using SwingConsole. This should have three
sliders, one each for the red, green, and blue values in java.awt.Color. The rest of the form
should be a JPanel that displays the color determined by the three sliders. Also include noneditable text fields that show the current RGB values.
*/
public class Zad22_22 extends JFrame {
    //panel wyświetlajacy kolor z wartości RGB
    //oraz osobne panele dla każdego slidera
    private JPanel colorPanel, redPanel, greenPanel, bluePanel;
    //slidery sterujące RGB
    private JSlider greenSlider, redSlider, blueSlider;
    //pola pokazujące wartość RGB
    private JTextField redfield, greenfield, bluefield;
    public Zad22_22() {
        //każdy slider przyjmuje wartość od 0 do 255
        //wartość początkowa wynosi 0
        greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        //pola tekstowe na aktualne wartości RGB
        redfield = new JTextField(3);
        greenfield = new JTextField(3);
        bluefield = new JTextField(3);
        //nieedytowalne pola
        redfield.setEditable(false);
        bluefield.setEditable(false);
        greenfield.setEditable(false);
        colorPanel = new JPanel();
        redPanel = new JPanel();
        greenPanel = new JPanel();
        bluePanel = new JPanel();
        //panel pokazujący kolor
        colorPanel.setPreferredSize(new Dimension(200, 200));
        //każda zmiana wartości slidera wywołuje updateColor()
        greenSlider.addChangeListener(e -> updateColor());
        blueSlider.addChangeListener(e -> updateColor());
        redSlider.addChangeListener(e -> updateColor());
        //FlowLayout układa komponenty kolejno od lewej do prawej
        setLayout(new FlowLayout());
        //dodanie panelu wyświetlającego kolor
        add(colorPanel);
        //dodanie etykiet określających RGB
        greenPanel.add(new JLabel("Green:"));
        redPanel.add(new JLabel("Red:"));
        bluePanel.add(new JLabel("Blue:"));
        //dodanie odpowiednich sliderów do panelu
        greenPanel.add(greenSlider);
        redPanel.add(redSlider);
        bluePanel.add(blueSlider);
        redPanel.add(redfield);
        greenPanel.add(greenfield);
        bluePanel.add(bluefield);
        add(redPanel);
        add(greenPanel);
        add(bluePanel);
        //ustawienie początkowych wartości pól i koloru panelu
        updateColor();
    }
    private void updateColor() {
        int green = greenSlider.getValue();
        int red = redSlider.getValue();
        int blue = blueSlider.getValue();
        redfield.setText(red + "");
        greenfield.setText(green + "");
        bluefield.setText(blue + "");
        //tworzy kolor na podstawie aktualnych wartości RGB
        colorPanel.setBackground(new Color(red, green, blue));
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_22(), 1500, 1000);
    }
}

/*
Program tworzy trzy slidery odpowiadające wartościom Red, Green i Blue
w zakresie 0 do 255.
Zmiana dowolnego slidera wywołuje metodę updateColor(), która pobiera aktualne
wartości RGB, wyświetla je w nieedytowalnych JTextField
oraz ustawia odpowiedni kolor tła panelu colorPanel.
Kolor panelu jest więc tworzony dynamicznie na podstawie ustawień trzech sliderów.
*/