package exceptions;

import java.util.Scanner;

public class ExceptionHandlingStarter {

    public int num1;
    public int num2;
    public int result;


    public void GetResult(){

        Scanner sc = new Scanner(System.in);

        // get the numbers
        System.out.print("Enter the numerator: ");
        num1 = sc.nextInt();

        System.out.print("Enter the denominator: ");
        num2 = sc.nextInt();

        try {
            if (num1 < num2 || num2 == 0) {
                throw new NumeratorExceptionStarter("Numerator should be greater than denominator && denominator should never be zero");
            } else {
                result = num1 / num2;
            }

            System.out.println("The result is: "+result);
        } catch (NumeratorExceptionStarter ex) {
            System.out.println(ex.getMessage());
        }
    }
}
