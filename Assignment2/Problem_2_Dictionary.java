import java.util.Arrays;
import java.util.Scanner;

public class Problem_2_Dictionary {
    static final String[] DICTIONARY = {
        "a1 b5 c8 d6 e3 f7 g2 h4",
        "a1 b6 c8 d3 e7 f4 g2 h5",
        "a1 b7 c4 d6 e8 f2 g5 h3",
        "a1 b7 c5 d8 e2 f4 g6 h3",
        "a2 b4 c6 d8 e3 f1 g7 h5",
        "a2 b5 c7 d1 e3 f8 g6 h4",
        "a2 b5 c7 d4 e1 f8 g6 h3",
        "a2 b6 c1 d7 e4 f8 g3 h5",
        "a2 b6 c8 d3 e1 f4 g7 h5",
        "a2 b7 c3 d6 e8 f5 g1 h4",
        "a2 b7 c5 d8 e1 f4 g6 h3",
        "a2 b8 c6 d1 e3 f5 g7 h4",
        "a3 b1 c7 d5 e8 f2 g4 h6",
        "a3 b5 c2 d8 e1 f7 g4 h6",
        "a3 b5 c2 d8 e6 f4 g7 h1",
        "a3 b5 c7 d1 e4 f2 g8 h6",
        "a3 b5 c8 d4 e1 f7 g2 h6",
        "a3 b6 c2 d5 e8 f1 g7 h4",
        "a3 b6 c2 d7 e1 f4 g8 h5",
        "a3 b6 c2 d7 e5 f1 g8 h4",
        "a3 b6 c4 d1 e8 f5 g7 h2",
        "a3 b6 c4 d2 e8 f5 g7 h1",
        "a3 b6 c8 d1 e4 f7 g5 h2",
        "a3 b6 c8 d1 e5 f7 g2 h4",
        "a3 b6 c8 d2 e4 f1 g7 h5",
        "a3 b7 c2 d8 e5 f1 g4 h6",
        "a3 b7 c2 d8 e6 f4 g1 h5",
        "a3 b8 c4 d7 e1 f6 g2 h5",
        "a4 b1 c5 d8 e2 f7 g3 h6",
        "a4 b1 c5 d8 e6 f3 g7 h2",
        "a4 b2 c5 d8 e6 f1 g3 h7",
        "a4 b2 c7 d3 e6 f8 g1 h5",
        "a4 b2 c7 d3 e6 f8 g5 h1",
        "a4 b2 c7 d5 e1 f8 g6 h3",
        "a4 b2 c8 d5 e7 f1 g3 h6",
        "a4 b2 c8 d6 e1 f3 g5 h7",
        "a4 b6 c1 d5 e2 f8 g3 h7",
        "a4 b6 c8 d2 e7 f1 g3 h5",
        "a4 b6 c8 d3 e1 f7 g5 h2",
        "a4 b7 c1 d8 e5 f2 g6 h3",
        "a4 b7 c3 d8 e2 f5 g1 h6",
        "a4 b7 c5 d2 e6 f1 g3 h8",
        "a4 b7 c5 d3 e1 f6 g8 h2",
        "a4 b8 c1 d3 e6 f2 g7 h5",
        "a4 b8 c1 d5 e7 f2 g6 h3",
        "a4 b8 c5 d3 e1 f7 g2 h6",
        "a5 b1 c4 d6 e8 f2 g7 h3",
        "a5 b1 c8 d4 e2 f7 g3 h6",
        "a5 b1 c8 d6 e3 f7 g2 h4",
        "a5 b2 c4 d6 e8 f3 g1 h7",
        "a5 b2 c4 d7 e3 f8 g6 h1",
        "a5 b2 c6 d1 e7 f4 g8 h3",
        "a5 b2 c8 d1 e4 f7 g3 h6",
        "a5 b3 c1 d6 e8 f2 g4 h7",
        "a5 b3 c1 d7 e2 f8 g6 h4",
        "a5 b3 c8 d4 e7 f1 g6 h2",
        "a5 b7 c1 d3 e8 f6 g4 h2",
        "a5 b7 c1 d4 e2 f8 g6 h3",
        "a5 b7 c2 d4 e8 f1 g3 h6",
        "a5 b7 c2 d6 e3 f1 g4 h8",
        "a5 b7 c2 d6 e3 f1 g8 h4",
        "a5 b7 c4 d1 e3 f8 g6 h2",
        "a5 b8 c4 d1 e3 f6 g2 h7",
        "a5 b8 c4 d1 e7 f2 g6 h3",
        "a6 b1 c5 d2 e8 f3 g7 h4",
        "a6 b2 c7 d1 e3 f5 g8 h4",
        "a6 b2 c7 d1 e4 f8 g5 h3",
        "a6 b3 c1 d7 e5 f8 g2 h4",
        "a6 b3 c1 d8 e4 f2 g7 h5",
        "a6 b3 c1 d8 e5 f2 g4 h7",
        "a6 b3 c5 d7 e1 f4 g2 h8",
        "a6 b3 c5 d8 e1 f4 g2 h7",
        "a6 b3 c7 d2 e4 f8 g1 h5",
        "a6 b3 c7 d2 e8 f5 g1 h4",
        "a6 b3 c7 d4 e1 f8 g2 h5",
        "a6 b4 c1 d5 e8 f2 g7 h3",
        "a6 b4 c2 d8 e5 f7 g1 h3",
        "a6 b4 c7 d1 e3 f5 g2 h8",
        "a6 b4 c7 d1 e8 f2 g5 h3",
        "a6 b8 c2 d4 e1 f7 g5 h3",
        "a7 b1 c3 d8 e6 f4 g2 h5",
        "a7 b2 c4 d1 e8 f5 g3 h6",
        "a7 b2 c6 d3 e1 f4 g8 h5",
        "a7 b3 c1 d6 e8 f5 g2 h4",
        "a7 b3 c8 d2 e5 f1 g6 h4",
        "a7 b4 c2 d5 e8 f1 g3 h6",
        "a7 b4 c2 d8 e6 f1 g3 h5",
        "a7 b5 c3 d1 e6 f8 g2 h4",
        "a8 b2 c4 d1 e7 f5 g3 h6",
        "a8 b2 c5 d3 e1 f7 g4 h6",
        "a8 b3 c1 d6 e2 f5 g7 h4",
        "a8 b4 c1 d3 e6 f2 g7 h5",
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        scanner.nextLine();
        while (t --> 0) {
            String line = scanner.nextLine();
            String[] coordinates = line.split(" ");
            Arrays.sort(coordinates);
            String given = String.join(" ", coordinates);

            boolean ok = false;
            for (String sol : DICTIONARY)
                if (sol.equals(given)) {
                    ok = true;
                    break;
                }

            System.out.println(ok ? "Yes" : "No");
        }
    }
}
