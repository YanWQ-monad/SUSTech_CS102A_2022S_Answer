import java.util.Arrays;
import java.util.Scanner;

public class Problem_3 {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] s = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++)
            s[i] = scanner.nextInt();
        for (int i = 0; i < n; i++)
            d[i] = scanner.nextInt();

        Arrays.sort(s);
        Arrays.sort(d);

        int ans = 0;
        for (int i = 0; i < n; i++)
            ans = Math.max(ans, Math.abs(s[i] - d[i]));
        System.out.println(ans);
    }
}
