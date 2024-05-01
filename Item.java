import java.io.*;

public class Item implements Serializable {
    private String name; // Stores the name of the item.
    private int damage_inflict; // Stores the amount of damage the item can inflict on a Character.

    public Item(String name, int damage_inflict){
        this.name = name;
        this.damage_inflict = damage_inflict;
    }
    
    public String getItemName(){    // Returns the name of the item.
        return name;
    }
    
    public int getDamageImpact(){ // Returns the inflicted damage of the item.
        return damage_inflict;
    }
    
    public void reduceContents(){   // Reduces the contents of a potion (e.g. 100ml to 80ml).
    }
    
    public int getContents(){   // Returns the current contents of a potion.
      return 0;
    }

}