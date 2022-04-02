import java.util.ArrayList;

public class FoodTest {
    public static void main(String[] args) {
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food(1, "pizza", "Seafood", 11, 12));
        foodList.add(new Food(2, "pizza", "Beef", 9, 10));
        foodList.add(new Food(3, "fired rice", "Seafood", 5, 12));
        foodList.add(new Food(4, "noodles", "Beef", 6, 14));

        System.out.println("---------- welcome, this is Start of the Menu ------");;

        for (int i = 0; i < foodList.size(); i++)
            foodList.get(i).getMenu();

        System.out.printf("footer");
    }
}
