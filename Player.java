import java.util.*;
import java.io.*;

public class Player extends Character {
    private final int END_POINT = 30; // The number of miles the player has to walk until he reaches the end of town.
    private String name; // Variable for holding the player's name.
    private int walked = 0; // The current distance walked.
    private int level_completed = 0;
    private static final long serialVersionUID = -2481497429225178460L;

    public Player (int health){
       super(health);
       Item hands = new Hands(10);  // Hands are equipped as default equipped weapon.
       setWeaponEquipped(hands);
    }

    public void setName(){ // Sets the selected character's name to player's input.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your name:");
        String player_name = scanner.nextLine();
        name = player_name;
    }
    
    public void setName(String name) {  // Sets the selected character's name to 'name'.
        this.name = name;
    }
    
    public String getName() {   // Returns the name of the player.
        return name;
    }
    
    public int getLevelCompleted(){ // Returns the number of levels completed.
        return level_completed;
    }
    
    public void setLevelCompleted(int level) { // sets the levels completed attribute to the value of "level"
        level_completed = level;
    }
    
    public int getEndPoint(){ // Returns the end point of the world (i.e. the end of town).
        return END_POINT;
    }
    
    public int getMana() { // Returns mana. (This method is overridden in Magician and Warrior classes, hence why it returns 0)
        return 0;
    }
    
    public String interaction(String input) throws FileNotFoundException, IOException, ClassNotFoundException{ // Method used for player to character interation when in the world.
    if (input.equals("W")) {
           return walk(World.dice(4,1));
       }
    else if (input.equals("C")) {
        String weapons = "<html>Weapons:<br>";
        boolean no_weapons = true;
        int num_of_weapons = 0;
        for (int i = 0; i<getInventory().size(); i++) {
            Item item = getInventory().get(i);
            if (item.getClass() == Sword.class || item.getClass() == Shield.class || item.getClass() == Zombie_Sword.class || item.getClass() == Hands.class){
                weapons += ("["+(i+1)+"] "+item.getItemName()+" ("+item.getDamageImpact()+" damage)<br>");
                no_weapons = false;
                num_of_weapons++;
            }
        }
        if (no_weapons == true) {
            return "No weapons.";
        }
        else if (num_of_weapons == 1) {
            return "You only have one weapon.";
        }
        else{
              return weapons;
            }
        }
    else if (input.equals("S")) {
        FileWriter.saveGame(this);
        }
        return null;
    }
    public int getWalked() {    // Returns the amount the player has walked.
        return walked;
    }
    
    public void setWalked(int amount){ // Sets the player's walk attribute to value "amount"
        walked = amount;
    }
    
    public Item getWeaponEquipped(){    // Returns the equipped weapon Item obect.
        return super.getWeaponEquipped();
    }
    
    public void choice(Enemy defender, int input, int selected_item) {    // Options the player can choose from when in battle.
        if (input == 1){
            attack(defender);
        }
        else if (input == 2) {
            usePotion(defender, selected_item);
        }
        else if (input == 3) {
            showStats();
            choice(defender, input, selected_item);
        }
    }
    
    public String walk(int amount){   // Player walks by 'amount' amount.
        walked = walked + amount;
        if (walked >= END_POINT) {
            return "You have completed the game.\nWell Done!";
        }
        else {
            return "<html>You have walked "+getWalked()+" miles so far.<br>Only "+(END_POINT - walked)+" miles until you reach the end of the town.</html>";
        }
    }
    
    public String showStats(){    // Displays all current stats regarding the player.
        String stats = "<html><br>Name: "+ getName()+ "<br>Type: "+ getCharacterType() + "<br>Health: "+ getHealth() +"<br>Weapon Equipped: "+ getWeaponEquippedName()+"<br>Miles Walked: "+getWalked()+"<br>"+showInventory()+"<br>Level Completed: "+getLevelCompleted();
        return stats;
    }

}