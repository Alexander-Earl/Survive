import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class EndGameMessage extends GUI {    // setBounds(int x-coordinate, int y-coordinate, int width, int height)
    
  public EndGameMessage(String title, String message, String colour) {
      JLabel game_over = new JLabel(title);
      game_over.setBounds(135,60,400,40);
      add(game_over);
      
      if (colour.equals("GREEN")) {
          game_over.setForeground(Color.GREEN);
      }
      
      else {
        game_over.setForeground(Color.RED);
      }
      
      game_over.setFont(game_over.getFont().deriveFont(32f));
      
      JLabel thank_you = new JLabel(message);
      thank_you.setBounds(175,100,200,40);
      add(thank_you);
      
      Button exit_button = new Button("Close");
      exit_button.setBounds(210,300,100,75);
      add(exit_button);
      exit_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                  World.exit();
                } 
            }));
    }
}