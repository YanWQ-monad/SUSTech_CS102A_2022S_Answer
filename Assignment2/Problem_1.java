import java.util.Scanner;

public class Problem_1 {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextDouble();

        double l = scanner.nextDouble();
        double r = scanner.nextDouble();

        double ans = 0;
        for (int i = 1; i <= n; i++)
            ans += (1.0 / i) * a[i - 1] * (Math.pow(r, i) - Math.pow(l, i));
        System.out.println(ans);
    }
}
