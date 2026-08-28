import java.util.*;
import myutils.*;
/*
Exercise 30: (3) Compare the performance of Collections.sort( ) between an
ArrayList and a LinkedList.
*/
public class Zad17_30 {
    static List<Test_2<List<Integer>>> tests =
            new ArrayList<Test_2<List<Integer>>>();

    static {
        tests.add(new Test_2<List<Integer>>("sort") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    Collections.shuffle(list);
                    Collections.sort(list);
                }
                return loops * listSize;
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
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list,
                               List<Test_2<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        Tester.fieldWidth = 12;
        Tester.defaultParams = TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Zad17_30.ListTester.run(new ArrayList<Integer>(), tests);
        Zad17_30.ListTester.run(new LinkedList<Integer>(), tests);
    }
}
