package misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TreeStructure {

    public static void print(){
        print();
        System.out.println("Data structure");
    }

    public static void main(String[] args) {

//        Set<String> districts = new TreeSet<String>();

        Set<String> districts = new HashSet<>();

        districts.addAll(Arrays.asList("HUYE","HUYE","RUHANGO","KICUKIRO","BUGESERA","NYARUGENGE","HUYE","GASABO","NYANZA","HUYE","GASABO","BUGESERA","HUYE","MUSANZE","NYARUGENGE","MUHANGA","HUYE","NYAGATARE","NYARUGENGE","GASABO","MUSANZE","HUYE","HUYE","HUYE","GASABO"));

        int i = 1;
        for(String district : districts) {
            System.out.println(i + ". " + district);
            i++;
        }
    }
}
