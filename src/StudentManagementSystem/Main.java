package StudentManagementSystem;

public class Main {

        public static void main(String[] args) {
            try {
                StudentManagementUI ui = new StudentManagementUI();
                ui.start();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
