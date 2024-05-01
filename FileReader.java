import java.io.*;
import java.util.Scanner;
import java.nio.file.*;

class FileReader {

    public static String readFile(String text_file)  {
        String text = "";   
        try {
            File file = new File(text_file);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                text = text + scanner.nextLine();
            }
            return text;
        }
        catch (FileNotFoundException e) {
            text = "File cannot be found.";
        }
        return text;
    }
}