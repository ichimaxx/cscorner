import java.util.*;

/*
Exercise 36: (2) Add a second parameterized exception to the Processor class and
demonstrate that the exceptions can vary independently.
*/

interface Processoro<T,E extends Exception, F extends Exception> {
    void process(List<T> resultCollector) throws E, F;
}
class ProcessRunner<T,E extends Exception,F extends Exception>
        extends ArrayList<Processoro<T,E,F>> {
    List<T> processAll() throws E,F {
        List<T> resultCollector = new ArrayList<T>();
        for(Processoro<T,E,F> processor : this) {
            processor.process(resultCollector);
        }
        return resultCollector;
    }
}
class Failure1 extends Exception {}
class Processor1 implements Processoro<String,Failure1,Failure2> {
    static int count = 3;
    public void
    process(List<String> resultCollector) throws Failure1,Failure2 {
        if (count-- > 1)
            resultCollector.add("Hep!");
        else
            resultCollector.add("Ho!");
        if (count < 0)
            throw new Failure1();
        else if(count < 1)
            throw new Failure2();
    }
}
class Failure2 extends Exception {}
class Processor2 implements Processoro<Integer,Failure1,Failure2> {
    static int count = 2;
    public void
    process(List<Integer> resultCollector) throws Failure1,Failure2 {
        if(count-- == 0)
            resultCollector.add(47);
        else {
            resultCollector.add(11);
        }
        if(count < 0)
            throw new Failure1();
        else if(count < 1)
            throw new Failure2();
    }
}
public class Zad15_36 {
    public static void main(String[] args) {
        ProcessRunner<String,Failure1,Failure2> runner =
                new ProcessRunner<String,Failure1,Failure2>();
        for(int i = 0; i < 3; i++)
            runner.add(new Processor1());
        try {
            System.out.println(runner.processAll());
        } catch(Failure1 e) {
            System.out.println("Processor1 -> Failure1");
        } catch(Failure2 e) {
            System.out.println("Processor1 -> Failure2");
        }
        ProcessRunner<String,Failure1,Failure2> runner1_1 =
                new ProcessRunner<String,Failure1,Failure2>();
        Processor1.count = 0; // wymuszenie Failure1 z pomoca wybrania count manualnie
            runner1_1.add(new Processor1());
        try {
            System.out.println(runner1_1.processAll());
        } catch(Failure1 e) {
            System.out.println("Processor1 -> Failure1");
        } catch(Failure2 e) {
            System.out.println("Processor1 -> Failure2");
        }
        ProcessRunner<Integer,Failure1,Failure2> runner2 =
                new ProcessRunner<Integer,Failure1,Failure2>();
        for(int i = 0; i < 3; i++)
            runner2.add(new Processor2());
        try {
            System.out.println(runner2.processAll());
        } catch(Failure2 e) {
            System.out.println("Processor2 -> Failure2");
        } catch(Failure1 e) {
            System.out.println("Processor2 -> Failure1");
        }
        Processor2.count = 0; // wymuszenie Failure1 z pomoca wybrania count manualnie
        ProcessRunner<Integer,Failure1,Failure2> runner2_2 =
                new ProcessRunner<Integer,Failure1,Failure2>();
            runner2_2.add(new Processor2());
        try {
            System.out.println(runner2_2.processAll());
        } catch(Failure2 e) {
            System.out.println("Processor2 -> Failure2");
        } catch(Failure1 e) {
            System.out.println("Processor2 -> Failure1");
        }
    }
}
/*
Dodano drugi parametryzowany wyjątek F do Processor i ProcessRunner. Dzieki temu metoda process() może deklarować dwa różne typy wyjątków.
Pokazano, że zarówno Processor1 i Processor2 mogą zakończyć się Failure1 albo Failure2, zależnie od wartości count.
Udowadnia to, że wyjątki moga zmieniać się niezależnie od siebie nawzajem(exceptions can vary independently).
 */