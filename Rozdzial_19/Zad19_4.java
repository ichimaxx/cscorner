import myutils.Enums;
/*
Exercise 4: (1) Repeat the above exercise for Meal2.java
*/

public enum Zad19_4 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    TEA(Food.Tea.class);
    private Food[] values;
    private Zad19_4(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public interface Food {
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
    public Food randomSelection() {
        return Enums.random(values);
    }
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            for(Zad19_4 meal : Zad19_4.values()) {
                Food food = meal.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}

/*
Zadanie różni się od Zad19_3 stylem budowy kodu.
W Zad19_3 Food i Course były osobnymi typami, a tutaj interfejs Food oraz
enumy Appetizer, MainCourse itd. są zagnieżdżone wewnątrz jednego enuma Zad19_4.
*/