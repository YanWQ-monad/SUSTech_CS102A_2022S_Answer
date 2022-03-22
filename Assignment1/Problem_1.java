import java.util.Scanner;

public class Problem_1 {
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

        if (!isStudentIDInvalid && !isTemperatureInvalid && !isRecordInvalid)
            System.out.println("Submit successfully");
        else if (!isStudentIDInvalid && !isTemperatureInvalid && isRecordInvalid)
            System.out.println("Error in Nucleic Acid PCR test");
        else if (!isStudentIDInvalid && isTemperatureInvalid && !isRecordInvalid)
            System.out.println("Error in Temperature");
        else if (!isStudentIDInvalid && isTemperatureInvalid && isRecordInvalid)
            System.out.println("Error in Temperature and Nucleic Acid PCR test");
        else if (isStudentIDInvalid && !isTemperatureInvalid && !isRecordInvalid)
            System.out.println("Error in Student ID");
        else if (isStudentIDInvalid && !isTemperatureInvalid && isRecordInvalid)
            System.out.println("Error in Student ID and Nucleic Acid PCR test");
        else if (isStudentIDInvalid && isTemperatureInvalid && !isRecordInvalid)
            System.out.println("Error in Student ID and Temperature");
        else if (isStudentIDInvalid && isTemperatureInvalid && isRecordInvalid)
            System.out.println("Error in Student ID and Temperature and Nucleic Acid PCR test");
    }
}
