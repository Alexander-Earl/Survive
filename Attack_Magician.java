public class Attack_Magician extends Magician { // Will start with a damage potion.
    public Attack_Magician(){
        super(100);
        setCharacterType("Attacking Magician");
        Hands hands = new Hands(5);
        addItem(hands);
        setWeaponEquipped(hands);
        Potion damage_potion = new damagePotion(30, 100);
        addItem(damage_potion);
    }
}