package day2;

import java.util.ArrayList;

public class MergeLists {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            list1.add(4);
            list1.add(5);
        ArrayList<Integer> list2 = new ArrayList<>();
            list2.add(6);
            list2.add(7);
            list2.add(8);
            list2.add(9);
            list2.add(10);
        ArrayList<Integer> mergedList = new ArrayList<>();
            mergedList.addAll(list1);
            mergedList.addAll(list2);
        System.out.println("List 1: "+list1);
        System.out.println("List 2: "+list2);
        System.out.println("Merged List: "+mergedList);
    }
}
