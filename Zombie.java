public class Zombie extends Enemy {
    public Zombie(int health, int damage_impact) { // Inventory of zombie consists of Zombie Sword, healing potion and damage potion.
        super(health, damage_impact);
        setCharacterType("Zombie");
        Sword zombie_sword = new Zombie_Sword(damage_impact);
        Potion heal_potion = new healingPotion(damage_impact+5, 100);
        Potion damage_potion = new damagePotion(damage_impact+5, 100);
        addItem(zombie_sword);
        addItem(heal_potion);
        addItem(damage_potion);
        setWeaponEquipped(zombie_sword);
    }
}    