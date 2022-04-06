import java.util.Scanner;

public class Problem_5_Bruteforce {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        long r = scanner.nextInt();

        if (r == 0) {
            System.out.println("1");
            return;
        }

        int ans = 0;
        long y = r - 1, x = 1;
        while (true) {
            long remain = r * r - y * y;
            while (x * x < remain)
                x++;
            if (x >= y)
                break;
            if (x * x == remain)
                ans++;
            y--;
        }

        ans = ans * 8 + 4;
        if (2 * x * x == r * r)
            ans += 4;

        System.out.println(ans);
    }
}
