package streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataStream {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new FileInputStream("C:\\Users\\Aimable\\Desktop\\misc\\source.txt"));
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
