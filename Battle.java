import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class Battle extends GUI { // setBounds(int x-coordinate, int y-coordinate, int width, int height)
    
    Button show_stats_button = new Button("Show Stats");
    Button attack_button = new Button("Attack");
    Button use_potion_button = new Button("Use Potion");
    TextField text_field = new TextField(20);
    Button enter_button = new Button("Enter");
    
    public Battle(Player player, Enemy enemy, int options) {
        Battle window = this; // This statement is so I can reference the window object from a static context.
        
        JLabel battle_label = new JLabel("<html>Battle!</html>");
        battle_label.setBounds(210,0,300,300);
        add(battle_label);
        
        checkMana(player, enemy, options, battle_label, window);
        
        text_field.setBounds(150, 195, 200, 25);
        add(text_field);
        text_field.setVisible(false);
               
        enter_button.setBounds(225, 250, 50, 25);
        add(enter_button);
        enter_button.setVisible(false);
        
        JLabel potion_exception_label = new JLabel("Select the potion through entering the number.");
        potion_exception_label.setBounds(125, 210, 420, 50);
         
        show_stats_button.setBounds(75, 395, 100,100);
        add(show_stats_button);
        show_stats_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
               battle_label.setText(player.showStats());
               battle_label.setBounds(170,0,250,270);
               text_field.setVisible(false);
               enter_button.setVisible(false);
               potion_exception_label.setVisible(false);
            } 
            })); 
        
        attack_button.setBounds(195, 395, 100,100);
        add(attack_button);
        
        use_potion_button.setBounds(315, 395, 100,100);
        add(use_potion_button);
               
        use_potion_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
               battle_label.setText(player.showPotions(enemy));
               battle_label.setBounds(170,0,300,150);             
               
               text_field.setVisible(true);
               enter_button.setVisible(true);
               add(potion_exception_label);
               potion_exception_label.setVisible(false);
               
               enter_button.addActionListener((new ActionListener(){ 
                public void actionPerformed(ActionEvent evt) {
                    try {
                       int num = Integer.valueOf(text_field.getText());
                       num = Integer.valueOf(text_field.getText()); 
                       if (num <= 0 || text_field.getText().equals("")) {
                           potion_exception_label.setVisible(true);
                           potion_exception_label.setText("Invalid number inputted. Please try again.");
                           text_field.setText("");
                       }
                       else if (player.getInventory().get(num-1) instanceof Hands || player.getInventory().get(num-1) instanceof Shield || player.getInventory().get(num-1) instanceof Sword || player.getInventory().get(num-1) instanceof Zombie_Sword)
                       {
                           potion_exception_label.setVisible(true);
                           potion_exception_label.setText("Inputted number is not a potion. Please try again.");
                           text_field.setText("");
                       }
                       else{
                           battle_label.setText(enemy.battle(player, enemy, options, 2, num-1));
                           potion_exception_label.setVisible(false);
                           text_field.setText("");
                           text_field.setVisible(false);
                           enter_button.setVisible(false);
                       }
                   }
                   catch (NumberFormatException e) {
                       potion_exception_label.setVisible(true);
                       potion_exception_label.setText("Input is not numerical. Try again.");
                   }
                   catch (IndexOutOfBoundsException e){
                       potion_exception_label.setVisible(true);
                       potion_exception_label.setText("Input is out of bounds. Try again.");
                   }
                   String text = battle_label.getText();
                   checkStatus(battle_label, player, enemy, window);
                } 
                })); 
            }
        }));
        
        attack_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
               battle_label.setBounds(170,20,400,120);
               battle_label.setText(enemy.battle(player, enemy, options, 1, 0));
               
                Button mana_attack_button = new Button("");
                mana_attack_button.setBounds(195, 350, 100, 30);
                add(mana_attack_button);
                mana_attack_button.setVisible(false);
               
               text_field.setVisible(false);
               enter_button.setVisible(false);
               potion_exception_label.setVisible(false);
               
               checkStatus(battle_label, player, enemy, window);
               checkMana(player, enemy, options, battle_label, window);
            } 
            })); 
    }
    
    public static void checkStatus(JLabel battle_label, Player player, Enemy enemy, Battle window) {
       String text = battle_label.getText();
       if (text.equals("<html>"+enemy.getCharacterType()+" is dead.<br>Would you like to view their inventory?</html>")) {
             ViewInventory vi = new ViewInventory(player, enemy);
             window.dispose();
       }
       
       else if (text.contains("Your health is now 0hp") || text.contains("Your health is now -") || text.contains("GAME OVER")) {
           EndGameMessage egm = new EndGameMessage("GAME OVER", "Thank you for playing", "RED");
           window.dispose();
       }
    }
    
    public static void checkMana(Player player, Enemy enemy, int options, JLabel label, Battle window){
       
      if (player instanceof Warrior) {
          Button mana_attack_button = new Button("Use PowerPoints");
          if (player.getMana() >=5) {
              mana_attack_button.setBounds(195, 350, 100,30);
              window.add(mana_attack_button);
          }
          else {
              mana_attack_button.setVisible(false);
           }
          
          mana_attack_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
              label.setBounds(170,20,400,120);
              label.setText(enemy.battle(player, enemy, options, 4, 0));
              mana_attack_button.setVisible(false);
              checkStatus(label, player, enemy, window);
            }
            }));
      }
               
      else if (player instanceof Magician)  {
          Button mana_attack_button = new Button("Steal an Item");
          if (player.getMana() >= 5) {
              mana_attack_button.setBounds(195, 350, 103,40);
              window.add(mana_attack_button);
          }
          else {
              mana_attack_button.setVisible(false);
           }
           
          mana_attack_button.addActionListener((new ActionListener(){ 
           public void actionPerformed(ActionEvent evt) {
               label.setBounds(170,20,400,120);
               if (enemy.showInventory().equals("<html>Inventory:<br>")){
                  label.setText("Enemy inventory is empty.");
                  mana_attack_button.setVisible(false);
                  window.show_stats_button.setVisible(true);
                  window.attack_button.setVisible(true);
                  window.use_potion_button.setVisible(true);
                  window.text_field.setVisible(false);
                  window.enter_button.setVisible(false);
                  window.text_field.setText("");
              }
              else{
                  mana_attack_button.setVisible(false);
                  window.show_stats_button.setVisible(false);
                  window.attack_button.setVisible(false);
                  window.use_potion_button.setVisible(false);
                  window.text_field.setVisible(true);
                  window.enter_button.setVisible(true);
                  
                  Label exception_label = new Label();
                  exception_label.setBounds(225, 230, 270, 25);
                  window.add(exception_label);
                  exception_label.setVisible(false);
                  
                  label.setText(enemy.showInventory());
                  window.enter_button.addActionListener((new ActionListener(){ 
                    public void actionPerformed(ActionEvent evt) {
                      try {
                          int item = Integer.valueOf(window.text_field.getText());
                          if (item <=0) {
                              exception_label.setText("Invalid input");
                              exception_label.setVisible(true);
                            }
                          else{
                              label.setText(enemy.battle(player, enemy, options, 4, item));
                              mana_attack_button.setVisible(false);
                              window.show_stats_button.setVisible(true);
                              window.attack_button.setVisible(true);
                              window.use_potion_button.setVisible(true);
                              window.text_field.setVisible(false);
                              window.enter_button.setVisible(false);
                              window.text_field.setText("");
                              exception_label.setVisible(true);
                        }
                      }
                      catch (NumberFormatException e) {
                      }
                      catch (IndexOutOfBoundsException e) {
                          System.out.println(e.getMessage());
                          exception_label.setText("Input is out of bounds. Please try again");
                      }
                    }
                    }));
                }
                checkStatus(label, player, enemy, window);
           }    
           }));
       }
        
    }
}