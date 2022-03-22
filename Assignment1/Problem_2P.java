import java.util.Scanner;

public class Problem_2P {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String studentID = scanner.next();
        String record = scanner.next();

        if (!studentID.matches("[1-9]\\d{7}") || !record.matches("[YN]{7}")) {
            System.out.println("Not valid");
        }
        else {
            int takenCounter = (int)record.chars().filter(ch -> ch == 'Y').count();
            boolean notConsecutive = record.contains("NNN");

            if (notConsecutive || takenCounter <= 3)
                System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
            else
                System.out.println("Good, keep it up!");
        }
    }
}
