package Rozdzial_15;
/*
Exercise 31: (1) Remove all the generics from MultipleInterfaceVariants.java and
modify the code so that the example compiles.
 */
interface Payable {}
class Employee implements Payable {}
public class Zad15_31 extends Employee implements Payable {}



/*
Jeśli interfejs jest dodany do różnych klas z różnymi określonymi typami generycznymi, przez erasure ten interfejs będzie traktowany jako ten sam. Ergo, program się nie skompiluje.
PS C:\Users\ichim\Desktop\cscorner\Rozdzial_15> javac Zad15_31.java
Zad15_31.java:5: error: Payable cannot be inherited with different arguments: <Rozdzial_15.Zad15_31> and <Rozdzial_15.Employee>
public class Zad15_31 extends Employee implements Payable<Zad15_31> {}
       ^
1 error

zadanie pokazuje, że po usunięciu typów generycznych z implementowanego interfejsu, program kompiluje się.
*/