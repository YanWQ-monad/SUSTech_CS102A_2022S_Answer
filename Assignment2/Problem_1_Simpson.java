import java.util.Scanner;

public class Problem_1_Simpson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextDouble();

        double l = scanner.nextDouble();
        double r = scanner.nextDouble();

        final int N = 2000;
        double ans1 = 0, ans2 = 0;
        for (int i = 1; i <= N - 1; i += 2)
            ans1 += valueAt((r - l) * i / N + l, a);
        for (int i = 2; i <= N - 2; i += 2)
            ans2 += valueAt((r - l) * i / N + l, a);
        double ans = ans1 * 4 + ans2 * 2 + valueAt(l, a) + valueAt(r, a);
        System.out.println((r - l) * ans / (3 * N));
    }

    public static double valueAt(double x, double[] c) {
        double val = 0, xi = 1;
        for (double v : c) {
            val += xi * v;
            xi *= x;
        }
        return val;
    }
}
