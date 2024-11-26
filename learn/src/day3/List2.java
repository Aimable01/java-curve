package day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class List2 {
    public static void main(String[] args) {
        // Create and initialize the list
        List<Integer> numbers = new ArrayList<>();
        numbers.add(52);
        numbers.add(10);
        numbers.add(77);
        numbers.add(43);
        numbers.add(15);

        // Sort using custom comparator
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                // Get last digits of both numbers
                int lastDigit1 = num1 % 10;
                int lastDigit2 = num2 % 10;
                
                // Compare last digits
                return Integer.compare(lastDigit1, lastDigit2);
            }
        });

        // Print the sorted list
        System.out.println("Sorted list according to last digit: " + numbers);
    }
} 