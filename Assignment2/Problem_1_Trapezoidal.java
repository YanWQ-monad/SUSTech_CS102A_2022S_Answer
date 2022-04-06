import java.util.Scanner;

public class Problem_1_Trapezoidal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextDouble();

        double l = scanner.nextDouble();
        double r = scanner.nextDouble();

        final int N = 1000000;
        double ans = 0;
        for (int i = 1; i <= N - 1; i ++)
            ans += valueAt((r - l) * i / N + l, a);
        ans = ans * 2 + valueAt(l, a) + valueAt(r, a);
        System.out.println((r - l) * ans / (2 * N));
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
