package exceptions;

import java.util.Scanner;

public class AgeHandler {

    public int age;

    public void getAge(){

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            age = sc.nextInt();

            if(age < 18) throw new AgeExceptionHandler("Must be over 18 years.");

            System.out.println("You are eligible to participate");
        } catch (AgeExceptionHandler e) {
            System.out.println(e.getMessage());
        }
    }
}
