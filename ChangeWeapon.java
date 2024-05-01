import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;

public class ChangeWeapon extends GUI {

    public ChangeWeapon(Player player, String weapons){
        JLabel label = new JLabel(weapons);
        label.setBounds(170,0,300,150);
        add(label);
        
        TextField weapon_text_field = new TextField(20);
        weapon_text_field.setBounds(150, 195, 200, 25);
        add(weapon_text_field);
        
        JLabel exception_label = new JLabel("Select the weapon through entering the number.");
        exception_label.setBounds(125, 210, 420, 50);
        add(exception_label);
        
        Button enter_button = new Button("Enter");
        enter_button.setBounds(225, 420, 50, 25);
        add(enter_button);
        enter_button.addActionListener((new ActionListener(){    
             public void actionPerformed(ActionEvent evt) {
                 try{
                  int selected_weapon = Integer.valueOf(weapon_text_field.getText());
                       if (selected_weapon <= 0) {
                           exception_label.setVisible(true);
                           exception_label.setText("Invalid number inputted. Please try again.");
                           weapon_text_field.setText("");
                       }
                       
                       else if (player.getInventory().get(selected_weapon-1) instanceof Potion){
                           exception_label.setText("Inputted number is not a weapon. Please try again.");
                       }
                        
                       else{
                           exception_label.setVisible(false);
                           weapon_text_field.setText("");
                           player.setWeaponEquipped(player.getInventory().get(selected_weapon-1));
                           try{
                               InGame ig = new InGame(player, player.getWeaponEquippedName()+" is now equipped as your weapon.");
                               dispose();
                           }
                           catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e) {System.out.println(e.getMessage());}
                       }
                   }
                   catch (NumberFormatException e) {
                       exception_label.setVisible(true);
                       weapon_text_field.setText("");
                    }
                   catch (IndexOutOfBoundsException e){
                       exception_label.setVisible(true);
                       weapon_text_field.setText("");
                    }
             }
             }));
    }


}