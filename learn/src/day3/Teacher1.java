package day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Teacher1 {
    int teacherId;
    String teacherName;

    public Teacher1(int teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }


    @Override
    public String toString() {
        return teacherId + " " + teacherName;
    }

    // comparator sort by name
    public static class SortByName implements Comparator<Teacher1> {
        @Override
        public int compare(Teacher1 t1, Teacher1 t2) {
            return t1.teacherName.compareTo(t2.teacherName);
        }
    }

    public static void main(String[] args) {
        List<Teacher1> teachers = new ArrayList<Teacher1>();
        teachers.add(new Teacher1(1, "Beni"));
        teachers.add(new Teacher1(2, "Nicole"));
        teachers.add(new Teacher1(3, "Annick"));
        teachers.add(new Teacher1(4, "Kalisa"));

        System.out.println("Before sorting:");
        for (Teacher1 teacher : teachers) {
            System.out.println(teacher);
        }

        // sort by name
        Collections.sort(teachers, new SortByName());

        System.out.println("\nAfter sorting:");
        for (Teacher1 teacher : teachers) {
            System.out.println(teacher);
        }
    }
} 