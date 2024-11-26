package day3;

import java.util.HashMap;
import java.util.Map;

public class Map1 {
    public static void main(String[] args) {

        Map<String, Integer> marks = new HashMap<>();
        marks.put("Aimable", 80);
        marks.put("Salim", 70);
        marks.put("Sylvie", 75);
        marks.put("Kelia", 81);


        for (String name : marks.keySet()) {
            System.out.println(name + ": " + marks.get(name));
        }

        // update Salim to 85
        marks.put("Salim", 85);
        System.out.println("Salims new value is: "+marks.get("Salim"));

        // check if we have the value 70
        System.out.println("Do we have the value 70? " + marks.containsValue(70));

        // add Goal: 84 if absent
        marks.putIfAbsent("Goal", 84);

        System.out.println("After adding Goal: 84 if absent");

        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
