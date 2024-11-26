package day2;

import java.util.ArrayList;
import java.util.List;

public class Lists2 {
    public static void main(String[] args) {
        // Example with String list
        List<String> stringList = new ArrayList<>();
        addElements(stringList, "Hello", "World");
        System.out.println("String List: " + stringList);
        
        // Example with Integer list
        List<Integer> intList = new ArrayList<>();
        addElements(intList, 1, 2, 3, 4, 5);
        System.out.println("Integer List: " + intList);
    }
    
    public static void addElements(List<?> list, Object... elements) {
        for (Object element : elements) {
            ((List)list).add(element);
        }
    }
}
