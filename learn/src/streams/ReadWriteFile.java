package streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteFile {

    public static void main(String[] args) throws IOException {

        FileInputStream source = null;
        FileOutputStream destination = null;

        try{
            source = new FileInputStream("C:\\Users\\Aimable\\Desktop\\source.txt");
            destination = new FileOutputStream("C:\\Users\\Aimable\\Desktop\\destination.txt");

            int content;
            while ((content = source.read()) != -1) {
                //destination.write((byte)content);
                destination.write(content);
            }
        } finally {
            source.close();
            destination.close();
        }
    }
}
