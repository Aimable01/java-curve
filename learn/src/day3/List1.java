package day3;

import java.util.ArrayList;
import java.util.Collections;

public class List1 {
    public static void main(String[] args) {
        // Create ArrayList and add numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(20);
        numbers.add(15);
        numbers.add(1);
        numbers.add(0);
        numbers.add(3);
        numbers.add(4);

        System.out.println("Original list: " + numbers);

        Collections.sort(numbers);

        System.out.println("Sorted list: " + numbers);
    }
} 