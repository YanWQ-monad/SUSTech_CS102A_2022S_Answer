import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line1 = scanner.nextLine();
        String[] line1num = line1.split(" ");

        String line2 = scanner.nextLine();
        String[] line2num = line2.split(" ");

        if (line1num.length != 3 || line2num.length != 3) {
            System.out.println("Not valid");
            return;
        }

        int hour1 = Integer.parseInt(line1num[0]);
        int minute1 = Integer.parseInt(line1num[1]);
        int second1 = Integer.parseInt(line1num[2]);

        int hour2 = Integer.parseInt(line2num[0]);
        int minute2 = Integer.parseInt(line2num[1]);
        int second2 = Integer.parseInt(line2num[2]);

        if (hour1 < 0 || hour1 >= 24 || minute1 < 0 || minute1 >= 60 || second1 < 0 || second1 >= 60 ||
            hour2 < 0 || hour2 >= 24 || minute2 < 0 || minute2 >= 60 || second2 < 0 || second2 >= 60)
        {
            System.out.println("Not valid");
            return;
        }

        int timestamp1 = hour1 * 3600 + minute1 * 60 + second1;
        int timestamp2 = hour2 * 3600 + minute2 * 60 + second2;

        int difference = timestamp2 - timestamp1;

        if (difference < 0) {
            System.out.println("Not valid");
        }
        else if (difference == 0) {
            System.out.println("0s");
        }
        else {
            int hours = difference / 3600;
            int minutes = (difference / 60) % 60;
            int seconds = difference % 60;

            if (hours != 0) {
                System.out.printf("%dh", hours);
            }
            if (minutes != 0) {
                System.out.printf("%dm", minutes);
            }
            if (seconds != 0) {
                System.out.printf("%ds", seconds);
            }
            System.out.println();
        }
    }
}
