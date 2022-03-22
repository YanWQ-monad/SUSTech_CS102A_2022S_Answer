import java.util.Scanner;

public class Problem_3P1 {
    static int nextTimestamp(Scanner scanner) {
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        int second = scanner.nextInt();

        if (hour < 0 || hour >= 24 || minute < 0 || minute >= 60 || second < 0 || second >= 60)
            throw new RuntimeException("invalid hour, minute or second");

        return hour * 3600 + minute * 60 + second;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int t = -(nextTimestamp(scanner) - nextTimestamp(scanner));

            if (t < 0)
                System.out.println("Not valid");
            else if (t == 0)
                System.out.println("0s");
            else {
                String time = String.format(" %dh %dm %ds", t / 3600, (t / 60) % 60, t % 60);
                System.out.println(time.replaceAll(" 0.", "").replaceAll(" ", ""));
            }
        }
        catch (Exception e) {
            System.out.println("Not valid");
        }
    }
}
