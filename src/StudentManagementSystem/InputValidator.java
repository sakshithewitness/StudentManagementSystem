package StudentManagementSystem;

public class InputValidator {

    public static boolean isValidStudentId(String studentId) {
        return studentId != null &&
                !studentId.trim().isEmpty() &&
                studentId.matches("^[a-zA-Z0-9]+$") &&
                studentId.length() >= 3 &&
                studentId.length() <= 10;
    }
    public static boolean isValidName(String name) {
        return name != null &&
                !name.trim().isEmpty() &&
                name.matches("^[a-zA-Z\\s]+$") &&
                name.length() >= 2 && name.length() <= 50;
    }
    public static boolean isValidAge(int age) {
        return age >= 16 && age <= 80;
    }
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null &&
                phoneNumber.matches("^\\d{10}$");
    }
}
