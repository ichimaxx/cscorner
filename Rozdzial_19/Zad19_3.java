import myutils.Enums;
/*
Exercise 3: (1) Add a new Course to Course.java and demonstrate that it works in
Meal.java.
*/
interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
    enum Tea implements Food {
        EARL_GREY, BLACK_TEA, GREEN_TEA, WHITE_TEA,
        ROOIBOS, JASMINE_TEA;
    }
}
enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    TEA(Food.Tea.class);
    private Food[] values;
    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public Food randomSelection() {
        return Enums.random(values);
    }
}
public class Zad19_3 {
    public static void main(String[] args) {
    for(int i = 0; i < 5; i++) {
        for(Course course : Course.values()) {
            Food food = course.randomSelection();
            System.out.println(food);
        }
        System.out.println("---");
    }
}
}
