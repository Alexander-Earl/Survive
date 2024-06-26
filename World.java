import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class World { 
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        WorldGUIFrame.main();
    }
    
    public static void gameMenu(Player player, int choice, String name)throws FileNotFoundException, IOException, ClassNotFoundException { // Game menu method is for displaying all options (Play game, view game rules, quit) to the user when the game runs.
       //Substitution principle applied to the player object depending upon what character the player wishes to select.
       if (choice == 3) {
           player = new Attack_Warrior();
       }
       else if (choice == 4) {
           player = new Defensive_Warrior();
       }
       else if (choice == 5) {
           player = new Attack_Magician();
       }
       else if (choice == 6) {
           player = new Defensive_Magician();
       }
       else{
           System.out.println("Error. Try again.");
           play(player);                                     // If the user enters an invalid input, the method loops and asks the player to re-enter their input.
       }
       player.setName(name);
       player.showStats();
       InGame ig = new InGame(player, welcome(player));
       play(player);
    }
    
    public static void level1(Player player) { // contains level 1 content.
        Enemy level1_zombie = new Zombie(90,5); // level 1 zombie created, 60 health and can do a base attack of 5 damage.
        player.setLevelCompleted(1);
        askToBattle a = new askToBattle(player, level1_zombie, 2);  // asks the player if they wish to battle the zombie. If yes then they battle, if not then they lose 25hp. 
    }
    
    public static void level2(Player player) { // contains level 2 content.
        Enemy level2_monster = new Monster(60,15); // level 1 zombie created, 60 health and can do a base attack of 5 damage.
        player.setLevelCompleted(2);
        askToBattle a = new askToBattle(player, level2_monster, 3);  // asks the player if they wish to battle the zombie. If yes then they battle, if not then they lose 25hp. 
    }
    
    public static void level3(Player player) { // contains level 3 content.
        Enemy level3_monster = new Monster(70, 10);
        player.setLevelCompleted(3);
        askToBattle a = new askToBattle(player, level3_monster, 3);
    }
    
    public static void level4(Player player){ // contains level 4 content.
        Enemy level4_zombie = new Zombie(80, 15);
        player.setLevelCompleted(4);
        askToBattle a = new askToBattle(player, level4_zombie, 2);
    }
    
    public static void level5(Player player) { // contains level 5 content.
        Enemy level5_monster = new Monster(90, 15);
        player.setLevelCompleted(5);
        askToBattle a = new askToBattle(player, level5_monster, 3);
    }
    
    public static void play(Player player) throws FileNotFoundException, IOException, ClassNotFoundException{ // play method is the centralised place for all in-game simulation and user interaction.
        welcome(player);
        if (player.getWalked() > 4 && player.getWalked() < 12 && player.getLevelCompleted() == 0) {
            level1(player);
        }
        else if (player.getWalked() >=12 && player.getWalked() < 15 && player.getLevelCompleted() == 1) {
            level2(player);
        }
        else if (player.getWalked() >=14 && player.getWalked() < 19 && player.getLevelCompleted() == 2) {
            level3(player);
        }
        else if (player.getWalked() >=19 && player.getWalked() < 23 && player.getLevelCompleted() == 3) {
            level4(player);
        }
        else if (player.getWalked() >=23 && player.getWalked() < 27 && player.getLevelCompleted() == 4) {
            level5(player);
        }
    }
    
    public static String welcome (Player player) { // method to welcome the player to the world.
        String welcome = "<html><p>Welcome "+ player.getName() +", to the world of Kupakeep!<br>The rules to survive are simple.<br>Just walk to the end of town.<br>Good Luck!<br><br>You have walked "+player.getWalked()+" miles.</html>";
        return welcome;
    }
    
    public static Player LoadGame(String name) throws FileNotFoundException, IOException, ClassNotFoundException { // loads the game if the player has a save state they wish to load. 
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(name.toUpperCase()+".txt"));
            Player player = (Player) in.readObject();
            if (player.getHealth() >0){
                play(player);
            } 
            else{
                System.out.println("You died from the last time you played. Please Start a New Game.");
            }
            return player;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }
    
    public static void saveGame(Player player) throws FileNotFoundException, IOException, ClassNotFoundException { // permits users to save their state.
        FileWriter.saveGame(player);
    }
    
    public static void about() throws IOException{ // Displays all game information from a text-file for new players to understand overall mechanics.
        FileReader.readFile("About.txt");
    }
    
    public static void exit(){ // Ends the program when a player decides to exit the game from the opening menu.
        System.exit(0);
    }
    
    public static int intInput(String string) { // Allows for integer input with accompanying String output.    
        Scanner scanner = new Scanner(System.in);
        System.out.println(string);
        int input;
        try {
            input = scanner.nextInt();
            return input;
        }
        catch (InputMismatchException e) {
            System.out.println("Input must only be numerical.");
            input = intInput(string);
        }
        return input;
    }
    
    public static int intInput() { // Allows for integer input without accompanying String output.   
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt(); // Exception handling
        return input;
    }
    
    public static String input(String string) { // Allows for String input with accompanying String output. 
        Scanner scanner = new Scanner(System.in);
        System.out.println(string);
        String input = scanner.nextLine();
        input = input.toUpperCase();
        return input;
    }

    public static int dice(int x, int y) {// Function that generates a random number between two variables.
        Random dice = new Random();
        int rolled = dice.nextInt(x) + y;
        return rolled;
    }// END Dice.
}