import java.util.Scanner;

public class Problem_5_Factorize {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        int r = scanner.nextInt();

        if (r == 0) {
            System.out.println("1");
            return;
        }

        int ans = 1;

        for (int i = 2; i * i <= r; i++) {
            int cnt = 0;
            while (r % i == 0) {
                r /= i;
                cnt++;
            }
            if (i % 4 == 1 && cnt > 0) {
                ans *= 2 * cnt + 1;
            }
        }

        if (r != 1 && r % 4 == 1)
            ans *= 3;

        System.out.println(ans * 4);
    }
}
