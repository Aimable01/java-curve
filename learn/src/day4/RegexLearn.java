package day4;

import java.util.regex.*;

public class RegexLearn {
    public static void main(String[] args) {
        Pattern p = Pattern.compile(".rwanda");
        Matcher m = p.matcher("irwanda");
        System.out.println(m.matches());

        System.out.println(Pattern.matches("[amn]","mn"));

        System.out.println(Pattern.matches("[^ub]","n"));

        System.out.println(Pattern.matches("[amb]?",""));

        System.out.println(Pattern.matches("[amb]?","aa"));

        System.out.println(Pattern.matches("colou?r","color"));

        p = Pattern.compile("(a{1,2}|A{1,2})gent \\d{2}");
        m = p.matcher("Agent 45");
        System.out.println(m.matches());
    }
}
