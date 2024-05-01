import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class askToBattle extends GUI {

    public askToBattle(Player player, Enemy enemy, int options) {
          JLabel near_enemy_label = new JLabel("<html>You are near a "+enemy.getCharacterType()+"<br>Would you like to battle?</html>");
          near_enemy_label.setBounds(190,50,200,50);
          add(near_enemy_label);
          
          Button yes_button = new Button("Yes");
          yes_button.setBounds(195, 140, 115,100);
          add(yes_button);  
          yes_button.addActionListener((new ActionListener(){    
             public void actionPerformed(ActionEvent evt) {
                try{
                     FileWriter.saveGame(player); 
                     Battle b = new Battle(player, enemy, options);
                     dispose();
                }
                catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e){}
             }}));
          
          Button no_button = new Button("No");
          no_button.setBounds(195, 300, 115,100);
          add(no_button);
          no_button.addActionListener((new ActionListener(){    
             public void actionPerformed(ActionEvent evt) {
                 try{
                     player.decreaseHealth(25);
                     if (player.getHealth() <=0) {
                         EndGameMessage egm = new EndGameMessage("GAME OVER", "Thank you for playing", "RED");
                         dispose();
                    }
                     else{
                         FileWriter.saveGame(player);
                         InGame ig = new InGame(player, "<html>Your progress has now been saved.<br>Your health is now: "+player.getHealth()+"hp</html>");                  
                         dispose();
                    }
                 }
                 catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e){}
             }}));
    } 
    
}