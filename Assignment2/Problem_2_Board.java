import java.util.Scanner;

public class Problem_2_Board {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        while (t --> 0) {
            boolean[][] board = new boolean[8][8];
            boolean valid = true;

            for (int i = 0; i < 8; i++) {
                String coordinates = scanner.next();
                int x = coordinates.charAt(0) - 'a';
                int y = coordinates.charAt(1) - '1';

                if (board[x][y])
                    valid = false;
                board[x][y] = true;
            }

            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++) {
                    if (!board[x][y])
                        continue;

                    for (int k = 1; k < 8; k++) {
                        if (x + k < 8 && board[x + k][y])
                            valid = false;
                        if (y + k < 8 && board[x][y + k])
                            valid = false;
                        if (x + k < 8 && y + k < 8 && board[x + k][y + k])
                            valid = false;
                        if (x + k < 8 && y - k >= 0 && board[x + k][y - k])
                            valid = false;
                    }
                }

            System.out.println(valid ? "Yes" : "No");
        }
    }
}
