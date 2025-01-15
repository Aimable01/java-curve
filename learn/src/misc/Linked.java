package misc;

import java.util.LinkedList;

public class Linked {
    public static void main(String[] args) {

        LinkedList<String> districts = new LinkedList<String>();
        districts.add("Nyabihu");
        districts.add("Musanze");

        for(String district : districts){
            System.out.println(district);
        }

        System.out.println("After adding ------------------");

        districts.addFirst("Rubavu");
        districts.addLast("Kayonza");

        for(String district : districts){
            System.out.println(district);
        }
    }
}
