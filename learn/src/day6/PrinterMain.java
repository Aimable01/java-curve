package day6;

@FunctionalInterface
interface Printer {
    int sum(int num1, int num2);
}

public class PrinterMain {

    public static void main(String[] args) {
        Printer p = (c,d)-> c+d;
        int sum = p.sum(1,2);
        System.out.println("The sum is: " + sum);
    }
}