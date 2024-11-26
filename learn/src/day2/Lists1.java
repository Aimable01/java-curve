package day2;

import java.util.ArrayList;

public class Lists1 {
    public <T> void addElementsGeneric(ArrayList<T> list, T... elements) {
        for (T element : elements) {
            list.add(element);
        }
    }

    public void addElements(ArrayList<Integer> list, int n)  {
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }
    public static void main(String[] args) {
        Lists1 lists = new Lists1();
        
        // Example usage with different types
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();
        
        lists.addElementsGeneric(stringList, "Hello", "World");
        lists.addElementsGeneric(intList, 1, 2, 3, 4, 5);

        System.out.println(stringList);
    }
}
