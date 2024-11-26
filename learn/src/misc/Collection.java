package misc;

import java.util.ArrayList;

public class Collection {
    public static void main(String[] args) {
        ArrayList<Object> c = new ArrayList<>();
        c.add(2);
        c.add(3);
        c.add(5);
        for(Object num:c){
            System.out.println(num);
        }

        PrintInt num = new PrintInt(5);
        num.display();

        PrintAny<Integer> num2 = new PrintAny<>(5);
        num2.display();

        PrintAny<String> word = new PrintAny<>("hello");
        word.display();
    }
}
