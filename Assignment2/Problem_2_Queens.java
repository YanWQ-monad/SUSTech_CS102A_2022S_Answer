import java.util.Scanner;

public class Problem_2_Queens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        while (t --> 0) {
            boolean valid = true;
            int[] x = new int[8];
            int[] y = new int[8];

            for (int i = 0; i < 8; i++) {
                String coordinates = scanner.next();
                x[i] = coordinates.charAt(0) - 'a';
                y[i] = coordinates.charAt(1) - '1';
            }

            for (int i = 0; i < 8; i++)
                for (int j = i + 1; j < 8; j++)
                    if (x[i] == x[j] || y[i] == y[j] || Math.abs(x[i] - x[j]) == Math.abs(y[i] - y[j])) {
                        valid = false;
                        break;
                    }

            System.out.println(valid ? "Yes" : "No");
        }
    }
}
