import model.StudentModel;
import service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();

        // create table
        studentService.createTableIfNotExists();

        // insert data
        studentService.insertStudent("kwizera","aimable");
        studentService.insertStudent("shema","jean");
        studentService.insertStudent("teta","tesi");

        // retrieve and display all students
        System.out.println("All students: ");
        List<StudentModel> students = studentService.getAllStudents();
        for (StudentModel student : students) {
            System.out.println(student);
        }

        // search for student using id
        int searchID = 1;
        System.out.println("\nSearching for student with ID " + searchID);
        StudentModel student = studentService.getStudentById(searchID);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student with ID " + searchID + " not found");
        }
    }
}
