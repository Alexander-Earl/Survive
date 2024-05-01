public class Warrior extends Player {
    
    private int power_points; // keeps track of the current number of Power Points the player has collected.
    private static final long serialVersionUID = -2089396706585389939L;

    public Warrior(int health) {
        super(health);
    }
    
    public int getMana(){    //  Returns the current number of Power Points.
        return power_points;
    }
    
    public void reducePowerPoints(int amount) { // reduces Power Points by 'amount' amount.
        power_points = power_points - amount;
    }
    
    public void usePowerPoints(Enemy defender) { // Uses power points by doing a power point attack on the enemy and reduces the power points variable by 5.
        pp_attack(defender);
        reducePowerPoints(5);
    }
    
    public void pp_attack(Enemy defender){ // Performs an attack on the user of 5 times weapon_equipped damage.
        defender.decreaseHealth(getWeaponEquipped().getDamageImpact()*5);
    }
    
    public void attack(Enemy defender){ // Overridden method that performs a regular attack of on the enemy. This method also increments the power points on every attack.
        power_points++;
        super.attack(defender);
    }
    
    public String showStats(){ // Overridden method that shows all player stats including the number of Power Points if they are a Warrior.
        String stats = super.showStats()+"<br>Power Points: "+getMana()+"</html>";
        return stats;
    }
    
    public void choice(Enemy defender, int input, int potion) { // Overidden method that displays a 4th option to use Power Points when the player has 5 or more power points.
        if (input == 1){
            attack(defender);
        }
        else if (input == 2) {
            usePotion(defender, potion);
        }
        else if (input == 3) {
            showStats();
            choice(defender, input, potion);
        }
        else if (input == 4) {
            usePowerPoints(defender);
        }
    }
}   