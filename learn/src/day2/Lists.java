package day2;

import java.util.ArrayList;

public class Lists {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
            fruits.add("Apple");
            fruits.add("Banana");
            fruits.add("Mango");
            fruits.add("Orange");

            fruits.add(1,"Pineapple");
            fruits.remove(3);
        System.out.println("New list: "+fruits);
    }
}
