import model.StudentModel;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);

        // Ensure the table exists
        studentService.createTableIfNotExists();

        while (true) {
            // Display menu options
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a new student");
            System.out.println("2. Retrieve all students");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    // Insert a new student
                    System.out.println("Enter the student details:");
                    System.out.print("Enter the first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter the last name: ");
                    String lastName = scanner.nextLine();

                    studentService.insertStudent(firstName, lastName);
                    System.out.println("Student added successfully.");
                }
                case 2 -> {
                    // Retrieve and display all students
                    System.out.println("\nAll Students:");
                    List<StudentModel> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (StudentModel student : students) {
                            System.out.println(student);
                        }
                    }
                }
                case 3 -> {
                    // Search for a student by ID
                    System.out.print("Enter the student ID to search: ");
                    int studentID = scanner.nextInt();
                    StudentModel student = studentService.getStudentById(studentID);

                    System.out.println("\nSearching for student with ID " + studentID);
                    if (student != null) {
                        System.out.println("Found: " + student);
                    } else {
                        System.out.println("Student with ID " + studentID + " not found.");
                    }
                }
                case 4 -> {
                    // Exit the program
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
