import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Problem_3P2 {
    static int nextTimestamp(Scanner scanner) throws Exception {
        SimpleDateFormat parser = new SimpleDateFormat("HH mm ss");
        parser.setLenient(false);
        return (int)parser.parse(scanner.nextLine()).getTime() / 1000;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int t = -(nextTimestamp(scanner) - nextTimestamp(scanner));

            if (t <= 0)
                System.out.println(t == 0 ? "0s" : "Not valid");
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
