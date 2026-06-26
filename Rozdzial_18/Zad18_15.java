import java.io.*;
/*
Exercise 15: (4) Look up DataOutputStream and DataInputStream in the JDK
documentation. Starting with StoringAndRecoveringData.java, create a program that
stores and then retrieves all the different possible types provided by the
DataOutputStream and DataInputStream classes. Verify that the values are stored and
retrieved accurately.
*/
public class Zad18_15 {
    public static void main(String[] args)
            throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeBoolean(true);
        out.writeByte(122);
        out.writeChar('a');
        out.writeFloat(0.5f);
        out.writeInt(133);
        out.writeLong(1335555);
        out.writeShort(133);
        out.writeBytes("BYTE");
        out.writeChars("BYTES");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        // Only readUTF() will recover the
        // Java-UTF String properly:
        System.out.println(in.readUTF());
        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        System.out.println(in.readChar());
        System.out.println(in.readFloat());
        System.out.println(in.readInt());
        System.out.println(in.readLong());
        System.out.println(in.readShort());
        //zapis writeBytes() zapisuje każdy character jako jeden bajt dlatego też stworzono 4 obiekty readByte()
        byte b1 = in.readByte();
        byte b2 = in.readByte();
        byte b3 = in.readByte();
        byte b4 = in.readByte();
        //by wyprintować słowo, użyto pustego cudzysłowa, który powoduje, że java traktuje "+" jako łączenie tekstu
        //i wymusza String traktowania reszty elementów jako String
        System.out.println("" + (char)b1 + (char)b2 + (char)b3 + (char)b4);
        char c1 = in.readChar();
        char c2 = in.readChar();
        char c3 = in.readChar();
        char c4 = in.readChar();
        char c5 = in.readChar();
        //writeChars() zapisuje znaki jako char, więc odczyt jest przez readChar(), nie potrzeba castować
        System.out.println("" + c1 + c2 + c3 + c4 + c5);
        in.close();
    }
}
//Bardzo ważna jest kolejność odczytu po zapisie, jeżeli odczyt będzie w innej kolejności niż zapis,
//nie uda się poprawnie odczytać informacji