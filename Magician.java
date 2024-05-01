public class Magician extends Player {
    
    private int steal_points; // Used for holding the current amount of mana the player possesses.
    
    public Magician(int health) {
        super(health);
    }
    
    public void attack(Enemy defender){ // Overridden attack method from class however every attack from a magician increases their steal points amount.
        super.attack(defender);
        steal_points++;
    }
    
    public int getMana(){ // Returns current mana.
        return steal_points;
    }
    
    public void reduceMana(int amount) { // Reduces mana by 'amount' amount
        steal_points = steal_points - amount;
    }
    
    public void steal(Enemy enemy, int num) { // When a player has 5 or more mana points they will be able to steal an item of their choice from the enemy's inventory.
        Item item = enemy.getInventory().get(num-1);
        System.out.println(item);
        addItem(item);
        enemy.removeItem(enemy.getInventory().get(num-1));
        reduceMana(5);
    }
    
    public String showStats(){ // Overridden method that shows all player stats including the number of steal points.
        String stats = super.showStats()+"<br>Steal Points: "+getMana()+"</html>";
        return stats;
    }
    
    public void choice(Enemy defender, int input, int selected_item) {    // Overridden method that provides the 4th option to steal an item from the enemy's inventory when the player's mana is >= 5.
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
        else if (input == 4) {
            steal(defender, selected_item);
        }
    }
}