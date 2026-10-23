package arquivos;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {

    public static void main(String[] args) {

        String [] lines = new String [] {"And here we go again"};

        //String path = "C:\\Users\\Luis Fernando\\Desktop/out.txt";
        String path = "files/out.txt";

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(path, true))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
