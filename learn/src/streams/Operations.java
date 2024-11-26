package streams;

import java.io.*;

public class Operations {
    public static void main(String[] args) {

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
           in = new BufferedInputStream(new FileInputStream("C:\\Users\\Aimable\\Desktop\\source.txt"));
           out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Aimable\\Desktop\\destination.txt"));

           int content;
           while ((content = in.read()) != -1){
               out.write(content);
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
