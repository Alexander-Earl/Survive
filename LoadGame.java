import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.io.*;                                                    // setBounds(int x-coordinate, int y-coordinate, int width, int height)
public class LoadGame extends GUI{
  
    public LoadGame() throws FileNotFoundException, IOException, ClassNotFoundException {
        Button back_button = new Button("BACK");
        back_button.setBounds(15, 40, 60, 30);
        add(back_button);
        back_button.addActionListener((new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                WorldGUIFrame.main();
                dispose();
                } 
            }
            ));
        
        Label characters_name_label = new Label("Enter your characters name: ");
        characters_name_label.setBounds(80, 190, 170, 35);
        add(characters_name_label);
        
        TextField character_name_text_field = new TextField(40);
        character_name_text_field.setBounds(250, 195, 200, 25);
        add(character_name_text_field);
        
        Button enter_button = new Button("Enter");
        enter_button.setBounds(225, 420, 50, 25);
        add(enter_button);
        
        Label save_not_found = new Label("Save not found. Please enter your characters name.");
        save_not_found.setBounds(110, 380, 300, 25);
        add(save_not_found);
        save_not_found.setVisible(false);
        
        enter_button.addActionListener((new ActionListener(){ 
        public void actionPerformed(ActionEvent evt) {
                try {
                    String name = character_name_text_field.getText();
                    Player player = World.LoadGame(name);
                    if (player == null) {
                        save_not_found.setVisible(true);
                    }
                    else{
                        InGame ig = new InGame(player, World.welcome(player));
                        dispose();
                    }
                }
                catch (FileNotFoundException e) {System.out.println(e.getMessage());} catch (IOException e) {System.out.println(e.getMessage());} catch (ClassNotFoundException e){System.out.println(e.getMessage());}
            }}
        ));
    }
}