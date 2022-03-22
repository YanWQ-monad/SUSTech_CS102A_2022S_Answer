import java.util.Arrays;
import java.util.Scanner;

public class Problem_4P {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        if (!line.matches("^([YN]{7} ){3}[YN]{7}$")) {
            System.out.println("Not valid");
            return;
        }

        int count = (int)Arrays.stream(line.split(" ")).filter((item) -> {
            int yesCount = (int)item.chars().filter(ch -> ch == 'Y').count();
            boolean notConsecutive = item.contains("NNN");
            return yesCount <= 3 || notConsecutive;
        }).count();

        int score = 100 - (5 * count + 15) * count / 2;
        System.out.println(score);
    }
}
