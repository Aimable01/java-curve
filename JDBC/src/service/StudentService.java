package service;

import database.DbConn;
import model.StudentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS student(
                    student_id SERIAL PRIMARY KEY,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL
                )
                """;

        try(
                Connection conn = DbConn.getConnection();
                Statement stmt = conn.createStatement();
        ){
            stmt.executeUpdate(sql);
            System.out.println("Table ensured to exist");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertStudent(String firstName, String lastName) {
        String sql = "INSERT INTO student(first_name, last_name) VALUES(?,?)";

        try(
                Connection conn = DbConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            stmt.executeUpdate();
            System.out.println("Student inserted successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try(
                Connection conn = DbConn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            while (rs.next()){
                students.add(new StudentModel(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return students;
    }

    public StudentModel getStudentById(int student_id) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        StudentModel student = null;

        try(
                Connection conn = DbConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, student_id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    student = new StudentModel(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return student;
    }
}
