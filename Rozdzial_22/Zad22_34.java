import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.*;
import myutils.*;
/*
Exercise 34: (4) Modify ColorBoxes.java so that it begins by sprinkling points ("stars")
across the canvas, then randomly changes the colors of those "stars."
*/
class CBox extends JPanel implements Runnable {
    private int pause;
    private static Random rand = new Random();
    private int iloscGwiazd = 20;
    //każda "gwiazda" posiada własny kolor
    private Color[] kolory = new Color[iloscGwiazd];
    //tablica przechowująca stałe pozycje "Gwiazd"
    private Point[] p = new Point[iloscGwiazd];
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Dimension s = getSize();
        for (int i = 0; i < iloscGwiazd; i++) {
            int size = 5;
            //pozycja jest losowana tylko przy pierwszym rysowaniu
            //późniejsze repaint() korzysta z zapisanych point
            if (p[i] == null) {
                int x = rand.nextInt(s.width);
                int y = rand.nextInt(s.height);
                p[i] = new Point(x, y);
            }
            //nadaje początkowy kolor każdej "gwiezdzie"
            if (kolory[i] == null) {
                kolory[i] = new Color(rand.nextInt(0xFFFFFF));
            }
            //każda gwiazda jest rysowana swoim własnym kolorem
            g2d.setColor(kolory[i]);
            g2d.fillOval(p[i].x, p[i].y, size, size);
        }
    }
    public CBox(int pause) { this.pause = pause; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //losowanie jednej z istniejących gwiazd
                int i = rand.nextInt(iloscGwiazd);
                //zmiana koloru tylko wylosowanej gwiazdy
                kolory[i] = new Color(rand.nextInt(0xFFFFFF));
                repaint(); // Asynchronously request a paint()
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch(InterruptedException e) {
            // Acceptable way to exit
        }
    }
}
public class Zad22_34 extends JFrame {
    private int grid = 12;
    private int pause = 50;
    //każdy CBox jest osobnym Runnable
    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    public void setUp() {
        setLayout(new GridLayout(grid, grid));
        for (int i = 0; i < grid * grid; i++) {
            //utworzenie kolejnego pola zawierającego "gwiazdy"
            CBox cb = new CBox(pause);
            add(cb);
            //uruchomienie CBox jako osobnego zadania
            exec.execute(cb);
        }
    }

    public static void main(String[] args) {
        Zad22_34 boxes = new Zad22_34();
        if (args.length > 0)
            boxes.grid = Integer.parseInt(args[0]);
        if (args.length > 1)
            boxes.pause = Integer.parseInt(args[1]);
        boxes.setUp();
        SwingConsole.run(boxes, 500, 400);
    }
}

/*
Stan obiektu, który ma pozostać niezmieniony między kolejnymi repaint(),
musi być przechowywany poza paintComponent(), dlatego pozycje gwiazd
są zapisywane w tablicy Point[].

Oddzielenie pozycji i kolorów pozwala zachować stałe położenie gwiazd,
a jednocześnie niezależnie zmieniać ich wygląd.

Tablice Point[] i Color[] są ze sobą powiązane przez indeks:
ten sam indeks oznacza pozycję i kolor tej samej gwiazdy.
*/