import java.io.*;
import java.util.*;
/*
Exercise 30: (1) Repair the program CADState.java as described in the text.
*/
abstract class ShapeCAD implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;
    public abstract void setColor(int newColor);
    public abstract int getColor();
    public ShapeCAD(int xVal, int yVal, int dim) {
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }
    public String toString() {
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }
    public static ShapeCAD randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch(counter++ % 3) {
            default:
            case 0: return new CircleCAD(xVal, yVal, dim);
            case 1: return new SquareCAD(xVal, yVal, dim);
            case 2: return new LineCAD(xVal, yVal, dim);
        }
    }
}
class CircleCAD extends ShapeCAD {
    private static int color = RED;
    public CircleCAD(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }
    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException { os.writeInt(color); }
    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException { color = os.readInt(); }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}
class SquareCAD extends ShapeCAD {
    private static int color;
    public SquareCAD(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
        color = RED;
    }
    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException { os.writeInt(color); }
    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException { color = os.readInt(); }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}
class LineCAD extends ShapeCAD {
    private static int color = RED;
    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException { os.writeInt(color); }
    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException { color = os.readInt(); }
    public LineCAD(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}
public class Zad18_30 {
    public static void main(String[] args) throws Exception {
        List<ShapeCAD> shapes = new ArrayList<ShapeCAD>();
        // Make some shapes:
        for(int i = 0; i < 10; i++)
            shapes.add(ShapeCAD.randomFactory());
        // Set all the static colors to GREEN:
        for(int i = 0; i < 10; i++)
            ((ShapeCAD)shapes.get(i)).setColor(ShapeCAD.GREEN);
        // Save the state vector:
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("CADState.out"));
        CircleCAD.serializeStaticState(out);
        SquareCAD.serializeStaticState(out);
        LineCAD.serializeStaticState(out);
        out.writeObject(shapes);
        out.close();
        // Display the shapes:
        System.out.println(shapes);
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("CADState.out"));
        // Read in the same order they were written:
        CircleCAD.deserializeStaticState(in);
        SquareCAD.deserializeStaticState(in);
        LineCAD.deserializeStaticState(in);
        shapes = (List<ShapeCAD>) in.readObject();
        System.out.println(shapes);
    }
}
/*
wartości static nie zapisują się automatycznie podczas serializacji i trzeba to zrobić za pomocą serializeStaticState()
przed naprawą zadania output odczytu serializacji był taki:
class Circlecolor[1] xPos[58] yPos[55] dim[93]
, class Squarecolor[0] xPos[61] yPos[61] dim[29]
, class Linecolor[3] xPos[68] yPos[0] dim[22]
, class Circlecolor[1] xPos[7] yPos[88] dim[28]
, class Squarecolor[0] xPos[51] yPos[89] dim[9]
, class Linecolor[3] xPos[78] yPos[98] dim[61]
, class Circlecolor[1] xPos[20] yPos[58] dim[16]
, class Squarecolor[0] xPos[40] yPos[11] dim[22]
, class Linecolor[3] xPos[4] yPos[83] dim[6]
, class Circlecolor[1] xPos[75] yPos[10] dim[42]

3 - to GREEN, a pole color w programie jest static
Kolory wszystkich figur były zmienione na Green(3), jednak serializeStaticState() użyty był tylko w kształcie Line
dlatego tylko tamta wartość została zapisana.

*/