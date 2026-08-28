import java.util.*;

public class Tester<C> {
    public static int fieldWidth = 8;
    public static TestParam[] defaultParams= TestParam.array(
            10, 5000, 100, 5000, 1000, 5000, 10000, 500);
    // Override this to modify pre-test initialization:
    protected C initialize(int size) { return container; }
    protected C container;
    private String headline = "";
    private List<Test_2<C>> tests;
    private static String stringField() {
        return "%" + fieldWidth + "s";
    }
    private static String numberField() {
        return "%" + fieldWidth + "d";
    }
    private static int sizeWidth = 5;
    private static String sizeField = "%" + sizeWidth + "s";
    private TestParam[] paramList = defaultParams;
    public Tester(C container, List<Test_2<C>> tests) {
        this.container = container;
        this.tests = tests;
        if(container != null)
            headline = container.getClass().getSimpleName();
    }
    public Tester(C container, List<Test_2<C>> tests,
                  TestParam[] paramList) {
        this(container, tests);
        this.paramList = paramList;
    }
    public void setHeadline(String newHeadline) {
        headline = newHeadline;
    }
    // Generic methods for convenience :
    public static <C> void run(C cntnr, List<Test_2<C>> tests){
        new Tester<C>(cntnr, tests).timedTest();
    }
    public static <C> void run(C cntnr,
                               List<Test_2<C>> tests, TestParam[] paramList) {
        new Tester<C>(cntnr, tests, paramList).timedTest();
    }
    private void displayHeader() {
        // Calculate width and pad with ‘-’:
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);
        for(int i = 0; i < dashLength/2; i++)
            head.append('-');
        head.append(' ');
        head.append(headline);
        head.append(' ');
        for(int i = 0; i < dashLength/2; i++)
            head.append('-');
        System.out.println(head);
        // Print column headers:
        System.out.format(sizeField, "size");
        for(Test_2 test : tests)
            System.out.format(stringField(), test.name);
        System.out.println();
    }
    // Run the tests for this container:
    public void timedTest() {
        displayHeader();
        for(TestParam param : paramList) {
            System.out.format(sizeField, param.size);
            for(Test_2<C> test : tests) {
                C kontainer = initialize(param.size);
                long start = System.nanoTime();
                // Call the overriden method:
                int reps = test.test(kontainer, param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps; // Nanoseconds
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }
}