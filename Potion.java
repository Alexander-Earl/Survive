public class Potion extends Item {
    private int contents;

    public Potion(String name, int health_impact, int contents){
        super(name, health_impact);
        this.contents = contents;
    }
    
    public int getContents(){ // Reduces the contents of a potion (e.g. 100ml to 80ml).
       return contents; 
    }
    
    public void reduceContents(){ // Returns the current contents of a potion.
       contents = contents - 25;
    }
    
}