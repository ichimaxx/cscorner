/*
Exercise 6: (3) Is there any special benefit in nesting Appetizer, MainCourse,
Dessert, and Coffee inside Food(Zad19_6) rather than making them standalone enums that just
happen to implement Food(Zad19_6)?
*/
public interface Zad19_6 {
    enum Appetizer implements Zad19_6 {
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Zad19_6 {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Zad19_6 {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Zad19_6 {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
}

/*
Nie ma specjalnej korzyści typowej z tego, że Appetizer, MainCourse, Dessert i Coffee są zagnieżdżone
wewnątrz interfejsu Food/Zad19_6.
Wspólny typ Food wynika z tego, że każdy enum implementuje interfejs Food, a nie z samego zagnieżdżenia.

Korzyść z zagnieżdżenia jest głównie organizacyjna, enumy są pogrupowane pod jednym Food.
Gdyby były samodzielnymi enumami implementującymi Food, działałyby podobnie.
*/