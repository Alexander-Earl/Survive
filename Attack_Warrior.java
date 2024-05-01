public class Attack_Warrior extends Warrior {

    public Attack_Warrior() { // Attacking warrior has starting inventory of a Starting Sword, healing potion and damage potion.
        super(100);
        setCharacterType("Attacking Warrior");
        Hands hands = new Hands(5);
        addItem(hands);
        Sword sword = new Sword("Starting Sword", 10);
        addItem(sword);
        setWeaponEquipped(sword);
        Potion potion = new healingPotion(50, 100);
        addItem(potion);
        Potion damagepotion = new damagePotion(50, 100);
        addItem(damagepotion);
        
    }

}