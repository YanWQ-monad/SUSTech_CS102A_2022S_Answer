import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        // 检查长度是不是 31
        if (line.length() != 4 * 7 + 3) {
            System.out.println("Not valid");
            return;
        }

        // 检查 7 15 23 是不是空格
        if (line.charAt(7) != ' ' || line.charAt(15) != ' ' || line.charAt(23) != ' ') {
            System.out.println("Not valid");
            return;
        }

        // 检查「每周」的位置是不是 Y 或 N
        boolean isInputInvalid = false;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 7; j++) {
                char ch = line.charAt(i * 8 + j);
                if (ch != 'Y' && ch != 'N') {
                    isInputInvalid = true;
                    break;
                }
            }

        if (isInputInvalid) {
            System.out.println("Not valid");
            return;
        }

        int violationCount = 0;
        for (int i = 0; i < 4; i++) {
            int takenCounter = 0;
            int lastTaken = 0;
            boolean notConsecutive = false;

            for (int j = 0; j < 7; j++) {
                char ch = line.charAt(i * 8 + j);
                if (ch == 'Y') {
                    lastTaken = j;
                    takenCounter++;
                }

                if (j - lastTaken >= 3)
                    notConsecutive = true;
            }

            if (takenCounter <= 3 || notConsecutive)
                violationCount++;
        }

        int score = 100;
        for (int i = 0; i < violationCount; i++)
            score -= 10 + i * 5;

        System.out.println(score);
    }
}
