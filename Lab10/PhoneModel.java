import java.util.Scanner;

public enum PhoneModel {
    IPHONE(9999),
    HUAWEI(8888),
    PIXEL(6666),
    SAMSUNG(9399),
    LG(5588);

    int price;

    PhoneModel(int price) {
        this.price = price;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your budget: ");

        int budget = scanner.nextInt();
        for (PhoneModel model : PhoneModel.values()) {
            if (budget < model.price)
                continue;
            System.out.printf("%-10s price: %d\n", model.name(), model.price);
        }
    }
}
