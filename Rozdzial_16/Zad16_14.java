import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 14: (6) Create an array of each primitive type, then fill each array by using
CountingGenerator. Print each array.
*/
public class Zad16_14 {
    public static void main(String[] args){
        int size = 12;
        char[] c;
        int[] i;
        float[] f;
        double[] d;
        boolean[] b;
        byte[] bt;
        long[] l;
        short[] s;
        //zadeklarowano zmienne dla tablic typów prymitywnych
        c = ConvertTo.primitive(Generated.array(Character.class, new myutils.CountingGenerator.Character(), size));
        println("char[]: " + Arrays.toString(c));
        //kopiowanie stworzonej tablicy Character[] do prymitywnej tablicy której typ zadeklarowano na początku
        i = ConvertTo.primitive(Generated.array(Integer.class, new myutils.CountingGenerator.Integer(), size));
        println("int[]: " + Arrays.toString(i));
        f = ConvertTo.primitive(Generated.array(Float.class, new myutils.CountingGenerator.Float(), size));
        println("float[]: " + Arrays.toString(f));
        d = ConvertTo.primitive(Generated.array(Double.class, new myutils.CountingGenerator.Double(), size));
        println("double[]: " + Arrays.toString(d));
        b = ConvertTo.primitive(Generated.array(Boolean.class, new myutils.CountingGenerator.Boolean(), size));
        println("boolean[]: " + Arrays.toString(b));
        bt = ConvertTo.primitive(Generated.array(Byte.class, new myutils.CountingGenerator.Byte(), size));
        println("byte[]: " + Arrays.toString(bt));
        l = ConvertTo.primitive(Generated.array(Long.class, new myutils.CountingGenerator.Long(), size));
        println("long[]: " + Arrays.toString(l));
        s = ConvertTo.primitive(Generated.array(Short.class, new myutils.CountingGenerator.Short(), size));
        println("short[]: " + Arrays.toString(s));
        //Generated.array() tworzy Character[], a ConvertTo.primitive() tworzy nową char[]
    }
}
