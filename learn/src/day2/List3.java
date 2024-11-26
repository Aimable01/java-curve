package day2;

import java.util.ArrayList;
import java.util.List;

public class List3 {
    public static int findMaxValue(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        
        int max = numbers.get(0);
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(9);
        numbers.add(1);
        numbers.add(7);

        int maxValue = findMaxValue(numbers);
        System.out.println("Maximum value in the list: " + maxValue);
    }
} 