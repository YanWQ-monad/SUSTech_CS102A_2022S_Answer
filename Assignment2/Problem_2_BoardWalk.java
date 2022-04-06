import java.util.Scanner;

public class Problem_2_BoardWalk {
    public static int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();

        while (t --> 0) {
            boolean[][] map = new boolean[8][8];

            for (int i = 0; i < 8; i++) {
                String coordinates = scanner.next();
                int x = coordinates.charAt(0) - 'a';
                int y = coordinates.charAt(1) - '1';
                map[x][y] = true;
            }

            boolean conflict = false;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (!map[x][y])
                        continue;

                    // check 8 directions
                    for (int k = 0; k < 8; k++) {
                        int nowx = x + DX[k], nowy = y + DY[k];

                        while (nowx >= 0 && nowx < 8 && nowy >= 0 && nowy < 8) {
                            if (map[nowx][nowy])
                                conflict = true;

                            nowx += DX[k];
                            nowy += DY[k];
                        }
                    }
                }
            }

            System.out.println(conflict ? "No" : "Yes");
        }
    }
}
