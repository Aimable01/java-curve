package day2.q5;

import java.util.HashMap;
import java.util.Map;

public class LetterGradeBook extends GradeBook<String> implements Trackable<String> {
    private static final Map<String, Double> GRADE_VALUES = new HashMap<>();
    
    static {
        GRADE_VALUES.put("A", 4.0);
        GRADE_VALUES.put("B", 3.0);
        GRADE_VALUES.put("C", 2.0);
        GRADE_VALUES.put("D", 1.0);
        GRADE_VALUES.put("F", 0.0);
    }

    public LetterGradeBook(String subject) {
        super(subject);
    }

    @Override
    public double getAverageGrade() {
        return grades.stream()
                .mapToDouble(grade -> GRADE_VALUES.getOrDefault(grade, 0.0))
                .average()
                .orElse(0.0);
    }

    @Override
    public String getHighestGrade() {
        return grades.stream()
                .min((g1, g2) -> GRADE_VALUES.get(g2).compareTo(GRADE_VALUES.get(g1)))
                .orElse("F");
    }

    @Override
    public String getLowestGrade() {
        return grades.stream()
                .max((g1, g2) -> GRADE_VALUES.get(g2).compareTo(GRADE_VALUES.get(g1)))
                .orElse("F");
    }

    @Override
    public void updateGrade(int index, String newGrade) {
        if (index >= 0 && index < grades.size() && GRADE_VALUES.containsKey(newGrade)) {
            grades.set(index, newGrade);
        }
    }

    @Override
    public boolean isPassingGrade(String grade) {
        return !grade.equals("F");
    }

    @Override
    public String generateReport() {
        return String.format("Subject: %s\nGPA: %.2f\nHighest: %s\nLowest: %s",
                subject, getAverageGrade(), getHighestGrade(), getLowestGrade());
    }
} 