package StudentManagementSystem;

import java.util.*;
public class StudentService {
    private static Map<String, Student> students = new HashMap<>();

    public StudentService() {
        if (students == null) {
            students = new HashMap<>();
        }
    }
    public boolean addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            return false;
        }
        students.put(student.getStudentId(), student);
        return true;
    }
    public static Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student findStudentById(String studentId) {
        return students.get(studentId);
    }
    public boolean updateStudent(String studentId, String name, int age, String course, String email, String phoneNumber) {
        Student student = students.get(studentId);
        if (student == null) {
            return false;
        }

        if (name != null && !name.trim().isEmpty()) {
            student.setName(name);
        }
        if (age > 0) {
            student.setAge(age);
        }
        if (course != null && !course.trim().isEmpty()) {
            student.setCourse(course);
        }
        if (email != null && !email.trim().isEmpty()) {
            student.setEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            student.setPhoneNumber(phoneNumber);
        }
        return true;
    }
    public boolean deleteStudent(String studentId) {
        return students.remove(studentId) != null;
    }
    public boolean isEmpty() {
        return students.isEmpty();
    }
    public int getTotalStudents() {
        return students.size();
    }
    public List<Student> findStudentsByCourse(String courseName) {
        List<Student> courseStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getCourse().equalsIgnoreCase(courseName)) {
                courseStudents.add(student);
            }
        }
        return courseStudents;
    }
}
