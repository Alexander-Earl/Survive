import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Enemy extends Character {
    private Item[] inventory = new Item[3]; // Enemy inventory of size 3.
    private int damage;     // The amount of damage the enemy can do.
    
    public Enemy(int health, int damage){
        super(health);
        this.damage = damage;
    }
    
    public String increaseHealth(int amount) { //Increases enemy character's health by 'amount' amount.
        setHealth(getHealth()+amount);
        return "<html>"+"+"+amount+" hp<br>"+getCharacterType()+" health is now "+ getHealth()+"hp";
    }
    
    public String decreaseHealth(int amount) {   // Decreases character's health by 'amount' amount.
        super.decreaseHealth(amount);
        if (getHealth() <=0) {
           changeStatus();
           return getCharacterType()+" is dead.";
        }
        else{
            return getCharacterType()+"'s health is now"+ getHealth()+"hp";
        }
    }
    
    public String battle(Player player, Enemy enemy, int options, int choice, int selected_item){ // Battle method is for when the player comes into contact with an enemy and decides to battle.
       player.choice(enemy, choice, selected_item);
       if (player.getHealth() <= 0) {
            player.changeStatus();
            return "<html>You have died<br>GAME OVER</html>";
       }
       if (getHealth() >0) {
         return "<html>------------------------------------<br>&emsp;&emsp;&emsp;"+getCharacterType()+" Turn<br>------------------------------------<br>"+choice(player, World.dice(options,1));
         
        }
        if (getHealth() <= 0){
            changeStatus();
            return "<html>"+getCharacterType()+" is dead.<br>Would you like to view their inventory?</html>";
        }
        return null;
    }
    
    public String usePotion(Player player) { // When the enemy decides to use a potion.
        String potions = "Potions:\n";
        int num_potions = 0;
        String potion_message = "<html>";
        for (int i = 0; i<getInventory().size(); i++) {
            Item item = getInventory().get(i);
            if (item.getClass() == healingPotion.class || item.getClass() == damagePotion.class){
                if (item.getContents() <= 0) {
                    removeItem(inventory[i]);
                }
                else{
                    potions += ("["+(i+1)+"] "+item.getItemName()+" "+item.getContents()+"ml remaining\n");
                    num_potions = num_potions + 1;
                }
            }
        }
        if (num_potions == 0) {
            return attack(player);
        }
        else{
           int input = World.dice(getInventory().size(),1);
           Item item = getInventory().get(input-1);
            if (item.getItemName().equals("Healing Potion")) {
                increaseHealth(item.getDamageImpact());
                potion_message = potion_message + getCharacterType()+" used a "+item.getItemName()+"<br>";
                item.reduceContents();
                if (item.getContents() > 0) {
                    potion_message = potion_message + item.getItemName()+" ["+(input)+"] "+item.getContents()+"ml remaining<br>";
                    potion_message = potion_message + getCharacterType()+"'s Health is now "+getHealth()+"hp.<br>";
                }
                else {
                    removeItem(item);
                    potion_message = potion_message + item.getItemName()+" ["+(input)+"] "+item.getContents()+"ml remaining<br>";
                }                
            }
            else if (item.getItemName().equals("Damage Potion")) {
                player.decreaseHealth(item.getDamageImpact());
                potion_message = potion_message + getCharacterType()+" used a Damage Potion.<br>";
                item.reduceContents();
                if (player.getHealth() <= 0) {
                    player.changeStatus();
                    return "GAME OVER";
                }
                else {
                    potion_message = potion_message + "Your health is now "+player.getHealth()+"hp<br>";
                }
                if (item.getContents() > 0) {
                    potion_message = potion_message + item.getItemName()+" ["+(input)+"] "+item.getContents()+"ml remaining";
                }
                else {
                    removeItem(item);
                    potion_message = potion_message + item.getItemName()+" ["+(input)+"] "+item.getContents()+"ml remaining<br>";
                }                
            }
            else if (!item.getItemName().equals("Healing Potion") || !item.getItemName().equals("Damage Potion")) {
                return attack(player);
            }
        }
        return potion_message;
    }
    
    public String attack(Character defender){ // Method for enemy attack. This method is overridden from the Character's attack method.
       super.attack(defender);
       String label = "<html>"+getCharacterType()+" decided to attack!<br>"+"Your health is now "+defender.getHealth()+"hp";
       return label+"</html>";
    }
    
    public String choice(Player defender, int rng) { // Options for the enemy character to choose from. (rng = Random Number Generated)
        if (rng == 1 ){
           return attack(defender);
        }
        else if (rng == 2) {
           return usePotion(defender);
        }
        return "Enemy choice error";
    }
}