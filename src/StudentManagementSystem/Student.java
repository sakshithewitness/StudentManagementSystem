package StudentManagementSystem;

import java.util.Collection;

public class Student {

   private static String studentId ;
    private static String name;
    private static int age;
    private static String course;
    private static String email;
    private static String phoneNumber;

    public Student(String studentId, String name, int age, String course, String email, String phoneNumber) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-20s %-5d %-15s %-25s %-15s",
                studentId, name, age, course, email, phoneNumber);
    }

    public static Collection<Student> displayDetailedInfo() {

        System.out.println("Student ID    : " + studentId);
        System.out.println("Name          : " + name);
        System.out.println("Age           : " + age);
        System.out.println("Course        : " + course);
        System.out.println("Email         : " + email);
        System.out.println("Phone Number  : " + phoneNumber);

        return null;
    }
}
