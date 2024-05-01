import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.io.*;

public class WorldGUIFrame extends GUI{ 
        
  public static void main() {
      WorldGUIFrame world = new WorldGUIFrame();
      world.setSize(500, 501);
  }
    
  public WorldGUIFrame() {
      BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
      setLayout(boxlayout);
      
      Label welcome_banner = new Label("Welcome to Survival!", Label.CENTER);
      welcome_banner.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
      add(welcome_banner);
      
      Button new_game_button = new Button("New GAME");
      add(new_game_button);
      new_game_button.addActionListener(new ActionListener(){ 
      public void actionPerformed(ActionEvent evt)  {
           NewGame f = new NewGame();
           dispose();
           } 
      });
      
      Button load_game_button = new Button("Load Game");
      add(load_game_button);      
      load_game_button.addActionListener(new ActionListener(){ 
      public void actionPerformed(ActionEvent evt) {
          try{ 
           LoadGame f = new LoadGame();
           dispose();}
           catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e) {}
           } 
      });
      
      Button about_button = new Button("About the game");
      add(about_button);
      about_button.addActionListener(new ActionListener(){ 
      public void actionPerformed(ActionEvent evt) {
            About a = new About();
            dispose();
           } 
      });
      
  }
 

}