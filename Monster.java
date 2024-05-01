public class Monster extends Enemy {
    public Monster(int health, int damage_impact){ // Inventory consists of a damage potion, healing potion and 1950s shield.
        super(health, damage_impact);
        setCharacterType("Monster");
        Potion damage_potion = new damagePotion(damage_impact+15, 100);
        addItem(damage_potion);
        Potion healing_potion = new healingPotion(damage_impact+15, 100);
        addItem(healing_potion);
        Shield shield = new Shield("1950s Shield", damage_impact);
        addItem(shield);
        setWeaponEquipped(shield);
    }
    
    public String bite(Player player, int damage) {   // For when the monster decides to bite the player.
        return "<html>AHHHHH!<br>You have been bitten!<br>"+player.decreaseHealth(damage)+" <br>Your health is now "+player.getHealth()+"hp";
    }
    
    public String choice(Player defender, int rng) { // Monster choices when it is their turn to attack during battle.
        if (rng == 1 ){
           return attack(defender);
        }
        else if (rng == 2) {
           return usePotion(defender);
        }
        else if (rng == 3) {
            int num = World.dice(25, 5);
           return bite(defender, num);
        }
        return "Monster choice return error";
    }
}