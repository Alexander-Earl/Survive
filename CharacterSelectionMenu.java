import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;
    public class CharacterSelectionMenu extends GUI {    // setBounds(int x-coordinate, int y-coordinate, int width, int height)
        
      public CharacterSelectionMenu(Player player, String character_type, int attacking, int defensive) {
          JLabel label = new JLabel("Would you like to be an Attacking "+character_type+" or Defensive "+character_type+"?");
          label.setBounds(70,60,400,40);
          add(label);
          
          Button attacking_button = new Button("Attacking "+ character_type);
          attacking_button.setBounds(195, 140, 115,100);
          add(attacking_button);          
          
          Button defensive_button = new Button("Defensive "+ character_type);
          defensive_button.setBounds(195, 300, 115,100);
          add(defensive_button);
          
          TextField text_field = new TextField(40);
          text_field.setBounds(250, 195, 200, 25);
          add(text_field);
          text_field.setVisible(false);
          
          Button enter_button = new Button("Enter");
          enter_button.setBounds(225, 420, 50, 25);
          add(enter_button);
          enter_button.setVisible(false);
          
         attacking_button.addActionListener((new ActionListener(){    
         public void actionPerformed(ActionEvent evt) {
             enterCharactersName(label, attacking_button, defensive_button,text_field, enter_button);
             enter_button.addActionListener((new ActionListener(){    
             public void actionPerformed(ActionEvent evt) {
                 try{
                     World.gameMenu(player, attacking, text_field.getText());
                     player.setName(text_field.getText());
                     dispose();
                 }
                 catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e){}
             }
             }));
         }
         }));
         
         defensive_button.addActionListener((new ActionListener(){ 
         public void actionPerformed(ActionEvent evt) {
            enterCharactersName(label, attacking_button, defensive_button, text_field, enter_button);
            enter_button.addActionListener((new ActionListener(){    
            public void actionPerformed(ActionEvent evt) {
                try{    
                    World.gameMenu(player, defensive, text_field.getText());
                    player.setName(text_field.getText());
                    dispose();
                }
                catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e){}
            } 
            }));
            
         }
        }));
      }
      
      public static void enterCharactersName (JLabel label, Button attacking_button, Button defensive_button, TextField text_field, Button enter_button) {
            attacking_button.setVisible(false);
            defensive_button.setVisible(false);
            label.setText("Enter your characters name: ");
            label.setBounds(80, 190, 170, 35);
            text_field.setVisible(true);
            enter_button.setVisible(true);
      }
}