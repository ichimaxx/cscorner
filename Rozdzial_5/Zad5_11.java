import static myutils.Skrocenie_Print.print;
/*
Exercise 11: (4) Modify the previous exercise so that your finalize( ) will always be
called.
*/
class Zad5_11 {
    protected void finalize() {
        print("finalize() called");
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1_000_000; i++)   // 1 dużo obiektów
            new Zad5_11();
        System.gc();                         // 2 prośba o GC
        System.runFinalization();            // 3 prośba o finalizację
        Thread.sleep(200);                   // 4 daj czas skonczyc
    }
}


//               ODPALAJ Z FLAGA TYPU:            java -Xmx10m Rozdzial_5.Zad5_11         czyli limit 10mb