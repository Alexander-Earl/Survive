public class Defensive_Warrior extends Warrior{
    
    public Defensive_Warrior(){ // Defensive warrior will have a starting inventory of 1920's shield and a healing potion.
        super(125);
        setCharacterType("Defensive Warrior");
        Hands hands = new Hands(5);
        addItem(hands);
        Item shield = new Shield("1920s Shield", 35); // 35 damage.
        addItem(shield);
        setWeaponEquipped(shield);
        Potion healing_potion = new healingPotion(50, 100);
        addItem(healing_potion);
    }
    
}