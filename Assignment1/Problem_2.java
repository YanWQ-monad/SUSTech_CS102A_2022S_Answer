import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studentID = scanner.nextInt();
        String record = scanner.next();

        if (studentID < 10000000 || record.length() != 7) {
            System.out.println("Not valid");
            return;
        }

        boolean isInvalid = false;
        for (int i = 0; i < 7; i++)
            if (record.charAt(i) != 'Y' && record.charAt(i) != 'N') {
                isInvalid = true;
                break;
            }

        if (isInvalid) {
            System.out.println("Not valid");
            return;
        }

        int takenCounter = 0;
        int lastTaken = 0;
        boolean notConsecutive = false;
        for (int i = 0; i < record.length(); i++) {
            if (record.charAt(i) == 'Y') {
                lastTaken = i;
                takenCounter++;
            }

            if (i - lastTaken >= 3)
                notConsecutive = true;
        }

        if (notConsecutive || takenCounter <= 3)
            System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
        else
            System.out.println("Good, keep it up!");
    }
}
