import java.util.*;
import java.io.*;

public class Character implements Serializable
{
    private String character_type;
    private int health;     // health variable
    private ArrayList<Item> inventory = new ArrayList();    // inventory array of type Item with a size of 10.
    private Item weapon_equipped;   // Current weapon equipped.
    private boolean alive = true;   // character alive status.

    public Character(int hhealth) {
        health = hhealth;
    }
    
    public void changeStatus() {    // Changes the status of a character from alive to dead.
        alive = false;
    }
    
    public String getStatus(){  // Returns the alive status of a character.
        if (alive == true) {
            return "Is Alive";
        }
        else {
            return "Is Dead";
        }
    }
    
    public String getWeaponEquippedName(){  // Returns the name of the current weapon equipped by a character.
        return weapon_equipped.getItemName();
    }
    
    public Item getWeaponEquipped(){    // Returns the Item object that is currently equipped by the player.
        return weapon_equipped;
    }
    
    public void setWeaponEquipped(Item weapon) {    // Sets the 'weapon_equipped' instance variable to the 'weapon' object passed.
        weapon_equipped = weapon;
    }
    
    public void setCharacterType(String type) {     // Sets the character type.
        character_type = type;
    }
    
    public String getCharacterType() {  // Returns the character type.
        return character_type;
    }
    
    public String increaseHealth(int amount) {    // Increases character's health by 'amount' amount.
        health = health + amount;
        return "<html>+"+amount+" hp<br>Your health is now "+ getHealth()+"hp";
    }
    
    public String decreaseHealth(int amount) {   // Decreases character's health by 'amount' amount.
        health = health - amount;
        return "-"+amount+" hp";
    }
    
    public void setHealth(int amount) { // Sets the Character's health to 'amount' amount.
        health = amount;
    }
    
    public int getHealth(){ //Returns the health of the Character. 
        return health;
    }
    
    public ArrayList<Item> getInventory(){   // Returns the inventory ArrayList.
        return inventory;
    }
    
    public void addItem(Item item) {    //Adds 'item' object to Item array.
          inventory.add(item);
    }
    
    public void removeItem(Item item) { //Removes item 'item' from their inventory.
        inventory.remove(item);
    }
    
    public String showInventory(){  // Returns the whole contents of the character's inventory.
        String list = "<html>Inventory:<br>";
        for (int i = 0; i<inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getClass() == healingPotion.class || item.getClass() == damagePotion.class) {
                list += ("["+(i+1)+"] "+item.getItemName()+" ("+item.getContents()+"ml remaining)<br>");
            }
            else {
                list += ("["+(i+1)+"] "+item.getItemName()+" ("+item.getDamageImpact()+" damage)<br>");
            }
        }
        return list;
    }
    
    public String showPotions(Enemy enemy) { // Shows the potions within the player's inventory.
        String potions = "<html>Potions:<br>";
        boolean no_potions = true;
        for (int i = 0; i<inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getClass() == healingPotion.class || item.getClass() == damagePotion.class){
                if (item.getContents() <= 0) {
                    inventory.remove(i);
                }
                else{
                    potions += ("["+(i+1)+"] "+item.getItemName()+" "+item.getContents()+"ml remaining<br>");
                    no_potions = false;
                }
            }
        }
        if (no_potions == true) {
            return "No potions</html>";
        }
        else{
            return potions+"</html>";
        }
    }
    
    public String usePotion(Enemy enemy, int input){ // method for when the player has decided what potion to use and executes it onto the enemy.
        Item item = inventory.get(input);
        String label = "<html>";
        if (item.getItemName().equals("Healing Potion")) {
            increaseHealth(item.getDamageImpact());
            item.reduceContents();
            label = label + "You used a Healing Potion.<br>";
        }
        else if (item.getItemName().equals("Damage Potion")) {
            enemy.decreaseHealth(item.getDamageImpact());
            item.reduceContents();
            label = label + "You used a Damage Potion.<br>";
            if (enemy.getHealth() <= 0) {
                enemy.changeStatus();
            }
            else {
               label = label + enemy.getCharacterType()+"'s health is now "+enemy.getHealth()+"hp<br>";
            }
        }
        if (item.getContents() != 0) {
            label = label+ item.getItemName()+" ["+(input)+"] "+item.getContents()+"ml remaining";
        }
        else {
            removeItem(item);
        }
        return label;
    }
    
    public String attack(Character defender){ //Method for when a character decides to attack.
        return defender.decreaseHealth(weapon_equipped.getDamageImpact());
    }
}   
