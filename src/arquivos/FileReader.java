package arquivos;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    public static void main(String[] args) {

        //String path = "files/in.txt";
        String path = "files/out.txt";

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();

            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
