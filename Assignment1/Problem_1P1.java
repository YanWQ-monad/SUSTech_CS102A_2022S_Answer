import java.util.Scanner;

public class Problem_1P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String studentID = scanner.next();
        double temperature = scanner.nextDouble();
        String record = scanner.next();

        boolean isStudentIDInvalid = false;
        boolean isTemperatureInvalid = false;
        boolean isRecordInvalid = false;

        if (studentID.length() != 8 || studentID.startsWith("0"))
            isStudentIDInvalid = true;
        if (temperature <= 35.0 || 45.0 <= temperature)
            isTemperatureInvalid = true;
        if (!record.equals("N") && !record.equals("Y"))
            isRecordInvalid = true;

        if (isStudentIDInvalid || isTemperatureInvalid || isRecordInvalid) {
            String errorMessage = "";
            if (isStudentIDInvalid)
                errorMessage += " and Student ID";
            if (isTemperatureInvalid)
                errorMessage += " and Temperature";
            if (isRecordInvalid)
                errorMessage += " and Nucleic Acid PCR test";
            errorMessage = "Error in " + errorMessage.substring(5);
            System.out.println(errorMessage);
        }
        else {
            System.out.println("Submit successfully");
        }
    }
}
