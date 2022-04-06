import java.util.Scanner;

public class Problem_4 {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        System.out.println(3 * n + 3);
        System.out.println("-1 -1");
        System.out.println("-1 0");
        System.out.println("0 -1");

        for (int i = 0; i < n; i++) {
            System.out.printf("%d %d\n", i, i + 1);
            System.out.printf("%d %d\n", i + 1, i + 1);
            System.out.printf("%d %d\n", i + 1, i);
        }
    }
}
