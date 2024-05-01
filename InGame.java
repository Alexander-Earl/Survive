import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class InGame extends GUI {    // setBounds(int x-coordinate, int y-coordinate, int width, int height
    JLabel test = new JLabel();
    public InGame(Player player, String string) throws FileNotFoundException, IOException, ClassNotFoundException {
          JLabel label = new JLabel(string); //World.welcome(player);
          label.setBounds(155,30,400,100);
          add(label);
          
          Button weapon_button = new Button("Change Weapon");
          weapon_button.setBounds(75, 395, 100,100);
          add(weapon_button);
          weapon_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                try {
                    label.setText(player.interaction("C"));
                    label.setBounds(170,0,300,150);
                }
                 catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e) {System.out.println(e.getMessage());}
                String weapons = label.getText();
                 if (weapons.contains("Weapons:")){
                    ChangeWeapon cw = new ChangeWeapon(player, weapons);
                    dispose();
                }
                } 
            }));
          
          Button walk_button = new Button("Walk");
          walk_button.setBounds(195, 395, 100,100);
          add(walk_button);
          walk_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                try{
                    label.setText(player.interaction("W"));
                    label.setBounds(150,20,400,100);
                     if (player.getWalked() > 4 && player.getWalked() < 8) {
                         World.level1(player);
                         dispose();
                     }
                     else if (player.getWalked() >=8 && player.getWalked() < 13) {
                         World.level2(player);
                         dispose();
                     }
                     else if (player.getWalked() >=13 && player.getWalked() < 19) {
                        World.level3(player);
                        dispose();
                     }
                     else if (player.getWalked() >=19 && player.getWalked() < 23) {
                         World.level4(player);
                         dispose();
                     }
                     else if (player.getWalked() >=23 && player.getWalked() < 27) {
                         World.level5(player);
                         dispose();
                     }
                     else if (player.getWalked() >= player.getEndPoint() && player.getLevelCompleted() == 5){
                         EndGameMessage egm = new EndGameMessage("Congratulations", "You have completed the game.", "GREEN");
                         dispose();
                     }
                 }
                 catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e) {System.out.println(e.getMessage());} 
                } 
            }));
            
          Button save_button = new Button("Save and Exit");
          save_button.setBounds(315, 395, 100,100);
          add(save_button);
          save_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                try{
                    label.setText(player.interaction("S"));
                    label.setBounds(200,50,400,100);
                    World.exit();
                }
                 catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e) {System.out.println(e.getMessage());} 
                } 
            }));  
      }
      
    public void askToBattle() {
         test.setText("Would you like to battle?");
         test.setBounds(200,50,400,100);
         add(test);
         Button yes_button = new Button("Yes");
         yes_button.setBounds(195, 140, 115,100);
         add(yes_button);          
         
         Button no_button = new Button("No");
         no_button.setBounds(195, 300, 115,100);
         add(no_button);
      }
}