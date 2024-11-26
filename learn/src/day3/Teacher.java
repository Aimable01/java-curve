package day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teacher implements Comparable<Teacher> {
    int teacherId;
    String teacherName;

    public Teacher(int teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return teacherId + " " + teacherName;
    }

    @Override
    public int compareTo(Teacher other) {
        return this.teacherName.compareTo(other.teacherName);
    }

    public static void main(String[] args) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(new Teacher(1, "Beni"));
        teachers.add(new Teacher(2, "Nicole"));
        teachers.add(new Teacher(3, "Annick"));
        teachers.add(new Teacher(4, "Kalisa"));

        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        Collections.sort(teachers);

        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}
