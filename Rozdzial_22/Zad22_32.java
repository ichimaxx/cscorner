import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import myutils.*;
/*
Exercise 32: (6) Modify Progress.java so that it does not share models, but instead
uses a listener to connect the slider and progress bar.
*/
public class Zad22_32 extends JFrame {
    private JProgressBar pb = new JProgressBar();
    private ProgressMonitor pm = new ProgressMonitor(
            this, "Monitoring Progress", "Test", 0, 100);
    private JSlider sb =
            new JSlider(JSlider.HORIZONTAL, 0, 100, 60);
    public Zad22_32() {
        setLayout(new GridLayout(2,1));
        add(pb);
        pm.setProgress(0);
        pm.setMillisToPopup(1000);
        sb.setValue(0);
        sb.setPaintTicks(true);
        sb.setMajorTickSpacing(20);
        sb.setMinorTickSpacing(5);
        sb.setBorder(new TitledBorder("Slide Me"));
        add(sb);
        //slider i progress bar nie współdzielą już modelu,
        //listener pobiera wartość slidera i przekazuje ją do pozostałych wskaźników
        sb.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pm.setProgress(sb.getValue());
                pb.setValue(sb.getValue());
            }
        });
    }
    public static void main(String[] args) {
        SwingConsole.run(new Zad22_32(), 300, 200);
    }
}
/*
Program modyfikuje przykład Progress.java (Zad22_32)
W oryginalnym przykładzie JSlider i JProgressBar
korzystały z tego samego modelu przez pb.setModel(sb.getModel()).

W tym rozwiązaniu komponenty nie współdzielą modelu.
ChangeListener reaguje na zmianę wartości JSlider i przekazuje jego aktualną
wartość osobno do JProgressBar o raz ProgressMonitor.

Dzięki temu komponenty są połączone za pomocą listenera,
a nie przez wspólny model.
*/