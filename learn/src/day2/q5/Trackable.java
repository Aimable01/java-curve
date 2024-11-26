package day2.q5;

public interface Trackable<T> {
    void updateGrade(int index, T newGrade);
    boolean isPassingGrade(T grade);
    String generateReport();
} 