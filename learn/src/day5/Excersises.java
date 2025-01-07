package day5;

import java.util.EnumSet;

public class Excersises {
    // Enum for Weekdays
    enum WeekDays {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    // Enum for Planets
    enum Planet {
        MERCURY, VENUS, EARTH, MARS;

        public String printMessage() {
            if (this == EARTH) {
                return this + " is a habitable planet.";
            } else {
                return this + " is not a habitable planet.";
            }
        }
    }

    // Enum for Seasons
    enum Season {
        SPRING("Cold season"),
        SUMMER("Hot season"),
        AUTUMN("Warm season"),
        WINTER("Cold season");

        private final String description;

        Season(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Enum for TrafficLight
    enum TrafficLight {
        RED("Stop"),
        YELLOW("Get Ready"),
        GREEN("Go");

        private final String action;

        TrafficLight(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }
    }

    // Enum for Colors
    enum Color {
        RED, GREEN, BLUE;
    }

    public static void main(String[] args) {
        // 1. Switch case to determine if a day is a weekday or weekend
        for (WeekDays day : WeekDays.values()) {
            switch (day) {
                case SATURDAY, SUNDAY -> System.out.println(day + " is a weekend.");
                default -> System.out.println(day + " is a weekday.");
            }
        }

        System.out.println();

        // 2. Comparing ordinal positions of enum values
        Planet p1 = Planet.MERCURY;
        Planet p2 = Planet.MARS;
        System.out.println("Comparing " + p1 + " and " + p2 + ":");
        if (p1.ordinal() < p2.ordinal()) {
            System.out.println(p1 + " comes before " + p2);
        } else if (p1.ordinal() > p2.ordinal()) {
            System.out.println(p1 + " comes after " + p2);
        } else {
            System.out.println(p1 + " and " + p2 + " are the same.");
        }

        System.out.println();

        // 3. Iterating over the enum Color
        System.out.println("Iterating over Color enum:");
        for (Color color : Color.values()) {
            System.out.println(color);
        }

        System.out.println();

        // 4. TrafficLight with getAction()
        System.out.println("TrafficLight actions:");
        for (TrafficLight light : TrafficLight.values()) {
            System.out.println(light + ": " + light.getAction());
        }

        System.out.println();

        // 5. EnumSet for Weekdays
        EnumSet<WeekDays> weekdays = EnumSet.range(WeekDays.MONDAY, WeekDays.FRIDAY);
        System.out.println("Weekdays:");
        for (WeekDays day : weekdays) {
            System.out.println(day);
        }

        System.out.println();

        // Printing messages for planets
        for (Planet planet : Planet.values()) {
            System.out.println(planet.printMessage());
        }

        System.out.println();

        // Printing descriptions of seasons
        for (Season season : Season.values()) {
            System.out.println(season + ": " + season.getDescription());
        }
    }
}
