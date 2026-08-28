public abstract class Test_2<C> {
    String name;
    public Test_2(String name) { this.name = name; }
    // Override this method for different tests.
    // Returns actual number of repetitions of test.
    abstract int test(C container, TestParam tp);
}