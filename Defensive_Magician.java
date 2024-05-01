public class Defensive_Magician extends Magician { // Starts with a healing potion.
    
    public Defensive_Magician(){
        super(100);
        setCharacterType("Defensive Magician");
        Hands hands = new Hands(5);
        addItem(hands);
        setWeaponEquipped(hands);
        Potion health_potion = new healingPotion(30, 100);
        addItem(health_potion);
    }
}