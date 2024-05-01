import java.io.*;
import java.util.*;

class FileWriter {
       
   public static void saveGame(Player player) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(player.getName().toUpperCase()+".txt"));
        out.writeObject(player);
    }
    
}