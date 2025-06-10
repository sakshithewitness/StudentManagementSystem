package StudentManagementSystem;

import java.util.*;

public class StudentManagementUI {

    private Scanner scanner;
    private final StudentService studentService;

    public StudentManagementUI() {
        scanner = new Scanner(System.in);
        studentService = new StudentService();
    }

    public void start() {
        System.out.println("STUDENT MANAGEMENT SYSTEM");

        while (true) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1 -> addStudentProcess();
                case 2 -> viewAllStudentsProcess();
                case 3 -> updateStudentProcess();
                case 4 -> deleteStudentProcess();
                case 5 -> searchStudentProcess();
                case 6 -> searchByCourseProcess();
                case 7 -> displayStatistics();
                case 8 -> {
                    exitApplication();
                    return;
                }
                default -> System.out.println(" Invalid choice! Please select a number between 1-8.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student Information");
        System.out.println("4. Delete Student Record");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Search Students by Course");
        System.out.println("7. View Statistics");
        System.out.println("8. Exit Application");
        System.out.print("Enter your choice (1 to 8): ");
    }

    private int getMenuChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // consume invalid input
            return -1;
        }
    }

    private void addStudentProcess() {
        System.out.println("ADD NEW STUDENT");
        try {
            String studentId;
            do {
                System.out.print("Enter Student ID (3-10 alphanumeric characters): ");
                studentId = scanner.nextLine().trim();
            } while (!InputValidator.isValidStudentId(studentId));

            if (studentService.findStudentById(studentId) != null) {
                System.out.println(" Student ID already exists! Please use a different ID.");
                return;
            }

            String name;
            do {
                System.out.print("Enter Student Name (2-50 characters, letters only): ");
                name = scanner.nextLine().trim();
            } while (!InputValidator.isValidName(name));

            int age;
            do {
                System.out.print("Enter Student Age (16-80): ");
                try {
                    age = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    age = -1;
                }
            } while (!InputValidator.isValidAge(age));

            String course;
            do {
                System.out.print("Enter Course Name: ");
                course = scanner.nextLine().trim();
            } while (course.isEmpty());

            String email;
            do {
                System.out.print("Enter Email Address: ");
                email = scanner.nextLine().trim();
            } while (!InputValidator.isValidEmail(email));

            String phoneNumber;
            do {
                System.out.print("Enter Phone Number (10 digits): ");
                phoneNumber = scanner.nextLine().trim();
            } while (!InputValidator.isValidPhoneNumber(phoneNumber));

            Student student = new Student(studentId, name, age, course, email, phoneNumber);

            if (studentService.addStudent(student)) {
                System.out.println(" Student added successfully!");
                System.out.println("\nStudent Details:");
                student.displayDetailedInfo();
            } else {
                System.out.println("Failed to add student.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred while adding student: " + e.getMessage());
        }
    }

    private void viewAllStudentsProcess() {
        System.out.println("ALL STUDENTS");

        Collection<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("There are no students in the system.");
        } else {
            System.out.printf("%-12s %-20s %-5s %-15s %-25s %-15s%n",
                    "STUDENT ID", "NAME", "AGE", "COURSE", "EMAIL", "PHONE");
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("Total Students: " + students.size());
        }
    }

    private void updateStudentProcess() {
        System.out.println("UPDATE STUDENT");

        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            System.out.println(" Student not found with ID: " + studentId);
            return;
        }

        System.out.println("\nCurrent Student Information:");
        student.displayDetailedInfo();

        System.out.println("\nEnter new information (press Enter to keep current values):");

        System.out.print("New Name [" + student.getName() + "]: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) newName = null;

        System.out.print("New Age [" + student.getAge() + "]: ");
        String ageInput = scanner.nextLine().trim();
        int newAge = -1;
        if (!ageInput.isEmpty()) {
            try {
                newAge = Integer.parseInt(ageInput);
                if (!InputValidator.isValidAge(newAge)) newAge = -1;
            } catch (NumberFormatException ignored) {
            }
        }

        System.out.print("New Course [" + student.getCourse() + "]: ");
        String newCourse = scanner.nextLine().trim();
        if (newCourse.isEmpty()) newCourse = null;

        System.out.print("New Email [" + student.getEmail() + "]: ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty() && !InputValidator.isValidEmail(newEmail)) newEmail = null;

        System.out.print("New Phone Number [" + student.getPhoneNumber() + "]: ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty() && !InputValidator.isValidPhoneNumber(newPhone)) newPhone = null;

        if (studentService.updateStudent(studentId, newName, newAge, newCourse, newEmail, newPhone)) {
            System.out.println(" Student information updated successfully!");
            student.displayDetailedInfo();
        } else {
            System.out.println(" Failed to update student information.");
        }
    }

    private void deleteStudentProcess() {
        System.out.println("DELETE STUDENT");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            System.out.println(" Student not found with ID: " + studentId);
            return;
        }

        System.out.println("\nStudent to be deleted:");
        student.displayDetailedInfo();

        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            if (studentService.deleteStudent(studentId)) {
                System.out.println(" Student record deleted successfully!");
            } else {
                System.out.println(" Failed to delete student record.");
            }
        } else {
            System.out.println(" Deletion cancelled.");
        }
    }

    private void searchStudentProcess() {
        System.out.println("SEARCH STUDENT");
        System.out.print("Enter Student ID to search: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            System.out.println(" No student found with ID: " + studentId);
        } else {
            System.out.println(" Student found!");
            student.displayDetailedInfo();
        }
    }

    private void searchByCourseProcess() {
        System.out.println("SEARCH BY COURSE");
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine().trim();

        List<Student> courseStudents = studentService.findStudentsByCourse(courseName);
        if (courseStudents.isEmpty()) {
            System.out.println(" No students found for course: " + courseName);
        } else {
            System.out.println(" Found " + courseStudents.size() + " student(s) in course: " + courseName);
            System.out.printf("%-12s %-20s %-5s %-15s %-25s %-15s%n",
                    "STUDENT ID", "NAME", "AGE", "COURSE", "EMAIL", "PHONE");
            for (Student student : courseStudents) {
                System.out.println(student);
            }
        }
    }

    private void displayStatistics() {
        System.out.println("SYSTEM STATISTICS");

        int totalStudents = studentService.getTotalStudents();
        System.out.println(" Total Students: " + totalStudents);

        if (totalStudents > 0) {
            Map<String, Integer> courseCount = new HashMap<>();
            for (Student student : studentService.getAllStudents()) {
                String course = student.getCourse();
                if (course != null && !course.isEmpty()) {
                    courseCount.put(course, courseCount.getOrDefault(course, 0) + 1);
                }
            }

            System.out.println("\n Students per Course:");
            for (Map.Entry<String, Integer> entry : courseCount.entrySet()) {
                System.out.println("   " + entry.getKey() + ": " + entry.getValue() + " student(s)");
            }
        }
    }

    private void exitApplication() {
        System.out.println(" EXIT APPLICATION");
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            System.out.println("\n Thank you for using Student Management System lmao!");
            System.out.println(" bye ig !");
            scanner.close();
        } else {
            System.out.println(" Returning to main menu lol...");
        }
    }
}
