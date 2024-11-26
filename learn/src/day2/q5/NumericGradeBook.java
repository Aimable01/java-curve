package day2.q5;

public class NumericGradeBook extends GradeBook<Double> implements Trackable<Double> {
    private double passingThreshold;

    public NumericGradeBook(String subject, double passingThreshold) {
        super(subject);
        this.passingThreshold = passingThreshold;
    }

    @Override
    public double getAverageGrade() {
        return grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public Double getHighestGrade() {
        return grades.stream()
                .max(Double::compareTo)
                .orElse(0.0);
    }

    @Override
    public Double getLowestGrade() {
        return grades.stream()
                .min(Double::compareTo)
                .orElse(0.0);
    }

    @Override
    public void updateGrade(int index, Double newGrade) {
        if (index >= 0 && index < grades.size()) {
            grades.set(index, newGrade);
        }
    }

    @Override
    public boolean isPassingGrade(Double grade) {
        return grade >= passingThreshold;
    }

    @Override
    public String generateReport() {
        return String.format("Subject: %s\nAverage: %.2f\nHighest: %.2f\nLowest: %.2f",
                subject, getAverageGrade(), getHighestGrade(), getLowestGrade());
    }
} 