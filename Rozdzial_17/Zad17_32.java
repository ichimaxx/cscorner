import java.util.*;
import myutils.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/*
Exercise 32: (2) Repeat the previous exercise for a container of int, and compare the
performance to an ArrayList<Integer>. In your performance comparison, include the
process of incrementing each object in the container.
*/
class NowyConInt {
    private int index = 0;
    private int[] ara = new int[1];
    public int size() {
        return index;
    }
    public String toString() {
        return Arrays.toString(Arrays.copyOf(ara, index));
    }
    public boolean add(int o) {
        int indeka = 0;
        if (index == ara.length) {
            //tablica jest powiększana kilka razy, aby nie kopiować jej przy każdym add()
            int[] aras = new int[ara.length * 5];
            for (int ok : ara) {
                aras[indeka] = ok;
                indeka++;
            }
            ara = aras;
        }
        ara[index] = o;
        index++;
        return true;
    }
    public int get(int indeks) {
        if (indeks >= index || indeks < 0) {
            throw new IndexOutOfBoundsException();
        }
        return ara[indeks];
    }
    public void clear() {
        index = 0;
    }
    public int incr(int k) {
        if(k >= index || k < 0) {
            throw new IndexOutOfBoundsException();
        }
        ara[k] = ara[k] + 1;
        return ara[k];

    }
}
public class Zad17_32 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test_2<List<Integer>>> tests =
            new ArrayList<Test_2<List<Integer>>>();
    // osobny test dla nowo stworzonego kontenera NowyCon
    static List<Test_2<NowyConInt>> conTests =
            new ArrayList<Test_2<NowyConInt>>();

    static {
        tests.add(new Test_2<List<Integer>>("add") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                Integer[] arrayes = Generated.array(Integer.class, new CountingGenerator.Integer(), listSize);
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(arrayes[j]);
                }
                return loops * listSize;
            }
        });
        tests.add(new Test_2<List<Integer>>("incr") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for(int i = 0; i < loops; i++) {
                    for(int j = 0; j < listSize; j++)
                        list.set(j, list.get(j) + 1);
                }
                return loops * listSize;
            }
        });
        conTests.add(new Test_2<NowyConInt>("add") {
            int test(NowyConInt list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                Integer[] arrayes = Generated.array(Integer.class, new CountingGenerator.Integer(), listSize);
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(arrayes[j]);
                }
                return loops * listSize;
            }
        });
        conTests.add(new Test_2<NowyConInt>("incr") {
            int test(NowyConInt list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for(int i = 0; i < loops; i++) {
                    for(int j = 0; j < listSize; j++)
                        list.incr(j);
                }
                return loops * listSize;
            }
        });
        tests.add(new Test_2<List<Integer>>("get") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
        conTests.add(new Test_2<NowyConInt>("get") {
            int test(NowyConInt list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container,
                          List<Test_2<List<Integer>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<Integer> initialize(int size){
            Integer[] arrayes = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
            container.clear();
            container.addAll(Arrays.asList(arrayes));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list,
                               List<Test_2<List<Integer>>> tests) {
            new Zad17_32.ListTester(list, tests).timedTest();
        }
    }
    static class ConTester extends Tester<NowyConInt> {
        public ConTester(NowyConInt container,
                         List<Test_2<NowyConInt>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected NowyConInt initialize(int size){
            container.clear();
            Integer[] arrayes = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
            for (int i = 0; i < size;i++) {
                container.add(arrayes[i]);
            }
            return container;
        }

        // Convenience method:
        public static void run(NowyConInt list,
                               List<Test_2<NowyConInt>> tests) {
            new Zad17_32.ConTester(list, tests).timedTest();
        }
    }
    public static void main(String[] args) {
        Tester.defaultParams= TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if(args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 12;

        Zad17_32.ConTester.run(new NowyConInt(), conTests);
        Zad17_32.ListTester.run(new ArrayList<Integer>(), tests);
    }
}
//incr zrobione jako metoda bezpośrednio z kontenera jest szybsze, ponieważ od razu pracuje na tablicy int[].
//w przypadku ArrayList używane są dodatkowo metody get, aby wziąć wartość i set, aby ustawić nową wartość,
//co dodatkowo wymaga boxing i unboxing wartości