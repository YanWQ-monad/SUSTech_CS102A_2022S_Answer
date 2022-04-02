import java.util.ArrayList;
import java.util.Scanner;

public class SoftOpening {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Food> foodList = generateMenu();
        User user = generateUser(in);
        user.introduce();
        userConsume(foodList,user,in);
        user.introduce();
        in.close();
    }

    public static ArrayList<Food> generateMenu() {
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food(1, "pizza", "Seafood", 11, 12));
        foodList.add(new Food(2, "pizza", "Beef", 9, 10));
        foodList.add(new Food(3, "fired rice", "Seafood", 5, 12));
        foodList.add(new Food(4, "noodles", "Beef", 6, 14));

        return foodList;

    }

    public static void getMenu(ArrayList<Food> foodList) {
        System.out.println("---------- welcome, this is Start of the Menu ------");;

        for (int i = 0; i < foodList.size(); i++)
            foodList.get(i).getMenu();

        System.out.printf("footer");
    }

    public static User generateUser(Scanner in) {
        System.out.print("Generate a user, please input name: ");;
        String account = in.next();
        System.out.println("balance($): ");
        double money = in.nextDouble();

        return new User(account, "123456", money);
    }

    public static void userConsume(ArrayList<Food> foodList, User user, Scanner in) {
        getMenu(foodList);
        System.out.print("please input .....");

        double allCost = 0;

        while (true) {
            System.out.print("food id: ");
            int id = in.nextInt();
            if (id == 0)
                break;

            System.out.print("number: ");
            int number = in.nextInt();

            Food food = null;
            for (int i = 0; i < foodList.size(); i++)
                if (foodList.get(i).getId() == id)
                    food = foodList.get(i);

            double cost = food.getPrice() * number;
//            System.out.println(cost);
            allCost += cost;
        }

        user.expense(allCost, in);
    }
}
