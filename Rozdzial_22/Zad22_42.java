import myutils.*;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import java.util.concurrent.*;
import java.util.*;

/*
Exercise 42: (4) Modify swt/ColorBoxes.java so that it begins by sprinkling points
("stars") across the canvas, then randomly changes the colors of those "stars."
*/
class CBox_2 extends Canvas implements Runnable {
    //liczba "gwiazd" na każdym Canvas
    private int iloscGwiazd = 20;
    //każda gwiazda posiada własny kolor zapisany jako RGB
    private RGB[] kolory = new RGB[iloscGwiazd];
    //pozycje gwiazd
    private Point[] p = new Point[iloscGwiazd];
    class CBoxPaintListener implements PaintListener {
        private static Random rand = new Random();
        //każda "gwiazda" posiada własny kolor
        public void paintControl(PaintEvent e) {
            //rozmiar canvas
            Point s = getSize();
            for (int i = 0; i < iloscGwiazd; i++) {
                int size = 5;
                //pozycja jest losowana tylko przy pierwszym rysowaniu
                //późniejsze redraw() korzysta z zapisanych point
                if (p[i] == null) {
                    int x = rand.nextInt(s.x);
                    int y = rand.nextInt(s.y);
                    p[i] = new Point(x, y);
                }
                //nadaje początkowy kolor każdej "gwiezdzie"
                if (kolory[i] == null) {
                    kolory[i] = newColor();
                }
                Color color = new Color(e.display, kolory[i]);
                //każda gwiazda jest rysowana swoim własnym kolorem
                e.gc.setBackground(color);
                e.gc.fillOval(p[i].x, p[i].y, size, size);
                color.dispose();
            }
        }
    }
    //tworzy losowy kolor jako typ RGB
    private static Random rand = new Random();
    private static RGB newColor() {
        return new RGB(rand.nextInt(255),
                rand.nextInt(255), rand.nextInt(255));
    }
    //czas pomiędzy zmianami kolorów
    private int pause;
    //PaintListener odpowiada za rysowanie gwiazd na Canvas
    public CBox_2(Composite parent, int pause) {
        super(parent, SWT.NONE);
        this.pause = pause;
        addPaintListener(new CBoxPaintListener());
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //w każdej iteracji wybierana jest losowa gwiazda
                int i = rand.nextInt(iloscGwiazd);
                //wątek roboczy
                getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        //zmienia się kolor wylosowanej gwiazdy, pozycja bez zmian
                        kolory[i] = newColor();
                        try { redraw(); } catch(SWTException e) {}
                        // SWTException is OK when the parent
                        // is terminated from under us.
                    }
                });
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch(InterruptedException e) {
            // Acceptable way to exit
        } catch(SWTException e) {
            // Acceptable way to exit: our parent
            // was terminated from under us.
        }
    }
}
public class Zad22_42 implements SWTApplication  {
    private int grid = 12;
    private int pause = 50;
    //tworzy siatkę jednakowych Canvas
    public void createContents(Composite parent) {
        GridLayout gridLayout = new GridLayout(grid, true);
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        parent.setLayout(gridLayout);
        //każdy CBox jest osobnym
        //zadaniem wykonywanym przez pulę wątków daemon
        ExecutorService exec = new DaemonThreadPoolExecutor();
        for(int i = 0; i < (grid * grid); i++) {
            final CBox_2 cb = new CBox_2(parent, pause);
            cb.setLayoutData(new GridData(GridData.FILL_BOTH));
            exec.execute(cb);
        }
    }
    public static void main(String[] args) {
        Zad22_42 boxes = new Zad22_42();
        if(args.length > 0)
            boxes.grid = Integer.parseInt(args[0]);
        if(args.length > 1)
            boxes.pause = Integer.parseInt(args[1]);
        SWTConsole.run(boxes, 500, 400);
    }
}
/*
Zadanie rozwija przykład ColorBoxes z książki.
Zamiast zmieniać kolor całego Canvas, każdy CBox zawiera wiele "gwiazd"
Zadanie na wzór Zad22_34 tylko zamiast Swing użyto SWT.

Różnica pomiędzy Swingiem a SWT dotyczy głównie sposobu odświeżania GUI.

W Swing można wywołać repaint() bezpośrednio z wątku roboczego,
ponieważ repaint() nie rysuje komponentu od razu, tylko zgłasza
potrzebę jego późniejszego odmalowania przez wątek zdarzeń Swing.

W SWT run() działa w osobnym wątku, ale redraw() nie powinno być
wywoływane bezpośrednio z tego wątku. Dlatego zadanie przekazywane jest do
wątku SWT przez Display.asyncExec(), a dopiero tam wykonywane jest redraw().
 */