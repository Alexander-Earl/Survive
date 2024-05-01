import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;
public class NewGame extends GUI {    // setBounds(int x-coordinate, int y-coordinate, int width, int height)
        
  public NewGame() {
      JLabel selected_character_label = new JLabel("What Character would you like to be?");
      selected_character_label.setBounds(155,60,400,40);
      add(selected_character_label);
      
      Player player = new Player(100);
      
      Button warrior_button = new Button("Warrior");
      warrior_button.setBounds(195, 140, 100,100);
      add(warrior_button);
      warrior_button.addActionListener((new ActionListener(){ 
        public void actionPerformed(ActionEvent evt) { 
            CharacterSelectionMenu csm = new CharacterSelectionMenu(player, "Warrior", 3, 4);
            dispose();
        } 
        }));
      
      Button magician_button = new Button("Magician");
      magician_button.setBounds(195, 300, 100,100);
      add(magician_button);
      magician_button.addActionListener((new ActionListener(){ 
        public void actionPerformed(ActionEvent evt) {
            CharacterSelectionMenu csm = new CharacterSelectionMenu(player, "Magician", 5, 6);
            dispose();
            } 
        }));
        
  }
}