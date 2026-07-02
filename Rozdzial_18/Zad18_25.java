import java.io.*;
import java.nio.*;
import java.util.*;
import java.nio.channels.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 25: (6) Experiment with changing the ByteBuffer.allocate( ) statements in
the examples in this chapter to ByteBuffer.allocateDirect( ). Demonstrate performance
differences, but also notice whether the startup time of the programs noticeably changes.
*/
public class Zad18_25 {

    private static final int powtorki = 1_000_000;
    static String filename1 = "source.tmp";
    static String filename2 = "copy1.tmp";
    static String filename3 = "copy2.tmp";
    //pojemność pliku testowego i bufora to 1gb
    static final int length = 1024 * 1024 * 1024;
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.remaining() >= 2) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }
    //metoda kopiująca ze źródła do pliku
    private static void kopiarka(ByteBuffer buffer, String source, String target) throws IOException {
        try (
                //tworzy kanał in i out (source i target) - czyli in to plik źródłowy i out to plik gdzie będzie kopia
                FileChannel in = new FileInputStream(source).getChannel();
                FileChannel out = new FileOutputStream(target).getChannel()
        ) {
            //dopóki read z buffer(rozmiar buforu) nie zwróci -1 iteruje
            while (in.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining())
                    out.write(buffer);
                buffer.clear();
            }
        }
    }
    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.print(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration / 1.0e9);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {
        new Tester("1_000_000x ByteBuffer.allocate()") {
            public void test() throws IOException {
                char[] data = "UsingBuffers".toCharArray();
                for (int i = 0; i < powtorki; i++) {
                    ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
                    CharBuffer cb = bb.asCharBuffer();
                    cb.put(data);
                    cb.flip();
                    symmetricScramble(cb);
                }
            }
        },
        new Tester("1_000_000x ByteBuffer.allocateDirect()") {
            public void test() throws IOException {
                char[] data = "UsingBuffers".toCharArray();
                for (int i = 0; i < powtorki; i++) {
                    ByteBuffer bb = ByteBuffer.allocateDirect(data.length * 2);
                    CharBuffer cb = bb.asCharBuffer();
                    cb.put(data);
                    cb.flip();
                    symmetricScramble(cb);
                }
            }
        },
        new Tester("1x 1gb ByteBuffer.allocate()") {
            public void test() throws IOException {
                ByteBuffer bb = ByteBuffer.allocate(length);
                kopiarka(bb, filename1, filename2);
            }
        },
        new Tester("1x 1gb ByteBuffer.allocateDirect()") {
            public void test() throws IOException {
                ByteBuffer bb = ByteBuffer.allocateDirect(length);
                kopiarka(bb, filename1, filename3);
            }
        }
    };

    public static void main(String[] args) throws IOException {
        //stworzenie pliku source.tmp
        //nie trzeba tworzyć copy1 i copy2, bo FileOutputStream(target) nadpisze te pliki
        RandomAccessFile raf = new RandomAccessFile(filename1, "rw");
        raf.setLength(length);
        raf.close();
        for(Tester test : tests)
            test.runTest();
    }
}

/*
W testach z milionem małych buforów allocateDirect() jest wolniejsze, bo direct buffer ma większy koszt utworzenia.
W teście kopiowania pliku przez FileChannel allocateDirect() był szybszy, ale ogólnie wyniki na innych PC mogą być różne,
zależnie od systemu, cache dysku i rozmiar bufora. Direct buffer ma największy sens właśnie przy rzeczywistym I/O.
*/