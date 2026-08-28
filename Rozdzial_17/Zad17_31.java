import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 31: (5) Create a container that encapsulates an array of String, and that only
allows adding Strings and getting Strings, so that there are no casting issues during use. If
the internal array isn’t big enough for the next add, your container should automatically
resize it. In main( ), compare the performance of your container with an
ArrayList<String>.
*/
class NowyCon {
    private int index = 0;
    private String[] ara = new String[1];
    public int size() {
        return index;
    }
    public String toString() {
        return Arrays.toString(Arrays.copyOf(ara, index));
    }
    public boolean add(String o) {
        int indeka = 0;
        if (o == null) { // jeśli key będzie null, wyrzuci wyjątek
            throw new NullPointerException();
        }
        if (index == ara.length) {
            //tablica jest powiększana kilka razy, aby nie kopiować jej przy każdym add()
            String[] aras = new String[ara.length * 5];
            for (String ok : ara) {
                aras[indeka] = ok;
                indeka++;
            }
            ara = aras;
        }
        ara[index] = o;
        index++;
        return true;
    }
    public String get(int indeks) {
        if (indeks >= index || indeks < 0) {
            throw new IndexOutOfBoundsException();
        }
        return ara[indeks];
    }
    public void clear() {
        index = 0;
    }
}
public class Zad17_31 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test_2<List<String>>> tests =
            new ArrayList<Test_2<List<String>>>();
    // osobny test dla nowo stworzonego kontenera NowyCon
    static List<Test_2<NowyCon>> conTests =
            new ArrayList<Test_2<NowyCon>>();

    static {
        tests.add(new Test_2<List<String>>("add") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), listSize);
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(arrayes[j]);
                }
                return loops * listSize;
            }
        });
        conTests.add(new Test_2<NowyCon>("add") {
            int test(NowyCon list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), listSize);
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(arrayes[j]);
                }
                return loops * listSize;
            }
        });
        tests.add(new Test_2<List<String>>("get") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
        conTests.add(new Test_2<NowyCon>("get") {
            int test(NowyCon list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
    }

    static class ListTester extends Tester<List<String>> {
        public ListTester(List<String> container,
                          List<Test_2<List<String>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<String> initialize(int size){
            String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), size);
            container.clear();
            container.addAll(Arrays.asList(arrayes));
            return container;
        }

        // Convenience method:
        public static void run(List<String> list,
                               List<Test_2<List<String>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }
    static class ConTester extends Tester<NowyCon> {
        public ConTester(NowyCon container,
                          List<Test_2<NowyCon>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected NowyCon initialize(int size){
            container.clear();
            String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), size);
            for (int i = 0; i < size;i++) {
                container.add(arrayes[i]);
            }
            return container;
        }

        // Convenience method:
        public static void run(NowyCon list,
                               List<Test_2<NowyCon>> tests) {
            new ConTester(list, tests).timedTest();
        }
    }
    public static void main(String[] args) {
        Tester.defaultParams= TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if(args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 12;

        ConTester.run(new NowyCon(), conTests);
        ListTester.run(new ArrayList<String>(), tests);
    }
}
//przy dobrze zoptymalizowanym kontenerze NowyCon opartym na tablicy jest tak samo wydajny jak ArrayList