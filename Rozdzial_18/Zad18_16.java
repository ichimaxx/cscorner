import java.io.*;
/*
Exercise 16: (2) Look up RandomAccessFile in the JDK documentation. Starting with
UsingRandomAccessFile.java, create a program that stores and then retrieves all the
different possible types provided by the RandomAccessFile class. Verify that the values
are stored and retrieved accurately.
*/
public class Zad18_16 {
    static String file = "rtest.dat";
    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        System.out.println("Value 1 : " + rf.readDouble());
        System.out.println("Value 2 : " + rf.readBoolean());
        System.out.println("Value 3 : " + rf.readByte());
        System.out.println("Value 4 : " + rf.readFloat());
        System.out.println("Value 5 : " + rf.readInt());
        byte z1 = rf.readByte();
        byte z2 = rf.readByte();
        byte z3 = rf.readByte();
        byte z4 = rf.readByte();
        System.out.println("Value 6 : " + (char)z1 + (char)z2 + (char)z3 + (char)z4);
        System.out.println("Value 7 : " + rf.readLong());
        System.out.println("Value 8 : " + rf.readChar());
        System.out.println("Value 9 : " + rf.readShort());
        char x1 = rf.readChar();
        char x2 = rf.readChar();
        char x3 = rf.readChar();
        char x4 = rf.readChar();
        System.out.println("Value 10 : " + x1 + x2 + x3 + x4);
        System.out.println(rf.readUTF());
        rf.close();
    }
    public static void main(String[] args)
            throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        rf.writeDouble(1.414);
        rf.writeBoolean(false);
        rf.writeByte(41);
        rf.writeFloat(0.3f);
        rf.writeInt(44);
        rf.writeBytes("Bytt");
        rf.writeLong(51515151L);
        rf.writeChar('a');
        rf.writeShort(44);
        rf.writeChars("Byxt");
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file, "rw");
        /*
        Aby dojść do pozycji long w liście, trzeba policzyć, ile bajtów mają wszystkie typy przed pozycją long:
        Double: 8 byte
        Boolean: 1 byte
        Byte: 1 byte
        Float: 4 byte
        Int: 4 byte
        "Bytt" zapisany w writeBytes(): 4 byte, w tym przypadku 4, ponieważ przechowywany tekst jest złożony z 4 bajtów
        Razem: 8+1+1+4+4+4 = 22
        */
        rf.seek(22);
        rf.writeLong(474544L);
        rf.close();
        display();
    }
}