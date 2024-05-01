import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class ViewInventory extends GUI {

    public ViewInventory(Player player, Enemy enemy){
        ViewInventory window = this; 
        JLabel view_inventory_label = new JLabel("<html>"+enemy.getCharacterType()+" is dead.<br>Would you like to view their inventory?</html>");  
        view_inventory_label.setBounds(180,30,400,100);
        add(view_inventory_label);
        
        TextField inventory_text_field = new TextField(20);
        inventory_text_field.setBounds(150, 195, 200, 25);

        Button enter_button = new Button("Enter");
        enter_button.setBounds(225, 250, 50, 25);
        
       
        Button yes_button = new Button("Yes");
        yes_button.setBounds(100, 300, 100,100);
        add(yes_button);
        
        Button no_button = new Button("No");
        no_button.setBounds(300, 300, 100,100);
        add(no_button);
        
        yes_button.addActionListener((new ActionListener(){ 
             public void actionPerformed(ActionEvent evt) {
                yes_button.setVisible(false);
                no_button.setVisible(false);
                Button finish_button = new Button("Return to World");
                finish_button.setBounds(200, 350, 100, 25);
                add(finish_button);
                view_inventory_label.setText(enemy.showInventory());
                add(inventory_text_field);
                add(enter_button);
                enter_button.addActionListener((new ActionListener(){    
                 public void actionPerformed(ActionEvent evt) {
                         try{
                             int num = Integer.valueOf(inventory_text_field.getText());
                             Item item = enemy.getInventory().get(num-1);
                             player.addItem(item);
                             enemy.removeItem(item);
                             view_inventory_label.setText(enemy.showInventory());
                             inventory_text_field.setText("");
                             if (enemy.getInventory().size() == 0) {
                                returnToWorld(player, window);
                             }
                         }
                         catch (IndexOutOfBoundsException e) {
                             System.out.println("Index error");
                         }
                    }
                 }));
                 
                 finish_button.addActionListener((new ActionListener(){ 
                 public void actionPerformed(ActionEvent evt) {
                    returnToWorld(player, window);
                 } 
                 })); 
                 
             } 
             })); 
        
        no_button.addActionListener((new ActionListener(){ 
             public void actionPerformed(ActionEvent evt) {
                returnToWorld(player, window);
             } 
             })); 
    }
    
    public static void returnToWorld(Player player, ViewInventory window){
        try {
             FileWriter.saveGame(player); //show inventory after items have been taken. also add an escape button.
             InGame ig = new InGame(player, "<html>Your progress has been saved.<br>Level "+player.getLevelCompleted() + " Completed.<br></html>");
             window.dispose();
        }
        catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e) {System.out.println(e.getMessage());}
     }


}