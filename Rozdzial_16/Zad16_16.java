import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 16: (3) Starting with CountingGenerator.java, create a SkipGenerator
class that produces new values by incrementing according to a constructor argument. Modify
TestArrayGeneration.java to show that your new class works correctly.
*/
public class Zad16_16 {
    public static class Boolean implements myutils.Generator<java.lang.Boolean> {
        private int razy = 1;
        private boolean value = false;
        public Boolean() {
            this(1);
        }
        public Boolean(int razy) {
            this.razy = razy;
        }
        public java.lang.Boolean next() {
            for(int i = 0; i < razy; i++)
                value = !value; // zamiana na true/false
            return value;
        }
    }
    public static class
    Byte implements myutils.Generator<java.lang.Byte> {
        private int razy = 1;
        private byte value = 0;
        public Byte() {
            this(1);
        }
        public Byte(int razy) {
            this.razy = razy;
        }
        public java.lang.Byte next() {
            byte result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
                value++; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
        return result;
        }
    }
    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    public static class
    Character implements myutils.Generator<java.lang.Character> {
        private int razy = 1;
        public Character() {
            this(1);
        }
        public Character(int razy) {
            this.razy = razy;
        }
        int index = -1;
        public java.lang.Character next() {
            for(int i = 0; i < razy; i++)
                index = (index + 1) % chars.length;
            return chars[index];
        }
    }
    public static class
    String implements myutils.Generator<java.lang.String> {
        private int length = 7;
        private int razy = 1;
        private myutils.Generator<java.lang.Character> cg; // zadeklarowano pole klasy a wartość(generator) zdefiniowano w konstruktorze
        public String() {
            this(7,1);
        }
        public String(int length, int razy) {
            this.length = length;
            this.razy = razy;
            this.cg = new Character(razy); // generator znaków jest tworzony tylko raz w konstruktorze, dzięki temu pamięta swój stan między wywołaniami next(), i nie zaczyna za każdym razem od początku
        }
        public java.lang.String next() {
            char[] buf = new char[length];
                for (int z = 0; z < length; z++)
                    buf[z] = cg.next(); // pobiera kolejny znak z generatora Character z ustawionym krokiem(Character(razy))
            return new java.lang.String(buf);
        }
    }
    public static class
    Short implements myutils.Generator<java.lang.Short> {
        private int razy = 1;
        private short value = 0;
        public Short() {
            this(1);
        }
        public Short(int razy) {
            this.razy = razy;
        }
        public java.lang.Short next() {
            short result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
                value++; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
            return result; }
    }
    public static class
    Integer implements myutils.Generator<java.lang.Integer> {
        private int razy = 1;
        private int value = 0;
        public Integer() {
            this(1);
        }
        public Integer(int razy) {
            this.razy = razy;
        }
        public java.lang.Integer next() {
            int result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
                    value++; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
                return result; }
    }
    public static class
    Long implements myutils.Generator<java.lang.Long> {
        private int razy = 1;
        private long value = 0;
        public Long() {
            this(1);
        }
        public Long(int razy) {
            this.razy = razy;
        }
        public java.lang.Long next() {
            long result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
                value++; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
            return result;
        }
    }
    public static class
    Float implements myutils.Generator<java.lang.Float> {
        private float value = 0;
        private int razy = 1;
        public Float() {
            this(1);
        }
        public Float(int razy) {
            this.razy = razy;
        }
        public java.lang.Float next() {
            float result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
            value += 1.0; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
            return result;
        }
    }
    public static class
    Double implements myutils.Generator<java.lang.Double> {
        private int razy = 1;
        private double value = 0.0;
        public Double() {
            this(1);
        }
        public Double(int razy) {
            this.razy = razy;
        }
        public java.lang.Double next() {
            double result = value; // zapamiętuje aktualną wartość, która zostanie zwrócona przez next()
            for(int i = 0; i < razy; i++)
                value += 1.0; // inkrementacja robiona x razy w zależności ile wpisano w main do konstruktora
            return result;
        }
    }
    public static void main(java.lang.String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(
                java.lang.Boolean.class, new Zad16_16.Boolean(5), size)); // Boolean działa sensownie ze względu na liczbę nieparzystą, gdyby była wartość parzysta, nie było by widocznej zmiany
        println("a1(boolean) = " + Arrays.toString(a1));
        byte[] a2 = ConvertTo.primitive(Generated.array(
                java.lang.Byte.class, new Zad16_16.Byte(6), size));
        println("a2(byte) = " + Arrays.toString(a2));
        char[] a3 = ConvertTo.primitive(Generated.array(
                java.lang.Character.class,
                new Zad16_16.Character(8), size));
        println("a3(char) = " + Arrays.toString(a3));
        short[] a4 = ConvertTo.primitive(Generated.array(
                java.lang.Short.class, new Zad16_16.Short(5), size));
        println("a4(short) = " + Arrays.toString(a4));
        int[] a5 = ConvertTo.primitive(Generated.array(
                java.lang.Integer.class, new Zad16_16.Integer(6), size));
        println("a5(int) = " + Arrays.toString(a5));
        long[] a6 = ConvertTo.primitive(Generated.array(
                java.lang.Long.class, new Zad16_16.Long(3), size));
        println("a6(long) = " + Arrays.toString(a6));
        float[] a7 = ConvertTo.primitive(Generated.array(
                java.lang.Float.class, new Zad16_16.Float(5), size));
        println("a7(float) = " + Arrays.toString(a7));
        double[] a8 = ConvertTo.primitive(Generated.array(
                java.lang.Double.class, new Zad16_16.Double(2), size));
        println("a8(double) = " + Arrays.toString(a8));
        myutils.Generator<?> g = new String(7,2); // wywołanie generatora String(), ponieważ nie jest typem prymitywnym, nie trzeba używać ConvertTo.primitive()
        println("String = " + g.next() + " \ndrugie wywołanie generatora String (g.next()): " + g.next());
    }
}