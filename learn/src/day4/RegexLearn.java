package day4;

import java.util.regex.*;

public class RegexLearn {
    public static void main(String[] args) {
        Pattern p = Pattern.compile(".rwanda");
        Matcher m = p.matcher("irwanda");
        System.out.println(m.matches());

        System.out.println(Pattern.matches("[amn]","mn"));

        System.out.println(Pattern.matches("[^ub]","n"));
    }
}
