import java.util.*;
import myutils.*;
/*
Exercise 33: (5) Create a FastTraversalLinkedList that internally uses a LinkedList
for rapid insertions and removals, and an ArrayList for rapid traversals and get( )
operations. Test it by modifying ListPerformance.java.
*/
class FastTraversalLinkedList<E> extends AbstractList<E> {
    //LinkedList służy jako główna struktura do szybkiego add(), remove()
    //arrayList jest cache do get() i przechodzenia po liście
    private LinkedList<E> kk = new LinkedList<E>();
    private ArrayList<E> kz = new ArrayList<E>();
    private boolean changed = true;
    public void synchro() {
        if(changed) {
            kz = new ArrayList<E>(kk);
            changed = false;
        }
    }
    @Override
    public E set(int index, E element) {
        E old = kk.set(index, element);
        changed = true;
        return old;
    }
    @Override
    public boolean add(E e) {
        kk.add(e);
        changed = true;
        return true;
    }
    @Override
    public void add(int index, E e) {
        kk.add(index, e);
        changed = true;
    }
    @Override
    public E get(int index) {
        synchro();
        return kz.get(index);
    }

    @Override
    public int size() {
        return kk.size();
    }
    @Override
    public E remove(int index) {
        E old = kk.remove(index);
        changed = true;
        return old;
    }
    @Override
    public void clear() {
        kk.clear();
        kz.clear();
        changed = false;
    }
    //wymagane, aby test "iteradd" działał po iteratorze LinkedList inaczej będzie przechodzić po indeksach,
    //w czym LinkedList nie jest dobra(jest powolna)
    @Override
    public ListIterator<E> listIterator(int index) {
        final ListIterator<E> it = kk.listIterator(index);
        return new ListIterator<E>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public E next() {
                return it.next();
            }

            @Override
            public boolean hasPrevious() {
                return it.hasPrevious();
            }

            @Override
            public E previous() {
                return it.previous();
            }

            @Override
            public int nextIndex() {
                return it.nextIndex();
            }

            @Override
            public int previousIndex() {
                return it.previousIndex();
            }

            @Override
            public void remove() {
                it.remove();
                changed = true;
            }

            @Override
            public void set(E e) {
                it.set(e);
                changed = true;
            }

            @Override
            public void add(E e) {
                it.add(e);
                changed = true;
            }
        };
    }
    //używa iterator() z ArrayList
    @Override
    public Iterator<E> iterator() {
        synchro();
        return kz.iterator();
    }
}
public class Zad17_33 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test_2<List<Integer>>> tests =
            new ArrayList<Test_2<List<Integer>>>();
    static List<Test_2<LinkedList<Integer>>> qTests =
            new ArrayList<Test_2<LinkedList<Integer>>>();
    static {
        tests.add(new Test_2<List<Integer>>("add") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(j);
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
        tests.add(new Test_2<List<Integer>>("set") { int test(List<Integer> list, TestParam tp) {
            int loops = tp.loops * reps;
            int listSize = list.size();
            for(int i = 0; i < loops; i++)
                list.set(rand.nextInt(listSize), 47);
            return loops;
        }
        });
        tests.add(new Test_2<List<Integer>>("iteradd") {
            int test(List<Integer> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> it = list.listIterator(half);
                for(int i = 0; i < LOOPS; i++)
                    it.add(47);
                return LOOPS;
            }
        });
        tests.add(new Test_2<List<Integer>>("insert") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                for(int i = 0; i < loops; i++)
                    list.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });
        tests.add(new Test_2<List<Integer>>("remove") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while(list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });
        // Tests for queue behavior:
        qTests.add(new Test_2<LinkedList<Integer>>("addFirst") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });
        qTests.add(new Test_2<LinkedList<Integer>>("addLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            } });
        qTests.add(
                new Test_2<LinkedList<Integer>>("rmFirst") {
                    int test(LinkedList<Integer> list, TestParam tp) {
                        int loops = tp.loops;
                        int size = tp.size;
                        for(int i = 0; i < loops; i++) {
                            list.clear();
                            list.addAll(new CountingIntegerList(size));
                            while(list.size() > 0)
                                list.removeFirst();
                        }
                        return loops * size;
                    }
                });
        qTests.add(new Test_2<LinkedList<Integer>>("rmLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while(list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }
    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container,
                          List<Test_2<List<Integer>>> tests) {
            super(container, tests);
        }
        // Fill to the appropriate size before each test:
        @Override protected List<Integer> initialize(int size){
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
        if(args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<Integer>> arrayTest =
                new Tester<List<Integer>>(null, tests.subList(1, 3)){
                    // This will be called before each test. It
                    // produces a non-resizeable array-backed list:
                    @Override protected
                    List<Integer> initialize(int size) {
                        Integer[] ia = Generated.array(Integer.class,
                                new CountingGenerator.Integer(), size);
                        return Arrays.asList(ia);
                    }
                };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams= TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if(args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);
        //nowa lista
        ListTester.run(new FastTraversalLinkedList<Integer>(), tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<Integer>> qTest =
                new Tester<LinkedList<Integer>>(
                        new LinkedList<Integer>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}
//FastTraversalLinkedList łączy LinkedList i ArrayList:
//LinkedList jest główną strukturą dla add/remove,
//a ArrayList służy jako cache dla szybkiego get()
//listIterator() opiera się na LinkedList, żeby iteradd nie musiał pracować przez indeksy.
//set() nadal jest wolniejsze bo zmienia dane w LinkedList.
