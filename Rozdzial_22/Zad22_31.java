import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

import myutils.*;
/*
Exercise 31: (8) Create an "asymptotic progress indicator" that gets slower and slower as
it approaches the finish point. Add random erratic behavior so it will periodically look like it’s
starting to speed up.
*/

public class Zad22_31 extends JFrame {
    //poziom postępu od 0 do 100
    private int x = 0;
    private Random rand = new Random();
    //pasek pokazujący aktualny postęp
    private JProgressBar pb = new JProgressBar();
    //osobne okno monitorujące wartość postępu
    private ProgressMonitor pm = new ProgressMonitor(
            this, "Monitoring Progress", "Test", 0, 100);
    //slider, który używany jest jako model wartości postępu
    private JSlider sb =
            new JSlider(JSlider.HORIZONTAL, 0, 100, x);
    public Zad22_31() {
        //zadanie wykonywane cyklicznie przez Swing Timer
        ActionListener task = e -> {
            int z = rand.nextInt(101);
            //postęp zwiększa się tylko wtedy, gdy wylosowane z jest większe niż x
            //im większe x, tym trudniej spełnić warunek x > x
            //dlatego wskaźnik porusza się coraz wolniej
            if (z > x) {
                x++;
                sb.setValue(x);
                //losowanie chwilowego przyspieszenia, około 30% szans
                int k = rand.nextInt(10);
                if (k < 3) {
                    for(int i = 0; i < 5; i++)
                        if(x < 100)
                    x++;
                    sb.setValue(x);
                }
            }
        };
        //timer wykonuje zadanie co 0.3 sekundy
        Timer timer = new Timer(300, task);
        timer.setRepeats(true);
        timer.start();
        setLayout(new GridLayout(2,1));
        add(pb);
        pm.setProgress(0);
        pm.setMillisToPopup(1000);
        sb.setPaintTicks(true);
        sb.setMajorTickSpacing(20);
        sb.setMinorTickSpacing(5);
        sb.setBorder(new TitledBorder("Slide Me"));
        //ProgressMonitor
        pb.setModel(sb.getModel()); // Share model
        add(sb);
        sb.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pm.setProgress(sb.getValue());
            }
        });
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_31(), 300, 200);
    }
}

/*
Program tworzy asymptotyczne wskaźnik postępu od 0 do 100.

Swing Timer co określony czas losuje wartość od 0 do 100.
Postęp zwiększa się tylko wtedy, gdy wylosowana wartość jest większa
od aktualnej wartości x. Im bliżej x znajduje się wartości 100,
tym mniejsza jest szansa spełnienia tego warunku, dlatego postęp stopniowo zwalnia.

Dodatkowe losowanie daje czasami maksymalnie 5 dodatkowych kroków przez co wskaźnik
okresowo wygląda tak, jakby ponownie przyspieszał.

JSlider i JProgressBar korzystają z tego samego modelu, dlatego
zmiana wartości slidera automatycznie aktualizuje również JProgressBar.
ProgressMonitor jest aktualizowany osobno przez ChangeListener.
*/