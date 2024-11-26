package day2.q5;

import java.util.ArrayList;
import java.util.List;

public abstract class GradeBook<T> {
    protected String subject;
    protected List<T> grades;

    public GradeBook(String subject) {
        this.subject = subject;
        this.grades = new ArrayList<>();
    }

    public void addGrade(T grade) {
        grades.add(grade);
    }

    public List<T> getAllGrades() {
        return new ArrayList<>(grades);
    }

    public String getSubject() {
        return subject;
    }

    // Abstract methods to be implemented by specific grade book types
    public abstract double getAverageGrade();
    public abstract T getHighestGrade();
    public abstract T getLowestGrade();
} 