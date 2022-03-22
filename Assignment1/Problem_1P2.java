import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem_1P2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String studentID = scanner.next();
        double temperature = scanner.nextDouble();
        String record = scanner.next();

        List<String> errors = new ArrayList<>();
        if (!studentID.matches("[1-9]\\d{7}"))
            errors.add("Student ID");
        if (temperature <= 35.0 || 45.0 <= temperature)
            errors.add("Temperature");
        if (!record.equals("N") && !record.equals("Y"))
            errors.add("Nucleic Acid PCR test");

        if (errors.size() > 0) {
            String message = "Error in " + String.join(" and ", errors);
            System.out.println(message);
        }
        else {
            System.out.println("Submit successfully");
        }
    }
}
