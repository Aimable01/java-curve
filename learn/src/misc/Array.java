package misc;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        int[] myarray = new int[5];

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 5; i++){
            System.out.print("Enter element " + i + ": ");
            myarray[i] = sc.nextInt();
        }

        for(int i = 0; i < 5; i++){
            System.out.println("Element " + i + "- " + myarray[i]);
        }
    }
}
